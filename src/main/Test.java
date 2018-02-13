package main;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		List<Integer> integers = new ArrayList<>();
		integers.add(1);
		integers.add(2);
		integers.add(3);
		System.out.println(integers);
		changeList(integers);
		System.out.println(integers);
		int a = 5;
		Integer b = 7;
		printInt(a);
		printInteger(a);
		printInt(b);
		printInteger(b);
	}

	private static void changeList(List<Integer> integers) {
		integers.remove(1);
	}

	private static void printInt(int i) {
		System.out.println(i);
	}

	private static void printInteger(Integer i) {
		System.out.println(i);
	}
}
