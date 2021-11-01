package com.dataart.javaschool.spring_mvc_demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor
public class AuthorAndBook {
    String isbn;
    String name;
    String author;
}
