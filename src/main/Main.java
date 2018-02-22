package main;

import ru.javawebinar.webapp.model.*;

import java.lang.reflect.Field;

public class Main {

	private static Integer integer;

	public static void main(String[] args) {
		System.out.format("Hello %s!\n", args[0]);
		Car raceCar = new RaceCar();
		Car simpleCar = new SimpleCar();
		raceCar.getDescription();
		simpleCar.getDescription();
//		System.out.println(Link.EMPTY);
		integer = 7;
		integer.intValue();
		printInt(integer);

		Contact c = new Contact(ContactType.PHONE, "12345");
		ContactType c2 = ContactType.ICQ;

		Link l = new Link();

		System.out.println(l instanceof Object);

		System.out.println(l.getClass().isInstance(Link.class));
		System.out.println(Link.class.isInstance(l));
		System.out.println(Link.class.isInstance(new Link()));

		String a1 = "Ja" + "va";
		String a2 = "Java";
		System.out.println(a1 == a2);

		try {
			Field url = l.getClass().getDeclaredField("url");
			url.setAccessible(true);
			url.set(l, "efge2");
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		System.out.println(l.getUrl());

//		System.out.println(c2 == ContactType.PHONE);

	}

//	private static void printInt(Number i) {
//		System.out.println("Number : " + i + " " + integer);
//	}

	private static void printInt(int i) {
		System.out.println("Int : " + i);
	}
}
