package com.example.algorithms.ratelimiter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LeakyBucketRateLimiterTest {

    @Test
    public void isAllow() throws InterruptedException {
        LeakyBucketRateLimiter rateLimiter = new LeakyBucketRateLimiter(2L,1L);
        Thread.sleep(2000);
        Assertions.assertTrue(rateLimiter.isAllow());
        Assertions.assertTrue(rateLimiter.isAllow());
    }
}
