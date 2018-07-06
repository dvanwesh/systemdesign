package com.system.socialnetwork.graph;

import java.util.LinkedList;

public class PathNode {
	private Person person = null;
	private PathNode previousNode = null;

	public PathNode(Person p, PathNode prev) {
		person = p;
		previousNode = prev;
	}

	public Person getPerson() {
		return person;
	}

	public LinkedList<Person> collapse(boolean startWithRoot) {
		LinkedList<Person> path = new LinkedList<>();
		PathNode node = this;
		while (node != null) {
			if (startWithRoot) {
				path.add(node.person);
			} else {
				path.add(0, node.person);
			}
			node = node.previousNode;
		}
		return path;
	}
}
