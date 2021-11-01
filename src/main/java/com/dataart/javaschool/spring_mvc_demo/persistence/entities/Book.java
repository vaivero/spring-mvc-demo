package com.dataart.javaschool.spring_mvc_demo.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@EqualsAndHashCode
@Builder
@Data
@Table("books")
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    String isbn;
    String name;
    Long authorId;
}
