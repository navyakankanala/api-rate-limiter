package com.example.ratelimiter.controller;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api")
public class RateLimiterController {
    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    private Bucket getBucket(String apiKey) {
        return cache.computeIfAbsent(apiKey, key ->
                Bucket.builder()
                        .addLimit(Bandwidth.classic(10, Refill.greedy(10, Duration.ofMinutes(1))))
                        .build()
        );
    }

    @GetMapping("/protected-resource")
    public String accessProtectedResource(@RequestHeader(value = "X-API-KEY") String apiKey) {
        Bucket bucket = getBucket(apiKey);
        return bucket.tryConsume(1) ? "Request successful!" : "Too many requests! Please wait.";
    }
}
