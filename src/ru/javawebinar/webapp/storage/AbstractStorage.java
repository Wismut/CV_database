package ru.javawebinar.webapp.storage;


import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.Resume;

import java.util.Collection;
import java.util.logging.Logger;

public abstract class AbstractStorage implements IStorage {
	protected Logger logger = Logger.getLogger(getClass().getName());

	@Override
	public void save(Resume r) throws WebAppException {
		logger.info("Save resume with uuid = " + r.getUuid());
		// TODO: 01.03.2018  try to move here exception treatment
		doSave(r);
	}

	protected abstract void doSave(Resume r);

	@Override
	public void clear() {
		logger.info("Delete all resumes.");
		doClear();
	}

	protected abstract void doClear();

	@Override
	public void update(Resume r) {
		logger.info("Update resume with " + r.getUuid());
		doUpdate(r);
	}

	protected abstract void doUpdate(Resume r);

	@Override
	public Resume load(String uuid) {
		logger.info("Load resume with " + uuid);
		return doLoad(uuid);
	}

	protected abstract Resume doLoad(String uuid);

	@Override
	public void delete(String uuid) {
		logger.info("Delete resume with " + uuid);
		doDelete(uuid);
	}

	protected abstract void doDelete(String uuid);

	@Override
	public Collection<Resume> getAllSorted() {
		return null;
	}

	@Override
	public int size() {
		return 0;
	}
}
