package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.Resume;

import java.util.Collection;

public class FileStorage implements IStorage {
	@Override
	public void clear() {

	}

	@Override
	public void save(Resume r) throws WebAppException {

	}

	@Override
	public void update(Resume r) {

	}

	@Override
	public Resume load(String uuid) {
		return null;
	}

	@Override
	public void delete(String uuid) {

	}

	@Override
	public Collection<Resume> getAllSorted() {
		return null;
	}

	@Override
	public int size() {
		return 0;
	}
}
