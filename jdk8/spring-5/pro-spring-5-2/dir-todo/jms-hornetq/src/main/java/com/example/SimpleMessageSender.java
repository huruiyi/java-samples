package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

@Component("messageSender")
public class SimpleMessageSender implements MessageSender {

    private static final Logger logger = LoggerFactory.getLogger(SimpleMessageSender.class);
    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendMessage(final String message) {
        jmsTemplate.setDeliveryDelay(500L);

        this.jmsTemplate.send(session -> {
            TextMessage jmsMessage = session.createTextMessage(message);
            logger.info(">>> Sending: {}", jmsMessage.getText());
            return jmsMessage;
        });
    }
}
