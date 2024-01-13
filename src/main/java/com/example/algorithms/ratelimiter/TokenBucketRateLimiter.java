package com.example.algorithms.ratelimiter;

import java.util.concurrent.atomic.AtomicLong;

public class TokenBucketRateLimiter {

    private Long tokenCapacity;
    private AtomicLong currentTokenCount;
    private Long tokenRefreshIntervalInSeconds;

    private Long lastTokenRefreshTimeInSeconds;

    public TokenBucketRateLimiter(Long tokenCapacity, Long tokenRefreshIntervalInSeconds) {
        this.tokenCapacity = tokenCapacity;
        this.tokenRefreshIntervalInSeconds = tokenRefreshIntervalInSeconds;
        this.currentTokenCount = new AtomicLong(0);
        this.lastTokenRefreshTimeInSeconds = System.currentTimeMillis()/1000;
    }

    public synchronized boolean isAllow(){
        refillToken();
        if(currentTokenCount.get() > 0){
            currentTokenCount.decrementAndGet();
            return true;
        }
        return false;
    }
    public void refillToken(){
        Long currentTimeInSeconds = System.currentTimeMillis()/ 1000;
        Long timeElapsed =   currentTimeInSeconds  - lastTokenRefreshTimeInSeconds;
        Long tokensToAdd = timeElapsed / tokenRefreshIntervalInSeconds;
        Long currentToken = currentTokenCount.get();
        if( tokensToAdd > 0){
            lastTokenRefreshTimeInSeconds = currentTimeInSeconds;
            currentTokenCount.getAndUpdate( x -> Math.min(tokenCapacity, currentToken + tokensToAdd));
        }
    }
}
