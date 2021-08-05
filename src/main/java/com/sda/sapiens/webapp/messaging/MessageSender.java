package com.sda.sapiens.webapp.messaging;

import javax.jms.*;

public class MessageSender {
    private MessageProducer messageProducer;
    private Session session; // do generowania wiad.
    private Queue queue;

    public MessageSender(MessageProducer messageProducer, Session session, Queue queue) {
        this.messageProducer = messageProducer;
        this.session = session;
        this.queue = queue;
    }

    public void send(String what){
        try {
            // stworzenie wiadomości
            Message message = session.createTextMessage(what);

            // wysłanie jej do "przetworzenia"
            messageProducer.send(message);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
