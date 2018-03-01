package ru.javawebinar.webapp.storage;

import com.sun.xml.internal.bind.v2.TODO;
import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.Resume;

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

}
