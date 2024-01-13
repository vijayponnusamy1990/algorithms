package com.example.algorithms.ratelimiter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TokenBucketRateLimiterTest {

    @Test
    public void isAllow() throws InterruptedException {
        TokenBucketRateLimiter rateLimiter = new TokenBucketRateLimiter(4L, 1L);
        Thread.sleep(2000);
        Assertions.assertTrue(rateLimiter.isAllow());
        Assertions.assertTrue(rateLimiter.isAllow());
        Assertions.assertTrue(rateLimiter.isAllow());
        Assertions.assertFalse(rateLimiter.isAllow());
        Assertions.assertFalse(rateLimiter.isAllow());
        Thread.sleep(2000);
        Assertions.assertTrue(rateLimiter.isAllow());
        Assertions.assertTrue(rateLimiter.isAllow());
    }
}
