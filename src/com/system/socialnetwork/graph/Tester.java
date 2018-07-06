package com.system.socialnetwork.graph;

import java.util.LinkedList;

public class Tester {
	public static void printPeople(LinkedList<Person> path) {
		if (path == null) {
			System.out.println("No path");
		} else {
			for (Person p : path) {
				System.out.print(p.getPersonId()+"-->");
			}
		}
	}
}
