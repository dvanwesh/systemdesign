package com.system.social.twitter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

class Twitter2 {
    public static int count=0;
    int cap=0;
private static class Tweet{
    int tweetId;
    int timeStamp;
    public Tweet(int id){
        tweetId=id;
        timeStamp=count++;
    }
}
    Map<Integer,Stack<Tweet>> userTweet;
    Map<Integer,Set<Integer>> userMap;
    /** Initialize your data structure here. */
    public Twitter2() {
        cap=10;
        userTweet=new HashMap<>();
        userMap=new HashMap<>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) { 
        if(!userTweet.containsKey(userId)){
            userTweet.put(userId,new Stack<>());
        }
        userTweet.get(userId).push(new Tweet(tweetId));
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res=new LinkedList<>();
        if(userMap.get(userId)==null) return res;
        int count=cap;
        PriorityQueue<Tweet> pq=new PriorityQueue<>((a,b)->a.timeStamp-b.timeStamp);
        Set<Integer> users = userMap.get(userId);
        users.add(userId);
        for(int id:users){
            if(userTweet.get(id)==null) continue;
            Stack<Tweet> tStk=(Stack<Tweet>)userTweet.get(id).clone();
            int c=0;
            while(!tStk.isEmpty() && c!=cap){
                pq.offer(tStk.pop());
                c++;
                if(pq.size()>cap) pq.poll();
            }
        }
        while(!pq.isEmpty()) res.add(0,pq.poll().tweetId);
        return res;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(!userMap.containsKey(followerId)){
            userMap.put(followerId,new HashSet<>());
        }
        userMap.get(followerId).add(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(followerId==followeeId) return;
        if(userMap.containsKey(followerId)){
            userMap.get(followerId).remove(followeeId);
        }
    }
    public static void main(String[] args) {
		Twitter2 tw=new Twitter2();
		tw.postTweet(1,5);
		tw.follow(1, 2);
		tw.follow(2, 1);
		System.out.println(tw.getNewsFeed(2));
		tw.postTweet(2,6);
		System.out.println(tw.getNewsFeed(1));
		System.out.println(tw.getNewsFeed(2));
	}
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */