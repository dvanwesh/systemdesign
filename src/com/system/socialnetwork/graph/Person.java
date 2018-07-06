package com.system.socialnetwork.graph;

import java.util.ArrayList;

public class Person {
	private int personId;
	private String firstName;
	private String lastName;
	private ArrayList<Integer> friends = new ArrayList<>();

	public Person(int id) {
		setPersonId(id);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void addFriend(int id) {
		friends.add(id);
	}

	/**
	 * @return the friends
	 */
	public ArrayList<Integer> getFriends() {
		return friends;
	}

	/**
	 * @param friends
	 *            the friends to set
	 */
	public void setFriends(ArrayList<Integer> friends) {
		this.friends = friends;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

}
