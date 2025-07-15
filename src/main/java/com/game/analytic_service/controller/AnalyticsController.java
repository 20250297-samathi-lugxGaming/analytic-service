package com.game.analytic_service.controller;

import com.game.analytic_service.ClickHouseService;
import com.game.analytic_service.model.Analytics;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/analytics")
public class AnalyticsController {

    private final ClickHouseService clickHouseService;

    public AnalyticsController(ClickHouseService clickHouseService) {
        this.clickHouseService = clickHouseService;
    }

    @PostMapping
    public String captureEvent(@RequestBody Analytics event) {
        clickHouseService.saveEvent(event);

        return "Event saved";
    }
}
