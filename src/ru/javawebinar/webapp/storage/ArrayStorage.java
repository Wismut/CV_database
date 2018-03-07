package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ArrayStorage extends AbstractStorage {

	private static final int LIMIT = 100;
	private Resume[] array = new Resume[LIMIT];
//	private static Logger logger = Logger.getLogger(ArrayStorage.class.getName());

	private int size = 0;

	@Override
	protected void doClear() {
		Arrays.fill(array, null);
		size = 0;
	}

	@Override
	protected void doUpdate(Resume r) {
		int idx = getIndex(r.getUuid());
//		if (idx == -1) throw new WebAppException("Resume " + r.getUuid() + " not exist", r);
		array[idx] = r;
	}

	@Override
	public Resume doLoad(String uuid) {
		int idx = getIndex(uuid);
//		if (idx == -1) throw new WebAppException("Resume " + uuid + " not exist");
		return array[idx];
	}

	@Override
	public void doDelete(String uuid) {
		int idx = getIndex(uuid);
//		if (idx == -1) throw new WebAppException("Resume " + uuid + " not exist");
		int numMoved = size - idx - 1;
		if (numMoved > 0)
			System.arraycopy(array, idx + 1, array, idx, numMoved);
		array[--size] = null;
	}

	@Override
	public Collection<Resume> getAllSorted() {
		Arrays.sort(array, 0, size);
		return Arrays.asList(Arrays.copyOf(array, size));
	}

	@Override
	protected List<Resume> doGetAll() {
		return Arrays.asList(array);
	}

	@Override
	public int size() {
		int count = 0;
		for (int i = 0; i < LIMIT; i++) {
			if (array[i] != null) count++;
		}
		return count;
	}

	private int getIndex(String uuid) {
		for (int i = 0; i < LIMIT; i++) {
			if (array[i] != null && array[i].getUuid().equals(uuid))
				return i;
		}
		return -1;
	}

	@Override
	protected void doSave(Resume r) {
		int idx = getIndex(r.getUuid());
//		if (idx != -1) throw new WebAppException("Resume " + r.getUuid() + " already exist", r);
		array[size++] = r;
	}

	@Override
	protected boolean exist(String uuid) {
		for (Resume resume : array) {
			if (resume != null && resume.getUuid().equals(uuid)) return true;
		}
		return false;
	}
}
