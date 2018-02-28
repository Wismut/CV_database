package ru.javawebinar.webapp.storage;

import com.sun.javafx.util.Logging;
import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArrayStorage implements IStorage {

	private static final int LIMIT = 100;
	private Resume[] array = new Resume[LIMIT];
	private static Logger LOGGER = Logger.getLogger(ArrayStorage.class.getName());

	private int size = 0;

	@Override
	public void clear() {
		LOGGER.info("Delete all resumes.");
		Arrays.fill(array, null);
		size = 0;
	}

	@Override
	public void save(Resume r) throws WebAppException {
		LOGGER.info("Save resume with uuid = " + r.getUuid());
		int idx = getIndex(r.getUuid());
//		if (idx != -1) try {
//			throw new WebAppException("Resume " + r.getUuid() + " already exist", r);
//		} catch (WebAppException e) {
//			LOGGER.log(Level.SEVERE, e.getMessage(), e);
//		}
		if (idx != -1) throw new WebAppException("Resume " + r.getUuid() + " already exist", r);
		array[size++] = r;
//		for (int i = 0; i < LIMIT; i++) {
//			Resume resume = array[i];
//			if (resume != null) {
//				if (resume.equals(r)) throw new RuntimeException("Already present");
//			} else {
//				array[i] = r;
//				return;
//			}
//		}
	}

	@Override
	public void update(Resume r) {
		LOGGER.info("Update resume with " + r.getUuid());
		int idx = getIndex(r.getUuid());
		if (idx == -1) throw new WebAppException("Resume " + r.getUuid() + " not exist", r);
		array[idx] = r;
//		for (int i = 0; i < LIMIT; i++) {
//			if (array[i] != null && array[i].equals(r)) {
//				array[i] = r;
//				return;
//			}
//		}
	}

	@Override
	public Resume load(String uuid) {
		LOGGER.info("Load resume with " + uuid);
		int idx = getIndex(uuid);
		if (idx == -1) throw new WebAppException("Resume " + uuid + " not exist");
		return array[idx];
//		if (uuid == null) return null;
//		for (int i = 0; i < LIMIT; i++) {
//			if (array[i] != null && uuid.equals(array[i].getUuid()))
//				return array[i];
//		}
//		return null;
	}

	@Override
	public void delete(String uuid) {
		LOGGER.info("Delete resume with " + uuid);
		int idx = getIndex(uuid);
		if (idx == -1) throw new WebAppException("Resume " + uuid + " not exist");
		int numMoved = size - idx - 1;
		if (numMoved > 0)
			System.arraycopy(array, idx + 1, array, idx, numMoved);
		array[--size] = null;
//		if (uuid == null) return;
//		for (int i = 0; i < LIMIT; i++) {
//			if (array[i] != null && uuid.equals(array[i].getUuid())) {
//				array[i] = null;
//				return;
//			}
//		}
	}

	@Override
	public Collection<Resume> getAllSorted() {
		Arrays.sort(array, 0, size);
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

	private int getIndex(String uuid) {
		for (int i = 0; i < LIMIT; i++) {
			if (array[i] != null && array[i].getUuid().equals(uuid))
				return i;
		}
		return -1;
	}
}
