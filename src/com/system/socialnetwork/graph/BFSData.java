package com.system.socialnetwork.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BFSData {
	Queue<PathNode> toVisit=new LinkedList<>();
	Map<Integer, PathNode> visited=new HashMap<>();
	public BFSData(Person root) {
		PathNode sourcePath=new PathNode(root, null);
		toVisit.add(sourcePath);
		visited.put(root.getPersonId(), sourcePath);
	}
	public boolean isFinished() {
		return toVisit.isEmpty();
	}
}
