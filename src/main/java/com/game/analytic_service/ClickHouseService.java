package com.game.analytic_service;

import com.game.analytic_service.model.Analytics;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDateTime;

@Service
public class ClickHouseService {

    @Value("${clickhouse.url}")
    private String url;

    @Value("${clickhouse.user}")
    private String user;

    @Value("${clickhouse.password}")
    private String password;

    @PostConstruct
    public void printConfig() {
        System.out.println("[DEBUG] CLICKHOUSE_URL: " + url);
    }


    public void saveEvent(Analytics event) {
        event.setTimestamp(LocalDateTime.now()); // ensure timestamp is set

        System.out.println("=== [DEBUG] Saving event ===");
        System.out.println("event == null ? " + (event == null));
        System.out.println("event.getEventType() == null ? " + (event.getEventType() == null));
        System.out.println("EventType: " + event.getEventType());
        System.out.println("Page: " + event.getPage());
        System.out.println("SessionId: " + event.getSessionId());
        System.out.println("Timestamp: " + event.getTimestamp());
        System.out.println("Connecting to ClickHouse with URL: " + url);
        String insertSQL = "INSERT INTO analytics_events (eventType, pageUrl, sessionId, timestamp) VALUES (?, ?, ?, ?)";

        try (
                Connection conn = DriverManager.getConnection(url, user, password);

                PreparedStatement stmt = conn.prepareStatement(insertSQL)

        ) {
            stmt.setString(1, event.getEventType());
            stmt.setString(2, event.getPage());
            stmt.setString(3, event.getSessionId());
            stmt.setTimestamp(4, Timestamp.valueOf(event.getTimestamp()));
            stmt.executeUpdate();
            System.out.println("[DEBUG] Insert executed successfully!");
        } catch (SQLException e) {
            System.err.println("Failed to save analytics event: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
