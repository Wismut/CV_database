package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.Resume;

import java.io.File;
import java.util.List;

public class FileStorage extends AbstractStorage<File> {

	private File dir;

	public FileStorage(String path) {
		this.dir = new File(path);
		if (!dir.isDirectory() || !dir.canWrite())
			throw new IllegalArgumentException("'" + path + "' is not a directory or is not writable");
	}

	@Override
	protected void doSave(File context, Resume r) {

	}

	@Override
	protected File getContext(String fileName) {
		return new File(fileName);
	}

	@Override
	protected boolean exist(File file) {
		return file.exists();
	}

	@Override
	protected void doClear() {
		File[] files = dir.listFiles();
		if (files == null) return;
		for (File file : files) {
			doDelete(file);
		}
	}

	@Override
	protected void doUpdate(File context, Resume r) {

	}

	@Override
	public Resume doLoad(File context) {
		return null;
	}

	@Override
	protected void doDelete(File file) {
		if (file.delete())
			throw new WebAppException("File " + file.getAbsolutePath() + " can not be deleted");
	}

	@Override
	protected List<Resume> doGetAll() {
		return null;
	}

	@Override
	public int size() {
		return 0;
	}
}
