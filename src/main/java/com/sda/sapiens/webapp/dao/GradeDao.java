package com.sda.sapiens.webapp.dao;

import com.sda.sapiens.webapp.model.Grade;

import javax.ejb.Stateless;

// deklarując stateless mówimy że obiekt ma mieć jedną instancję która ma się stworzyć
// tuż po załadowaniu projektu i ma być dodana do DI
@Stateless
public class GradeDao implements EntityDao<Grade> {
}
