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
}
