package com.practice.lld.demopubsubproject;

import com.practice.lld.demopubsubproject.configs.PubSubConfig;
import com.practice.lld.demopubsubproject.messagecreators.MessageCreator;
import com.practice.lld.demopubsubproject.publisher.Publisher;
import com.practice.lld.demopubsubproject.services.PubSubService;
import com.practice.lld.demopubsubproject.subscriber.ISubscriber;
import com.practice.lld.demopubsubproject.subscriber.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class DemoPubSubProjectApplication {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(DemoPubSubProjectApplication.class, args);

        PubSubConfig pubsubConfig = context.getBean(PubSubConfig.class);

        Publisher publisher = new Publisher();
        Subscriber subscriber = new Subscriber();

        List<Subscriber> subscriberList = new ArrayList<>();
        subscriberList.add(subscriber);

        MessageCreator messageCreator = new MessageCreator();
        String message = messageCreator.createARandomMessage();
        PubSubService service = new PubSubService(publisher, subscriberList);
        service.publish(pubsubConfig.getProjectName(), pubsubConfig.getTopicName(), message);
        service.notifySubscribers(pubsubConfig.getProjectName(), pubsubConfig.getSubscriptionId());

    }

}
