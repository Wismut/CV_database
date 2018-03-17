package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public class ArrayStorage extends AbstractStorage<Integer> {

	private static final int LIMIT = 100;
	private Resume[] array = new Resume[LIMIT];

	private int size = 0;

	@Override
	protected void doClear() {
		Arrays.fill(array, null);
		size = 0;
	}

	@Override
	protected void doUpdate(Integer idx, Resume r) {
		array[idx] = r;
	}

	@Override
	public Resume doLoad(Integer idx) {
		return array[idx];
	}

	@Override
	public void doDelete(Integer idx) {
		int numMoved = size - idx - 1;
		if (numMoved > 0)
			System.arraycopy(array, idx + 1, array, idx, numMoved);
		array[--size] = null;
	}

	@Override
	protected List<Resume> doGetAll() {
		return Arrays.asList(Arrays.copyOf(array, size));
	}

	@Override
	public int size() {
		int count = 0;
		for (int i = 0; i < LIMIT; i++) {
			if (array[i] != null) count++;
		}
		return count;
	}

	public Integer getContext(String uuid) {
		for (int i = 0; i < LIMIT; i++) {
			if (array[i] != null && array[i].getUuid().equals(uuid))
				return i;
		}
		return -1;
	}

	@Override
	protected boolean exist(Integer index) {
		return index != -1;
	}

	@Override
	protected void doSave(Integer idx, Resume r) {
		idx = getContext(r.getUuid());
		array[size++] = r;
	}

//	@Override
//	protected boolean exist(String uuid) {
//		idx = getContext(uuid);
//		return idx != -1;
//	}
}
