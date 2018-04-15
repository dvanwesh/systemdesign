package com.system.social.twitter;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class User {
	int userId;
	Set<Integer> followed;
	List<Tweet> userTweets;

	public User(int id) {
		userId = id;
		followed = new HashSet<Integer>();
		userTweets = new LinkedList<Tweet>();
		follow(id);
	}

	public void follow(int id) {
		followed.add(id);
	}

	public void unFollow(int id) {
		followed.remove(id);
	}

	public void post(int tweetId,int timeStamp) {
		userTweets.add(0, new Tweet(tweetId,timeStamp));
	}
}
