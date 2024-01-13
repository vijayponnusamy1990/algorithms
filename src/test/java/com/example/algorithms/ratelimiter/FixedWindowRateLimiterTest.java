package com.example.algorithms.ratelimiter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FixedWindowRateLimiterTest {

    @Test
    public void isAllow() throws InterruptedException {
        FixedWindowRateLimiter rateLimiter = new FixedWindowRateLimiter(1, 3);
        Assertions.assertTrue(rateLimiter.isAllow("vijay"));
        Assertions.assertTrue(rateLimiter.isAllow("vijay"));
        Assertions.assertTrue(rateLimiter.isAllow("vijay"));
        Assertions.assertFalse(rateLimiter.isAllow("vijay"));
        Thread.sleep(2000);
        Assertions.assertTrue(rateLimiter.isAllow("vijay"));
        Assertions.assertTrue(rateLimiter.isAllow("vijay"));
    }
}
