package fr.uge.jee.bookstore;

import java.util.Objects;
import java.util.Set;

public class Library {
    private final Set<Book> books;

    public Library(Set<Book> books) {
        this.books = Objects.requireNonNull(books);
    };

    @Override
    public String toString(){
        return books.stream().map(Object::toString).toList().toString();
    }

}
