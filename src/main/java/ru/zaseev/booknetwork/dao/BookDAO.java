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
public class BookDAO {

//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public BookDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Transactional(readOnly = true)
//    public List<Book> index(){
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("select b from Book b", Book.class)
//                .getResultList();
//    }
//
//    @Transactional(readOnly = true)
//    public Person personWhoTookTheBook(int bookId) {
//
//        Session session = sessionFactory.getCurrentSession();
//
//        return session.get(Book.class, bookId).getPerson();
//
//    }
//
//    @Transactional(readOnly = true)
//    public Book read(int book_id){
//
//        Session session = sessionFactory.getCurrentSession();
//
//        return session.get(Book.class, book_id);
//
//    }
//
//    @Transactional
//    public void setPersonId(int bookId, int personId){
//        Session session = sessionFactory.getCurrentSession();
//
//        Person person = session.get(Person.class, personId);
//        Book book = session.get(Book.class, bookId);
//        person.getBooks().add(book);
//        book.setPerson(person);
//
//    }
//
//    @Transactional
//    public void removePersonId(int bookId){
//
//        Session session = sessionFactory.getCurrentSession();
//
//        session.get(Book.class, bookId).setPerson(null);
//
//    }
//
//    @Transactional
//    public void create(Book book){
//
//        Session session = sessionFactory.getCurrentSession();
//
//        session.save(book);
//
//    }
//
//    @Transactional
//    public void update(int book_id, Book updatedBook){
//
//        Session session = sessionFactory.getCurrentSession();
//
//        Book book = session.get(Book.class,book_id);
//        book.setTitle(updatedBook.getTitle());
//        book.setAuthor(updatedBook.getAuthor());
//        book.setYear(updatedBook.getYear());
//        book.setPerson(updatedBook.getPerson());
//    }
//
//    @Transactional
//    public void delete(int book_id){
//
//        Session session = sessionFactory.getCurrentSession();
//
//        session.remove(session.get(Book.class, book_id));
//
//    }
}
