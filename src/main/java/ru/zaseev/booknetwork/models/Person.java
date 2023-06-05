package ru.zaseev.booknetwork.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(max = 100, message = "Длина ФИО не должна превышать 100 символов")
    @Pattern(regexp = "[А-Я][а-я]+ [А-Я][а-я]+ [А-Я][а-я]+", message = "ФИО должно быть в формате: Фамилия Имя Отчество")
    @Column(name = "name")
    private String name;

    @Min(value = 1800, message = "Год рождения должен быть больше чем 1800")
    @Max(value = 2023, message = "Год рождения должен быть меньше чем 2023")
    @NotNull(message = "Поле не можеть быть пустым")
    @Column(name = "year")
    private int year;

    @OneToMany(mappedBy = "person")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Book> books;

    public Person() {
    }

    public Person(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public Person(String name, int year, List<Book> books) {
        this.name = name;
        this.year = year;
        this.books = books;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return personId == person.personId && year == person.year && name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, name, year);
    }
}
