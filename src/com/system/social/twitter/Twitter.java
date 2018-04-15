package com.system.social.twitter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Twitter {
		static int timeStamp = 0;
		int maxFeed = 0;
		Map<Integer, User> userMap;
		/** Initialize your data structure here. */
		public Twitter() {
			userMap = new HashMap<>();
			maxFeed = 10;
		}

		/** Compose a new tweet. */
		public void postTweet(int userId, int tweetId) {
			if (!userMap.containsKey(userId)) {
				userMap.put(userId, new User(userId));
			}
			userMap.get(userId).post(tweetId,timeStamp++);
		}

		/**
		 * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in
		 * the news feed must be posted by users who the user followed or by the user
		 * herself. Tweets must be ordered from most recent to least recent.
		 */
		public List<Integer> getNewsFeed(int userId) {
			List<Integer> feed = new LinkedList<Integer>();
			if (!userMap.containsKey(userId))
				return feed;
			PriorityQueue<Tweet> pq = new PriorityQueue<>((a, b) -> a.time - b.time);
			Set<Integer> users = userMap.get(userId).followed;
			for (int user : users) {
				List<Tweet> list = userMap.get(user).userTweets;
				int c = 0;
				for (Tweet t : list) {
					pq.offer(t);
					c++;
					if (pq.size() > maxFeed)
						pq.remove();
					if (c > maxFeed)
						break;
				}
			}
			while (!pq.isEmpty())
				feed.add(0, pq.remove().tweetId);
			return feed;
		}

		/**
		 * Follower follows a followee. If the operation is invalid, it should be a
		 * no-op.
		 */
		public void follow(int followerId, int followeeId) {
			if (!userMap.containsKey(followerId))
				userMap.put(followerId, new User(followerId));
			if (!userMap.containsKey(followeeId))
				userMap.put(followeeId, new User(followeeId));
			userMap.get(followerId).follow(followeeId);
		}

		/**
		 * Follower unfollows a followee. If the operation is invalid, it should be a
		 * no-op.
		 */
		public void unfollow(int followerId, int followeeId) {
			if (!userMap.containsKey(followerId) || !userMap.containsKey(followeeId) || followerId == followeeId)
				return;
			userMap.get(followerId).unFollow(followeeId);
		}
	
}
