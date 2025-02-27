package com.practice.lld.demopubsubproject.publisher;

import com.google.api.core.ApiFuture;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import com.practice.lld.demopubsubproject.configs.PubSubConfig;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
public class Publisher {

    public static void publish(String projectId, String topicId, String message) throws IOException, ExecutionException, InterruptedException {
        TopicName topicName = TopicName.of(projectId, topicId);

        com.google.cloud.pubsub.v1.Publisher publisher = null;
        try {
            // Create a publisher instance with default settings bound to the topic
            publisher = com.google.cloud.pubsub.v1.Publisher.newBuilder(topicName).build();

            ByteString data = ByteString.copyFromUtf8(message);
            PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

            // Once published, returns a server-assigned message id (unique within the topic)
            ApiFuture<String> messageIdFuture = publisher.publish(pubsubMessage);
            String messageId = messageIdFuture.get();
            System.out.println("Published message ID: " + messageId);
        } finally {
            if (publisher != null) {
                // When finished with the publisher, shutdown to free up resources.
                publisher.shutdown();
                publisher.awaitTermination(1, TimeUnit.MINUTES);
            }
        }
    }
}