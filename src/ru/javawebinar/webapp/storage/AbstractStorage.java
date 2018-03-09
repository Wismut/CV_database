package ru.javawebinar.webapp.storage;


import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.ContactType;
import ru.javawebinar.webapp.model.Resume;

import java.util.*;
import java.util.logging.Logger;

public abstract class AbstractStorage implements IStorage {
	protected final Logger logger = Logger.getLogger(getClass().getName());

	@Override
	public void save(Resume r) throws WebAppException {
		logger.info("Save resume with uuid = " + r.getUuid());
		if (exist(r.getUuid())) throw new WebAppException("Resume " + r.getUuid() + " already exist");
		doSave(r);
	}

	protected abstract void doSave(Resume r);

	protected abstract boolean exist(String uuid);

	@Override
	public void clear() {
		logger.info("Delete all resumes.");
		doClear();
	}

	protected abstract void doClear();

	@Override
	public void update(Resume r) {
		logger.info("Update resume with " + r.getUuid());
		if (!exist(r.getUuid())) throw new WebAppException("Resume " + r.getUuid() + " not found");
		doUpdate(r);
	}

	protected abstract void doUpdate(Resume r);

	@Override
	public Resume load(String uuid) {
		logger.info("Load resume with " + uuid);
		if (!exist(uuid)) throw new WebAppException("Resume " + uuid + " not found");
		return doLoad(uuid);
	}

	protected abstract Resume doLoad(String uuid);

	@Override
	public void delete(String uuid) {
		logger.info("Delete resume with " + uuid);
		if (!exist(uuid)) throw new WebAppException("Resume " + uuid + " not found");
		doDelete(uuid);
	}

	protected abstract void doDelete(String uuid);

	@Override
	public Collection<Resume> getAllSorted() {
		logger.info("Get sorted resumes");
		List<Resume> resumes = doGetAll();
		Collections.sort(resumes, (o1, o2) -> {
			int nameCmp = o1.getFullName().compareTo(o2.getFullName());
			if (nameCmp != 0) return nameCmp;
			return o1.getUuid().compareTo(o2.getUuid());
		});
		return resumes;
//		return Collections.singletonList(new Resume());
	}

	protected abstract List<Resume> doGetAll();

	@Override
	public abstract int size();
}
