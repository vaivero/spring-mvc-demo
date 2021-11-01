package com.dataart.javaschool.spring_mvc_demo.persistence;

import com.dataart.javaschool.spring_mvc_demo.persistence.entities.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorsRepository extends CrudRepository<Author, Long> {
    Author findAuthorByName(String name);
}

