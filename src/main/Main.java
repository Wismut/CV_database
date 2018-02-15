package main;

import ru.javawebinar.webapp.model.Contact;
import ru.javawebinar.webapp.model.ContactType;
import ru.javawebinar.webapp.model.Link;

public class Main {

	private static Integer integer;

	public static void main(String[] args) {
		System.out.format("Hello %s!\n", args[0]);
		Car raceCar = new RaceCar();
		Car simpleCar = new SimpleCar();
		raceCar.getDescription();
		simpleCar.getDescription();
		System.out.println(Link.EMPTY);
		integer = 7;
		integer.intValue();
		printInt(integer);

		Contact c = new Contact(ContactType.PHONE, "12345");
		ContactType c2 = ContactType.ICQ;

//		System.out.println(c2 == ContactType.PHONE);

	}

//	private static void printInt(Number i) {
//		System.out.println("Number : " + i + " " + integer);
//	}

	private static void printInt(int i) {
		System.out.println("Int : " + i);
	}
}
