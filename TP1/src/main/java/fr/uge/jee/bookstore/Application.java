package fr.uge.jee.bookstore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config-bookstore.xml");

        Library library = context.getBean("library", Library.class);
        System.out.println(library.toString());
    }
}
/**
 *
 * Qu'observez vous ?
 * les constructeurs sont remplis automatiquement
 */