package com.dataart.javaschool.spring_mvc_demo.persistence;

import com.dataart.javaschool.spring_mvc_demo.persistence.entities.Book;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BooksRepository extends CrudRepository<Book, String> {
    List<Book> findBooksByName(String name);

    @Query("select b.isbn, b.name, b.author_id " +
            "from books b join authors a " +
            "on b.author_id = a.id and b.name = :bookName and a.name = :authorName")
    List<Book> findBooksByNameAndAuthor(String bookName, String authorName);

    @Modifying
    @Query("update books set name = :name, author_id = :authorId where isbn = :isbn")
    boolean updateBook(String isbn, String name, Long authorId);
}
