package com.dataart.javaschool.spring_mvc_demo.service;

import com.dataart.javaschool.spring_mvc_demo.model.AuthorAndBook;
import com.dataart.javaschool.spring_mvc_demo.model.BooksFilter;
import com.dataart.javaschool.spring_mvc_demo.persistence.AuthorsRepository;
import com.dataart.javaschool.spring_mvc_demo.persistence.BooksRepository;
import com.dataart.javaschool.spring_mvc_demo.persistence.entities.Author;
import com.dataart.javaschool.spring_mvc_demo.persistence.entities.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@RequiredArgsConstructor
@Service
public class BooksService {
    private final BooksRepository booksRepository;
    private final AuthorsRepository authorsRepository;

    public List<AuthorAndBook> findByFilter(BooksFilter booksFilter) {
        Collection<Book> books;
        if (isNotEmpty(booksFilter.getName()) && isNotEmpty(booksFilter.getAuthor())) {
            books = booksRepository.findBooksByNameAndAuthor(booksFilter.getName(), booksFilter.getAuthor());
        } else if (isNotEmpty(booksFilter.getName())) {
            books = booksRepository.findBooksByName(booksFilter.getName());
        } else if (isNotEmpty(booksFilter.getAuthor())) {
            final Author author = authorsRepository.findAuthorByName(booksFilter.getAuthor());
            books = author.getBooks();
        } else {
            books = (List<Book>) booksRepository.findAll();
        }
        final Iterable<Author> authors = authorsRepository.findAllById(getAuthorIds(books));
        return getAuthorAndBooks(books, authors);
    }

    public AuthorAndBook findByIsbn(String isbn) {
        final Optional<Book> book = booksRepository.findById(isbn);
        return book.map(b -> {
            final Optional<Author> author = authorsRepository.findById(b.getAuthorId());
            return new AuthorAndBook(isbn, b.getName(), author.map(a -> a.getName()).get());
        }).orElse(null);
    }

    public AuthorAndBook updateBook(AuthorAndBook updateTo) {
        final String authorName = updateTo.getAuthor();
        Author authorByName = authorsRepository.findAuthorByName(authorName);
        if (authorByName == null) {
            authorByName = authorsRepository.save(Author.builder().name(authorName).build());
        }
        boolean updated = booksRepository.updateBook(updateTo.getIsbn(), updateTo.getName(), authorByName.getId());
        if (updated) {
            return updateTo;
        }
        throw new RuntimeException("Unable to update book to: " + updateTo);
    }

    private List<AuthorAndBook> getAuthorAndBooks(Iterable<Book> books, Iterable<Author> authors) {
        List<AuthorAndBook> authorAndBooks = new ArrayList<>();
        Map<Long, String> authorMap = new HashMap<>();
        authors.forEach(a -> authorMap.put(a.getId(), a.getName()));
        books.forEach(b -> authorAndBooks.add(AuthorAndBook.builder()
                .author(authorMap.get(b.getAuthorId()))
                .isbn(b.getIsbn())
                .name(b.getName())
                .build()));
        return authorAndBooks;
    }

    private List<Long> getAuthorIds(Collection<Book> booksByName) {
        return booksByName.stream().map(Book::getAuthorId).collect(Collectors.toList());
    }
}
