package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.model.Resume;

import java.util.Collection;

public class ArrayStorage implements IStorage {

	private static final int LIMIT = 100;
	private Resume[] array = new Resume[LIMIT];

	@Override
	public void clear() {
		for (int i = 0; i < LIMIT; i++) {
			array[i] = null;
		}
	}

	@Override
	public void save(Resume r) {
		for (int i = 0; i < LIMIT; i++) {
			Resume resume = array[i];
			if (resume != null) {
				if (resume.equals(r)) throw new RuntimeException("Already present");
			} else {
				array[i] = r;
				return;
			}
		}
	}

	@Override
	public void update(Resume r) {
		for (int i = 0; i < LIMIT; i++) {
			if (array[i] != null && array[i].equals(r)) {
				array[i] = r;
				return;
			}
		}
	}

	@Override
	public Resume load(String uuid) {
		if (uuid == null) return null;
		for (int i = 0; i < LIMIT; i++) {
			if (array[i] != null && uuid.equals(array[i].getUuid()))
				return array[i];
		}
		return null;
	}

	@Override
	public void delete(String uuid) {
		if (uuid == null) return;
		for (int i = 0; i < LIMIT; i++) {
			if (array[i] != null && uuid.equals(array[i].getUuid())) {
				array[i] = null;
				return;
			}
		}
	}

	@Override
	public Collection<Resume> getAllSorted() {
		return null;
	}

	@Override
	public int size() {
		int count = 0;
		for (int i = 0; i < LIMIT; i++) {
			if (array[i] != null) count++;
		}
		return count;
	}
}
