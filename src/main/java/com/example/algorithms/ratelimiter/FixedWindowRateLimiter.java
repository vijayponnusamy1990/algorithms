package com.example.algorithms.ratelimiter;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class FixedWindowRateLimiter{

    private int maxWindowSizeInSeconds;
    private long maxAllowedRequests;
    private Map<String, Window> store;

    public FixedWindowRateLimiter(int maxWindowSizeInSeconds, long maxAllowedRequests) {
        this.maxWindowSizeInSeconds = maxWindowSizeInSeconds;
        this.maxAllowedRequests = maxAllowedRequests;
        store = new ConcurrentHashMap<>();
    }
    public synchronized boolean isAllow(String userId){
        if(Objects.isNull(userId)){
            return false;
        }
        Long currentTimeInSeconds = System.currentTimeMillis()/ 1000;
        Window userWindow = store.get(userId);
        if(userWindow == null || (currentTimeInSeconds - userWindow.getStartTime() > maxWindowSizeInSeconds)){
            userWindow = new Window(currentTimeInSeconds, 0L);
        }
        userWindow.setRequestCount(userWindow.getRequestCount() + 1);
        store.put(userId, userWindow);
        if(userWindow.getRequestCount() <= maxAllowedRequests){
            return true;
        }
        return false;
    }
}

class Window{
    private Long startTime;
    private Long requestCount;
    public Window(Long startTime, Long requestCount){
        this.startTime = startTime;
        this.requestCount = requestCount;
    }
    public Long getStartTime() {
        return startTime;
    }
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }
    public Long getRequestCount() {
        return requestCount;
    }
    public void setRequestCount(Long requestCount) {
        this.requestCount = requestCount;
    }
}
