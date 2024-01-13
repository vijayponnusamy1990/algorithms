package com.example.algorithms.ratelimiter;


import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowRateLimiter{

    private Long maxWindowSizeInSeconds;
    private Long maxAllowedRequests;

    private Map<String, Queue<Long>> store;

    public SlidingWindowRateLimiter(Long maxWindowSizeInSeconds, Long maxAllowedRequests) {
        this.maxWindowSizeInSeconds = maxWindowSizeInSeconds;
        this.maxAllowedRequests = maxAllowedRequests;
        this.store = new ConcurrentHashMap<>();
    }
    public synchronized  boolean isAllow(String userId){
        if(Objects.isNull(userId)){
            return false;
        }
        Long currentTimeInSeconds = System.currentTimeMillis()/1000;
        Queue<Long> clientRequests = store.computeIfAbsent(userId, q -> new LinkedList<>());
        while (!clientRequests.isEmpty() && (currentTimeInSeconds - clientRequests.peek() > maxWindowSizeInSeconds)){
            clientRequests.poll();
        }
        if(clientRequests.size() < maxAllowedRequests){
            clientRequests.add(currentTimeInSeconds);
            return true;
        }
        return false;
    }
}
