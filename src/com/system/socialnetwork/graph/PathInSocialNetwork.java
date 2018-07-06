package com.system.socialnetwork.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class PathInSocialNetwork {
	public static void main(String[] args) {

		int nPeople = 11;
		HashMap<Integer, Person> people = new HashMap<Integer, Person>();
		for (int i = 0; i < nPeople; i++) {
			Person p = new Person(i);
			people.put(i, p);
		}
		
		int[][] edges = {{1, 4}, {1, 2}, {1, 3}, {3, 2}, {4, 6}, {3, 7}, {6, 9}, {9, 10}, {5, 10}, {2, 5}, {3, 7}};
		
		for (int[] edge : edges) {
			Person source = people.get(edge[0]);
			source.addFriend(edge[1]);
			
			Person destination = people.get(edge[1]);
			destination.addFriend(edge[0]);
		}
		
		int i = 1;
		int j = 10;
		PathInSocialNetwork network=new PathInSocialNetwork();
		LinkedList<Person> path1 = network.findPathBiBFS(people, i, j);
		Tester.printPeople(path1);
	}
	/*
	 * search one level and return collision if any
	 */
	Person searchLevel(Map<Integer, Person> people, BFSData primary, BFSData secondary) {
		/*
		 * We only want to search one level at a time. Count how many nodes are
		 * currently in the primary's level and only do that many nodes. We'll continue
		 * to add nodes to the end.
		 */
		int size = primary.toVisit.size();
		for (int i = 0; i < size; i++) {
			/* Pull out first node. */
			PathNode pathNode = primary.toVisit.poll();
			int personId = pathNode.getPerson().getPersonId();
			/* Check if it's already been visited. */
			if (secondary.visited.containsKey(personId)) {
				return pathNode.getPerson();
			}
			/* Add friends to queue. */
			Person person = pathNode.getPerson();
			ArrayList<Integer> friends = person.getFriends();
			for (int friendId : friends) {
				if (!primary.visited.containsKey(friendId)) {
					Person friend = people.get(friendId);
					PathNode next = new PathNode(friend, pathNode);
					primary.visited.put(friendId, next);
					primary.toVisit.add(next);
				}
			}
		}
		return null;
	}
	public LinkedList<Person> findPathBiBFS(HashMap<Integer,Person> people,int source,int dest){
		BFSData sourceData=new BFSData(people.get(source));
		BFSData DestData=new BFSData(people.get(dest));
		while(!sourceData.isFinished() && !DestData.isFinished()) {
			Person collision=searchLevel(people, sourceData, DestData);
			/* Search out from source. */
			if(collision!=null) {
				return mergePaths(sourceData, DestData, collision.getPersonId());
			}
			/* Search out from Destination. */
			collision=searchLevel(people, DestData, sourceData);
			if(collision!=null) {
				return mergePaths(sourceData, DestData, collision.getPersonId());
			}
		}
		return null;
		
	}
	public LinkedList<Person> mergePaths(BFSData bfs1, BFSData bfs2, int conection) {
		PathNode end1 = bfs1.visited.get(conection);// end1 -> source
		PathNode end2 = bfs2.visited.get(conection);// end2 -> dest
		LinkedList<Person> path1 = end1.collapse(false); // forward: source -> connection
		LinkedList<Person> path2 = end2.collapse(true);// reverse: connection -> dest
		path2.removeFirst();
		path1.addAll(path2);
		return path1;
	}
}
