package ru.zaseev.booknetwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zaseev.booknetwork.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
