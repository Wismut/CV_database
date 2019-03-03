package ru.javawebinar.webapp.storage;


import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.Resume;

import java.util.*;
import java.util.logging.Logger;

public abstract class AbstractStorage<C> implements IStorage {
	protected final Logger logger = Logger.getLogger(getClass().getName());

	@Override
	public void save(Resume r) throws WebAppException {
		logger.info("Save resume with uuid = " + r.getUuid());
		C context = getContext(r);
		if (exist(context)) throw new WebAppException("Resume " + r.getUuid() + " already exist");
		doSave(context, r);
	}

	protected abstract void doSave(C context, Resume r);

	protected abstract C getContext(String uuid);

	protected abstract boolean exist(C context);

	@Override
	public void clear() {
		logger.info("Delete all resumes.");
		doClear();
	}

	protected abstract void doClear();

	@Override
	public void update(Resume r) {
		logger.info("Update resume with " + r.getUuid());
		C context = getContext(r);
		if (!exist(context)) throw new WebAppException("Resume " + r.getUuid() + " not found");
		doUpdate(context, r);
	}

	protected abstract void doUpdate(C context, Resume r);

	@Override
	public Resume load(String uuid) {
		logger.info("Load resume with " + uuid);
		C context = getContext(uuid);
		if (!exist(context)) throw new WebAppException("Resume " + uuid + " not found");
		return doLoad(context);
	}

	public abstract Resume doLoad(C context);

	private C getContext(Resume resume) {
		return getContext(resume.getUuid());
	}

	@Override
	public void delete(String uuid) {
		logger.info("Delete resume with " + uuid);
		C context = getContext(uuid);
		if (!exist(context)) throw new WebAppException("Resume " + uuid + " not found");
		doDelete(context);
	}

	protected abstract void doDelete(C context);

	@Override
	public Collection<Resume> getAllSorted() {
		logger.info("Get sorted resumes");
		List<Resume> resumes = doGetAll();
		resumes.sort((o1, o2) -> {
			int nameCmp = o1.getFullName().compareTo(o2.getFullName());
			if (nameCmp != 0) return nameCmp;
			return o1.getUuid().compareTo(o2.getUuid());
		});
		return resumes;
	}

	protected abstract List<Resume> doGetAll();

	@Override
	public abstract int size();

	@Override
	public boolean isSectionSupported() {
		return true;
	}
}
