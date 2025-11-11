package com.bajajfinserv.health.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bajajfinserv.health.dto.SolutionRequest;
import com.bajajfinserv.health.dto.WebhookRequest;
import com.bajajfinserv.health.dto.WebhookResponse;

@Service
public class ChallengeService {

    private static final Logger logger = LoggerFactory.getLogger(ChallengeService.class);

    private final RestTemplate restTemplate;

    @Value("${app.user.name}")
    private String userName;

    @Value("${app.user.regNo}")
    private String userRegNo;

    @Value("${app.user.email}")
    private String userEmail;

    @Value("${app.api.base-url}")
    private String baseUrl;

    @Value("${app.api.generate-webhook-path}")
    private String generateWebhookPath;

    public ChallengeService() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Generate webhook by calling the API
     */
    public WebhookResponse generateWebhook() {
        String url = baseUrl + generateWebhookPath;
        logger.info("Generating webhook at: {}", url);

        WebhookRequest request = new WebhookRequest(userName, userRegNo, userEmail);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<WebhookRequest> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<WebhookResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    WebhookResponse.class
            );

            WebhookResponse webhookResponse = response.getBody();
            if (webhookResponse != null) {
                logger.info("Webhook generated successfully");
                logger.info("Webhook URL: {}", webhookResponse.getWebhook());
                return webhookResponse;
            } else {
                throw new RuntimeException("Empty response received from webhook generation API");
            }
        } catch (Exception e) {
            logger.error("Error generating webhook: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to generate webhook", e);
        }
    }

    /**
     * Submit the SQL solution to the webhook URL
     */
    public void submitSolution(String webhookUrl, String accessToken, String sqlQuery) {
        logger.info("Submitting solution to: {}", webhookUrl);

        SolutionRequest request = new SolutionRequest(sqlQuery);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", accessToken);

        HttpEntity<SolutionRequest> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    webhookUrl,
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            logger.info("Solution submitted successfully");
            logger.info("Response Status: {}", response.getStatusCode());
            logger.info("Response Body: {}", response.getBody());
        } catch (Exception e) {
            logger.error("Error submitting solution: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to submit solution", e);
        }
    }
}
