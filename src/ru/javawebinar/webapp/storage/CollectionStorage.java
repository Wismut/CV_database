package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.model.Resume;

import java.util.Collection;

public class CollectionStorage extends AbstractStorage {
	@Override
	protected void doSave(Resume r) {

	}

	@Override
	public void clear() {

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
