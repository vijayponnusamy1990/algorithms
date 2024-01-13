package com.example.algorithms.ratelimiter;

import java.util.concurrent.atomic.AtomicLong;

public class LeakyBucketRateLimiter {

    private Long capacity;
    private AtomicLong currentBucketSize;
    private Long ratePerSecond;
    private AtomicLong lastRequestedTimeInMili;

    public LeakyBucketRateLimiter(Long capacity, Long ratePerSecond) {
        this.capacity = capacity;
        this.ratePerSecond = ratePerSecond;
        this.currentBucketSize = new AtomicLong(0L);
        this.lastRequestedTimeInMili = new AtomicLong(System.currentTimeMillis());
    }

    public synchronized  boolean isAllow(){
        Long currentTimeInMili = System.currentTimeMillis();
        Long timeElapsed = currentTimeInMili - lastRequestedTimeInMili.getAndSet(currentTimeInMili);

        long leakedTokens = timeElapsed * ratePerSecond /1000;
        currentBucketSize.updateAndGet(bucketSize -> Math.max(0, Math.min(bucketSize+leakedTokens, capacity)));
        if(currentBucketSize.get() > 0){
            currentBucketSize.decrementAndGet();
            return true;
        }
        return false;
    }
}
