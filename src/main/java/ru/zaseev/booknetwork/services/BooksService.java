package ru.zaseev.booknetwork.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zaseev.booknetwork.models.Book;
import ru.zaseev.booknetwork.models.Person;
import ru.zaseev.booknetwork.repositories.BooksRepository;
import ru.zaseev.booknetwork.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;

    public BooksService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> index(){
        return booksRepository.findAll();
    }

    public List<Book> pageableIndex(int page, int booksPerPage){
        return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    public List<Book> sortIndex(){
        return booksRepository.findAll(Sort.by("year"));
    }

    public List<Book> sortPageableIndex(int page, int booksPerPage){
        return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
    }

    public Book read(int id){
        Optional<Book> foundPerson = booksRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Book book){
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook){
        updatedBook.setBookId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id){
        booksRepository.deleteById(id);
    }

    public Person personWhoTookTheBook(int id) {
        return read(id).getPerson();
    }

    @Transactional
    public void setPersonId(int bookId, int personId) {
        Book book = booksRepository.findById(bookId).orElse(null);
        Person person = peopleRepository.findById(personId).orElse(null);
        person.getBooks().add(book);
        book.setPerson(person);
    }

    @Transactional
    public void removePersonId(int bookId) {
        Book book = booksRepository.findById(bookId).orElse(null);
        book.getPerson().getBooks().remove(book);
        book.setPerson(null);
    }

    public Optional<Book> findByTitleStartingWith(String str) {
        return booksRepository.findBookByTitleStartingWith(str);
    }
}
