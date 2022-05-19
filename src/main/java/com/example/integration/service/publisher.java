package com.example.integration.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class publisher {


    public void pub(String message) throws IOException, ExecutionException, InterruptedException {
        //System.out.println("publisher sending");
        TopicName topicName = TopicName.of("exalted-cogency-346615", "publisher-in");
        Publisher publisher = null;
        try{

            publisher = Publisher.newBuilder(topicName).build();
            PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(ByteString.copyFromUtf8(message)).build();
            publisher.publish(pubsubMessage);
//            ApiFuture<String> messageIdFuture = publisher.publish(pubsubMessage);
//            String messageId = messageIdFuture.get();
            //System.out.println("Published message ID: " + messageId);

        }
        finally {
            if (publisher != null) {
                // When finished with the publisher, shutdown to free up resources.
                publisher.shutdown();
                publisher.awaitTermination(1, TimeUnit.MINUTES);
            }
        }
        //System.out.println("Publisher sent");
    }
}