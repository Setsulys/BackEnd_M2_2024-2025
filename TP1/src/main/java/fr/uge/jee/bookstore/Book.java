package fr.uge.jee.bookstore;

import java.util.Objects;
import java.util.Set;

public class Book {
    private final String title;
    private final long ref;

    public Book(String title,long ref){
        this.title =Objects.requireNonNull(title);
        this.ref= ref;
    };

    @Override
    public String toString(){
        return title+" : " + ref;
    }
}

