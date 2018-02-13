package main;

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
	}

//	private static void printInt(Number i) {
//		System.out.println("Number : " + i + " " + integer);
//	}

	private static void printInt(int i) {
		System.out.println("Int : " + i);
	}
}
