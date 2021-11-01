package com.dataart.javaschool.spring_mvc_demo.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.Set;

@EqualsAndHashCode
@Builder
@Data
@Table("authors")
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    Long id;
    String name;
    @MappedCollection(idColumn = "author_id")
    Set<Book> books;

    @PersistenceConstructor
    public Author(String name) {
        this.name=name;
    }
}
