package com.practice.lld.demopubsubproject.services;

import com.practice.lld.demopubsubproject.publisher.Publisher;
import com.practice.lld.demopubsubproject.subscriber.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class PubSubService {
    private final Publisher publisher;
    private final List<Subscriber> subscribers;

    @Autowired
    public PubSubService(Publisher publisher, List<Subscriber> subscribers) {
        this.publisher = publisher;
        this.subscribers = subscribers;
    }

    public void publish(String projectId, String topicId, String message) throws IOException, ExecutionException, InterruptedException {
        publisher.publish(projectId, topicId, message);
    }

    public void notifySubscribers(String projectId, String subId) {
        for (Subscriber subscriber : subscribers) {
            subscriber.onMessage(projectId, subId);
        }
    }
}
