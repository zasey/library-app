package ru.zaseev.booknetwork.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.zaseev.booknetwork.models.Book;
import ru.zaseev.booknetwork.models.Person;

import java.util.List;

@Component
public class PersonDAO {

//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public PersonDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Transactional(readOnly = true)
//    public List<Person> index(){
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("SELECT p FROM Person p", Person.class).getResultList();
//    }
//
//    @Transactional(readOnly = true)
//    public Person read(int person_id){
//        Session session = sessionFactory.getCurrentSession();
//        return session.get(Person.class, person_id);
//    }
//
//    @Transactional(readOnly = true)
//    public List<Book> booksOfPerson(int person_id){
//        Session session = sessionFactory.getCurrentSession();
//        return session.get(Person.class, person_id).getBooks();
//    }
//
//    @Transactional
//    public void create(Person person){
//        Session session = sessionFactory.getCurrentSession();
//        session.save(person);
//    }
//
//    @Transactional
//    public void update(int person_id, Person updatedPerson){
//        Session session = sessionFactory.getCurrentSession();
//        Person person = session.get(Person.class, person_id);
//        person.setName(updatedPerson.getName());
//        person.setYear(updatedPerson.getYear());
//        person.setBooks(updatedPerson.getBooks());
//    }
//
//    @Transactional
//    public void delete(int person_id){
//        Session session = sessionFactory.getCurrentSession();
//        session.remove(session.get(Person.class, person_id));
//    }
}
