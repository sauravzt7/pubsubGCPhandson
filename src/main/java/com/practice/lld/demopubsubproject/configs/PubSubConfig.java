package com.practice.lld.demopubsubproject.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Configuration
@PropertySource("classpath:application.properties")
@Component
public class PubSubConfig {

    @Value("${gcp.pubsub.project-name}")
    private String projectName;

    @Value("${gcp.pubsub.topic-name}")
    private String topicName;

    @Value("${gcp.pubsub.subscription-id}")
    private String subscriptionId;

    public String getTopicName() {
        return topicName;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }
}
