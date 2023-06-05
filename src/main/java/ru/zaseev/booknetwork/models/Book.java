package ru.zaseev.booknetwork.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person person;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(max = 50, message = "Длина названия не должна превышать 50 символов")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(max = 100, message = "Длина ФИО не должна превышать 100 символов")
    @Pattern(regexp = "[А-Я][а-я]+ [А-Я][а-я]+", message = "ФИ должно быть в формате: Имя Отчество")
    @Column(name = "author")
    private String author;

    @Min(value = 1900, message = "Год выпуска должен быть больше чем 1900")
    @Max(value = 2016, message = "Год выпуска должен быть меньше чем 2016")
    @NotNull(message = "Поле не можеть быть пустым")
    @Column(name = "year")
    private int year;

    @Column(name="date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Transient
    private boolean overdue;

    public Book() {
    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Book(String title, String author, int year, Person person) {
        this.person = person;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookId == book.bookId && year == book.year && title.equals(book.title) && author.equals(book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, title, author, year);
    }
}
