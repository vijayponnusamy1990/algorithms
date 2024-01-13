package com.example.algorithms.ratelimiter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SlidingWindowRateLimiterTest {

    @Test
    public void isAllow() throws InterruptedException {
        SlidingWindowRateLimiter rateLimiter = new SlidingWindowRateLimiter(1L, 3L);
        Assertions.assertTrue(rateLimiter.isAllow("vijay"));
        Assertions.assertTrue(rateLimiter.isAllow("vijay"));
        Assertions.assertTrue(rateLimiter.isAllow("vijay"));
        Assertions.assertFalse(rateLimiter.isAllow("vijay"));
        Assertions.assertFalse(rateLimiter.isAllow("vijay"));
        Thread.sleep(2000);
        Assertions.assertTrue(rateLimiter.isAllow("vijay"));
        Assertions.assertTrue(rateLimiter.isAllow("vijay"));
    }
}
