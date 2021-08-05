package com.sda.sapiens.webapp;

import com.sda.sapiens.webapp.model.mapper.IStudentMapper;
import org.mapstruct.factory.Mappers;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;

@Stateless
public class BeanConfiguration {

    @Produces
    public IStudentMapper studentMapper(){
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

}
