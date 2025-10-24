package org.example.jpa;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookMain {
    private static void find(){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try{
            Author author = em.find(Author.class, 1L);
            log.info("Author Name: {}", author.getName());
            System.out.println("Author Name: " + author.getName());

            log.info("Book List size : {}", author.getBooks().size());

//            Book book = em.find(Book.class, 1L);
//            log.info("Book title: {}", book.getTitle());
//
//
//            log.info("Book author: {}", book.getAuthor());


        }finally {
            em.close();
        }
    }
    public static void main(String[] args) {
        find();
    }
}
