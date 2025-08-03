package com.game.analytic_service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Analytics {



    private String eventType;     // e.g., "page_view", "click", "scroll"
    @JsonProperty("pageUrl")
    private String page;
    private String sessionId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    //"timestamp": "2025-06-24T12:00:00"
    private LocalDateTime timestamp = LocalDateTime.now();

@JsonProperty("additional_data")
private Map<String, Object> additionalData;

@JsonProperty("additional_data")
public Map<String, Object> getAdditionalData() {
    return additionalData;
}

@JsonProperty("additional_data")
public void setAdditionalData(Map<String, Object> additionalData) {
    this.additionalData = additionalData;
}

//    private String userId;

    public String getEventType() {
        return eventType;
    }

    @JsonProperty("pageUrl")
    public String getPage() {
        return page;
    }

    public String getSessionId() {
        return sessionId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @JsonProperty("pageUrl")
    public void setPageUrl(String pageUrl) {
        this.page = pageUrl;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
