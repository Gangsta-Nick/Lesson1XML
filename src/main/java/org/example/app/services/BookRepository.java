package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class BookRepository implements ProjectRepository<Book> {
    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();

    @Override
    public List<Book> retreiveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(Book book) {
        book.setId(book.hashCode());
        logger.info("store new book: " + book);
        repo.add(book);
    }

    @Override
    public boolean removeItemById(Integer bookIdToRemove) {
        for (Book book : retreiveAll()) {
            if (book.getId().equals(bookIdToRemove)) {
                logger.info("remove book completed: " + book);
                return repo.remove(book);
            } else {
                logger.info("book don't found");
            }
        }
        return false;
    }

    @Override
    public boolean removeItemByAuthor(String bookStringByAuthor) {
        for (Book book : retreiveAll()) {
            if (book.getAuthor().equals(bookStringByAuthor)) {
                logger.info("remove book completed: " + book);
                repo.remove(book);
            } else {
                logger.info("book don't found");
            }
        }
        return false;
    }

    @Override
    public boolean removeItemByTitle(String bookStringByTitle) {
        for (Book book : retreiveAll()) {
            Pattern pattern = Pattern.compile(bookStringByTitle, Pattern.CASE_INSENSITIVE);
            String title = book.getTitle();
            String author = book.getAuthor();
            Matcher matcherTitle = pattern.matcher(title);
            Matcher matcherAuthor = pattern.matcher(author);
             if (matcherTitle.find()){
                logger.info("remove book by title completed: " + book);
                repo.remove(book);
            } else if (matcherAuthor.find()) {
                 logger.info("remove book by author completed: " + book);
                 repo.remove(book);
            } else {
                 logger.info("book don't found");
             }
        }
        return false;
    }

    @Override
    public boolean removeItemBySize(Integer bookSize) {
        for (Book book : retreiveAll()) {
            if (book.getSize().equals(bookSize)) {
                logger.info("remove book completed: " + book);
                repo.remove(book);
            } else {
                logger.info("book don't found");
            }
        }
        return false;
    }
}
