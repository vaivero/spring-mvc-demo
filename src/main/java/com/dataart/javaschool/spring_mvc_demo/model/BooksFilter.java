package com.dataart.javaschool.spring_mvc_demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BooksFilter {
    String name;
    String author;
}
