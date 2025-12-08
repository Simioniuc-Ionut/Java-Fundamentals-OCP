package org.example.sq.part1.SmartHomeController.services;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.time.Instant;
import java.util.UUID;

@Component
@RequestScope
public class RequestContext {
    private final String requestId = UUID.randomUUID().toString();
    private final Instant startedAt = Instant.now();
    private String clientIp;
    private String userAgent;

    public String getRequestId() { return requestId; }
    public Instant getStartedAt() { return startedAt; }
    public String getClientIp() { return clientIp; }
    public void setClientIp(String clientIp) { this.clientIp = clientIp; }
    public String getUserAgent() { return userAgent; }
    public void setUserAgent(String userAgent) { this.userAgent = userAgent; }
}

