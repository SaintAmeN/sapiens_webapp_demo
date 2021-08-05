package com.sda.sapiens.webapp;

import com.sda.sapiens.webapp.messaging.MessageSender;
import com.sda.sapiens.webapp.model.mapper.IStudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.mapstruct.factory.Mappers;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.jms.*;
import java.net.URI;

@Slf4j
@Stateless
public class BeanConfiguration {

    @Produces
    public IStudentMapper studentMapper() {
        return Mappers.getMapper(IStudentMapper.class);
    }

    // Message Bus - narzędzie do rozgłaszania wiadomości
    //             - ActiveMQ
    //             - Kafka
    //             - RabbitMQ
    //

    // consumer - klasa ktora pochłania i obsługuje
    //          - wypisz komunikat po otrzymaniu wiadomości
    //          - Wiadomość będzie typu STRING - będzie opakowana w obiekt

    // producer - klasa która produkuje wiadomości
    //          - MessageResource - REST API
    //          - nadanie wiadomości na topicu/kolejce
    @Produces
    public MessageSender messageSender() {
        try {
            BrokerService brokerService = BrokerFactory.createBroker(new URI("broker:(tcp://localhost:61616)"));
            brokerService.start();

            // Connection factory - do tworzenia połączeń
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            // Connection - jedno połączenie do którego...
            Connection connection = connectionFactory.createConnection();

            // ... otwieramy sesję
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // Tworzymy kolejkę na której będziemy "nadawać"
            Queue queue = session.createQueue("our_queue");

            // Tworzymy producer'a (to jest klasa JMS)
            MessageProducer producer = session.createProducer(queue);
            // rozpoczynamy połączenie
            connection.start();

            // towrzymy nasz obiekt do wysyłania
            return new MessageSender(producer, session, queue);
        } catch (Exception e) {
            log.info("Error creating sender: " + e);
            return null;
        }
    }

}
