package com.bajajfinserv.health.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bajajfinserv.health.dto.WebhookResponse;
import com.bajajfinserv.health.service.ChallengeService;
import com.bajajfinserv.health.service.SqlQueryService;

@Component
public class ChallengeRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(ChallengeRunner.class);

    private final ChallengeService challengeService;
    private final SqlQueryService sqlQueryService;

    public ChallengeRunner(ChallengeService challengeService, SqlQueryService sqlQueryService) {
        this.challengeService = challengeService;
        this.sqlQueryService = sqlQueryService;
    }

    @Override
    public void run(String... args) {
        logger.info("=== Starting Bajaj Finserv Health Challenge ===");

        try {
            // Step 1: Generate webhook
            logger.info("Step 1: Generating webhook...");
            WebhookResponse webhookResponse = challengeService.generateWebhook();

            if (webhookResponse == null || webhookResponse.getWebhook() == null || webhookResponse.getAccessToken() == null) {
                logger.error("Failed to generate webhook - invalid response");
                return;
            }

            logger.info("Webhook generated successfully!");

            // Step 2: Get SQL solution
            logger.info("Step 2: Generating SQL solution...");
            String sqlQuery = sqlQueryService.getSqlQuery();
            logger.info("SQL Query: {}", sqlQuery);

            // Step 3: Submit solution
            logger.info("Step 3: Submitting solution...");
            challengeService.submitSolution(
                    webhookResponse.getWebhook(),
                    webhookResponse.getAccessToken(),
                    sqlQuery
            );

            logger.info("=== Challenge completed successfully! ===");

        } catch (Exception e) {
            logger.error("Challenge execution failed: {}", e.getMessage(), e);
        }
    }
}
