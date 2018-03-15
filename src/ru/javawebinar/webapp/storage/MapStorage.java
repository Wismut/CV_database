package ru.javawebinar.webapp.storage;


import ru.javawebinar.webapp.model.Resume;


import java.util.*;

public class MapStorage extends AbstractStorage<String> {

	private Map<String, Resume> map = new HashMap<>();

	@Override
	protected void doSave(String context, Resume r) {
		map.put(r.getUuid(), r);
	}

	@Override
	protected String getContext(String uuid) {
		return uuid;
	}

	@Override
	protected boolean exist(String context) {
		return map.containsKey(context);
	}

	@Override
	protected void doClear() {
		map.clear();
	}

	@Override
	protected void doUpdate(String context, Resume r) {
		map.put(r.getUuid(), r);
	}

	@Override
	public Resume doLoad(String context, String uuid) {
		return map.get(uuid);
	}

	@Override
	protected void doDelete(String context, String uuid) {
		map.remove(uuid);
	}

	@Override
	protected List<Resume> doGetAll() {
		return new ArrayList<>(map.values());
	}

	@Override
	public int size() {
		return map.size();
	}
}
