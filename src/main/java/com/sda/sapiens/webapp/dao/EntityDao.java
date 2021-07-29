package com.sda.sapiens.webapp.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface EntityDao<T> {
    default void saveOrUpdate(T entity) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();

            session.saveOrUpdate(entity);

            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    default List<T> getAll(Class<T> classT) {
        List<T> list = new ArrayList<>();
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.openSession()) {

            // Narzędzie do kreowania zapytania, do tworzenia query i budowania klauzuli 'where'
            CriteriaBuilder cb = session.getCriteriaBuilder();

            // Obiekt reprezentujący zapytanie o <typ generyczny>
            CriteriaQuery<T> criteriaQuery = cb.createQuery(classT);

            // reprezentuje tabelę 'Student' i tworzymy tą instancję żeby powiedzieć
            // "do jakiej tabeli chcemy wykonać zapytanie"
            Root<T> rootTable = criteriaQuery.from(classT);

            // wykonanie select'a z tabeli
            criteriaQuery.select(rootTable);

            // wywołujemy zapytanie, wyniki zbieramy do listy
            list.addAll(session.createQuery(criteriaQuery).list());
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return list;
    }


    default Optional<T> getById(Class<T> classT, Long id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.openSession()) {

            T entity = session.get(classT, id);

            return Optional.ofNullable(entity);
        }
    }

    // delete
    default void delete(Class<T> classT, Long id) {
        Optional<T> optionalEntity = getById(classT, id);

        if (optionalEntity.isPresent()) {
            deleteMyObject(optionalEntity.get());
        } else {
            System.err.println("Nie udało się odnaleźć instancji");
        }
    }

    default void deleteMyObject(T entity) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();

            session.delete(entity);

            transaction.commit();
        }
    }
}
