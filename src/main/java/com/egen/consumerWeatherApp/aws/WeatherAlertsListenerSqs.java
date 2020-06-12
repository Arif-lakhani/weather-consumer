package com.egen.consumerWeatherApp.aws;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.egen.consumerWeatherApp.model.SQSMessage;
import com.egen.consumerWeatherApp.model.Weatheralert;
import com.egen.consumerWeatherApp.service.AlertService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class WeatherAlertsListenerSqs {

    @Value("${sqs.url}")
    private String sqsUrl;

    @Value("${cloud.aws.credentials.accessKey}")
    private String awsAccessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String awsSecretKey;

    @Value("${cloud.aws.region.static}")
    private String awsRegion;

    private ObjectMapper objectMapper ;

    private AlertService alertService;

    private AmazonSQS amazonSQS;

    @Autowired
    public WeatherAlertsListenerSqs(@Value("${sqs.url}") String sqsUrl,
                                    @Value("${cloud.aws.credentials.accessKey}") String awsAccessKey,
                                    @Value("${cloud.aws.credentials.secretKey}") String awsSecretKey,
                                    @Value("${cloud.aws.region.static}")String awsRegion,
                                    ObjectMapper objectMapper,
                                    AlertService alertService,
                                    AmazonSQS amazonSQS){
        this.alertService=alertService;
        this.amazonSQS=amazonSQS;
        this.sqsUrl=sqsUrl;
        this.awsAccessKey=awsAccessKey;
        this.awsSecretKey=awsSecretKey;
        this.awsRegion=awsRegion;
        this.objectMapper=objectMapper;
    }

    @PostConstruct
    private void postConstructor() {


        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(awsAccessKey, awsSecretKey)
        );

        this.amazonSQS = AmazonSQSClientBuilder.standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion(awsRegion)
                .build();
    }

    public void startListeningToMessages() throws JsonProcessingException {

        final ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(sqsUrl)
                .withMaxNumberOfMessages(1)

                .withWaitTimeSeconds(3);

        while (true) {

            final List<Message> messages = amazonSQS.receiveMessage(receiveMessageRequest).getMessages();

            for (Message messageObject : messages) {
                String message = messageObject.getBody();
                // this is processing
                SQSMessage sqsMessage = objectMapper.readValue(message,SQSMessage.class);
                Weatheralert weatheralert = objectMapper.readValue(sqsMessage.getMessage(), Weatheralert.class);
                System.out.println(weatheralert);

                alertService.addAlerts(weatheralert);
                deleteMessage(messageObject);
            }
        }
    }

    private void deleteMessage(Message messageObject) {

        final String messageReceiptHandle = messageObject.getReceiptHandle();
        amazonSQS.deleteMessage(new DeleteMessageRequest(sqsUrl, messageReceiptHandle));

    }
}