package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.Resume;


import java.util.*;

public class CollectionStorage extends AbstractStorage {

	public Collection<Resume> resumeList = new HashSet<>();

	@Override
	protected void doSave(Resume r) {
		resumeList.add(r);
	}

	@Override
	protected void doClear() {
		resumeList.clear();
	}

	@Override
	protected void doUpdate(Resume r) {
		if (!resumeList.contains(r)) throw new WebAppException("Resume " + r.getUuid() + " not found");
		resumeList.add(r);
	}

	@Override
	protected Resume doLoad(String uuid) {
		return getByUuid(uuid);
	}

	@Override
	protected void doDelete(String uuid) {
		resumeList.remove(getByUuid(uuid));
	}

	@Override
	public Collection<Resume> getAllSorted() {
		Set<Resume> sortedSet = new TreeSet<>(Comparator.comparing(Resume::getFullName));
		sortedSet.addAll(resumeList);
		return sortedSet;
	}

	@Override
	public int size() {
		return resumeList.size();
	}

	private Resume getByUuid(String uuid) {
		for (Resume resume : resumeList) {
			if (resume.getUuid().equals(uuid)) return resume;
		}
		throw new WebAppException("Resume " + uuid + " not found");
	}
}
