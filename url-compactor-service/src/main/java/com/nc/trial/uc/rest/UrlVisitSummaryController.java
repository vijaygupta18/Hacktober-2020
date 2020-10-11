package com.nc.trial.uc.rest;

import com.nc.trial.uc.domain.UrlVisitSummary;
import com.nc.trial.uc.services.UrlVisitAnalyticsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@Controller
@RequestMapping("/${rest.prefix}/stat")
@ComponentScan("com.nc.trial.uc.services")
public class UrlVisitSummaryController {

    private @Value("${max.top.visited.count}") int maxTopVisited = 10;

    private final UrlVisitAnalyticsService urlVisitAnalyticsService;

    public UrlVisitSummaryController(UrlVisitAnalyticsService urlVisitAnalyticsService) {
        this.urlVisitAnalyticsService = urlVisitAnalyticsService;
    }

    @RequestMapping(path = "/top", method= RequestMethod.GET)
    public @ResponseBody
    Collection<UrlVisitSummary> topMostVisited(@RequestParam(value = "count") int count) {
        if (count > maxTopVisited) {
            //TODO: Throw more elegant error
            throw new IllegalArgumentException("'count' value too high");
        }
        return urlVisitAnalyticsService.topVisited(count);
    }

    public static void main(String[] args) {
        int sum = 24 * 3600 * 1000;
        System.out.println(sum);
    }
}
