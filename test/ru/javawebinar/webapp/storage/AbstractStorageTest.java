package ru.javawebinar.webapp.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.ContactType;
import ru.javawebinar.webapp.model.Organization;
import ru.javawebinar.webapp.model.Resume;
import ru.javawebinar.webapp.model.SectionType;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorageTest {
	private Resume R1, R2, R3;

	protected IStorage storage;

	@BeforeClass
	public static void beforeClass() {
		// same as static block
	}

	@Before
	public void before() {
		R1 = new Resume("полное имя1", "location1");
		R1.addContact(ContactType.MAIL, "erfg@dfg.com");
		R1.addContact(ContactType.PHONE, "23453455");
		if (storage.isSectionSupported()) {
			R1.addObjective("Objective1");
			R1.addMultiTextSection(SectionType.ACHIEVEMENT, "Achievement1", "Achievement2");
			R1.addMultiTextSection(SectionType.QUALIFICATIONS, "Java", "SQL");
			R1.addOrganizationSection(SectionType.EXPERIENCE,
					new Organization("Organization11", "Org1",
							new Organization.Period(LocalDate.of(2005, Month.JANUARY, 1), Organization.Period.NOW, "position1", "content1"),
							new Organization.Period(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2")),
					new Organization("Organization12", "www.com"));
			R1.addOrganizationSection(SectionType.EDUCATION,
					new Organization("Organization11", "Org2",
							new Organization.Period(2005, Month.JANUARY, 2000, Month.DECEMBER, "position1", null),
							new Organization.Period(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2")),
					new Organization("Organization12", "www.com"));
		}
		R2 = new Resume("полное имя2", "location2");
		R2.addContact(ContactType.MAIL, "eredrfgedfg@dfg.com");
		R2.addContact(ContactType.PHONE, "2344564553455");
		R3 = new Resume("полное имя3", "location3");
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

	@Test(expected = WebAppException.class)
	public void saveAlreadyPresent() {
		storage.clear();
		storage.save(R1);
		storage.save(R1);
	}

	@org.junit.Test
	public void update() {
		R2.setFullName("new full name");
		R2.setLocation("loca");
		storage.update(R2);
		Assert.assertEquals(R2.getFullName(), storage.load(R2.getUuid()).getFullName());
		Assert.assertEquals(R2.getLocation(), storage.load(R2.getUuid()).getLocation());
	}

	@Test(expected = WebAppException.class)
	public void updateNotFound() {
		Resume resume = new Resume("dfgrd", "dfger", "fghedf");
		storage.update(resume);
	}

	@org.junit.Test
	public void load() {
		Resume resume = storage.load(R3.getUuid());
		Assert.assertEquals(R3.getLocation(), resume.getLocation());
		Assert.assertEquals(R3.getFullName(), resume.getFullName());
		Assert.assertEquals(R3.getUuid(), resume.getUuid());
	}

	@Test(expected = WebAppException.class)
	public void loadNotFound() {
		storage.load("retro");
	}

	@Test(expected = WebAppException.class)
	public void deleteNotFound() {
		storage.load("dummy");
	}

	@org.junit.Test
	public void delete() {
		storage.delete(R1.getUuid());
		Assert.assertEquals(2, storage.size());
		Assert.assertEquals(R2, storage.load(R2.getUuid()));
	}

	@org.junit.Test
	public void getAllSorted() {
		List<Resume> list = Arrays.asList(R1, R3, R2);
		list.sort(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
		Assert.assertEquals(list, new ArrayList<>(storage.getAllSorted()));
//		Resume[] resumes = new Resume[]{R1, R3, R2};
//		Arrays.sort(resumes);
//		Assert.assertTrue(Arrays.equals(resumes, storage.getAllSorted().toArray()));
	}

	@org.junit.Test
	public void size() {
		Assert.assertEquals(3, storage.size());
		storage.clear();
		Assert.assertEquals(0, storage.size());
	}
}
