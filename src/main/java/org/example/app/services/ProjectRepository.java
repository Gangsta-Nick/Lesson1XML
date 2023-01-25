package org.example.app.services;

import java.util.List;

public interface ProjectRepository<T> {
    List<T> retreiveAll();

    void store(T book);

    boolean removeItemById(Integer bookIdToRemove);

    boolean removeItemByAuthor(String bookByAuthor);

    boolean removeItemByTitle(String bookStringByTitle);

    boolean removeItemBySize(Integer bookSize);

}
