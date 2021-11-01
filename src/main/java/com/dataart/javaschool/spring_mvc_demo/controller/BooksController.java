package com.dataart.javaschool.spring_mvc_demo.controller;

import com.dataart.javaschool.spring_mvc_demo.model.AuthorAndBook;
import com.dataart.javaschool.spring_mvc_demo.model.BooksFilter;
import com.dataart.javaschool.spring_mvc_demo.service.BooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BooksController {

    private final BooksService booksService;

    @GetMapping(value = {"/", "/index", "/books"})
    public String index(@ModelAttribute BooksFilter booksFilter, Model model) {
        List<AuthorAndBook> books = booksService.findByFilter(booksFilter);
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping(value = "/book/{id}")
    public String getBook(@PathVariable String id, Model model) {
        AuthorAndBook book = booksService.findByIsbn(id);
        model.addAttribute("book", book);
        return "book";
    }

    @PostMapping(value = "/update/{id}")
    public String updateBook(@ModelAttribute AuthorAndBook book, Model model, @PathVariable String id){
        final AuthorAndBook updatedBook = booksService.updateBook(book);
        return "redirect:/book/" + id;
    }
}
