package ru.javawebinar.webapp.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.Contact;
import ru.javawebinar.webapp.model.ContactType;
import ru.javawebinar.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collection;

public class ArrayStorageTest {

	private Resume R1, R2, R3;

	private ArrayStorage storage = new ArrayStorage();


	@BeforeClass
	public static void beforeClass() {
		// same as static block
	}

	@Before
	public void before() {
		R1 = new Resume("полное имя1", "location1");
		R1.addContact(new Contact(ContactType.MAIL, "erfg@dfg.com"));
		R1.addContact(new Contact(ContactType.PHONE, "23453455"));
		R2 = new Resume("полное имя2", null);
		R2.addContact(new Contact(ContactType.MAIL, "eredrfgedfg@dfg.com"));
		R2.addContact(new Contact(ContactType.PHONE, "2344564553455"));
		R3 = new Resume("полное имяr564553", null);
		storage.clear();
		storage.save(R1);
		storage.save(R2);
		storage.save(R3);
	}

	@org.junit.Test
	public void clear() {
		storage.clear();
		Assert.assertEquals(0, storage.size());
	}

	@org.junit.Test
	public void save() {
		storage.clear();
		storage.save(R1);
		Assert.assertEquals(R1, storage.load(R1.getUuid()));
	}

	@org.junit.Test
	public void update() {
		storage.update(new Resume(R2.getUuid(), "new full name", "loca"));
		Resume resume = storage.load(R2.getUuid());
		Assert.assertEquals(resume.getFullName(), "new full name");
		Assert.assertEquals(resume.getLocation(), "loca");
	}

	@org.junit.Test
	public void load() {
		Resume resume = storage.load(R3.getUuid());
		Assert.assertEquals(R3.getLocation(), resume.getLocation());
		Assert.assertEquals(R3.getFullName(), resume.getFullName());
		Assert.assertEquals(R3.getUuid(), resume.getUuid());
	}

	@Test(expected = WebAppException.class)
	public void deleteNotFound() {
		storage.load("dummy");
	}

	@org.junit.Test(expected = WebAppException.class)
	public void delete() {
		storage.delete(R1.getUuid());
		Assert.assertEquals(2, storage.size());
		Assert.assertEquals(R2, storage.load(R2.getUuid()));
		storage.load(R1.getUuid());
	}

	@org.junit.Test
	public void getAllSorted() {
		Resume[] resumes = new Resume[]{R1, R3, R2};
		Arrays.sort(resumes);
		Assert.assertTrue(Arrays.equals(resumes, storage.getAllSorted().toArray()));
//		Collection<Resume> allSortedResumes = storage.getAllSorted();
	}

	@org.junit.Test
	public void size() {
		Assert.assertEquals(3, storage.size());
	}
}