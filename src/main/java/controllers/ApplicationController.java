//
// * Copyright (C) 2013 the original author or authors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// *

package controllers;

import ninja.Result;
import ninja.Results;

import com.google.inject.persist.Transactional;

import java.util.*;

import entity.Book;

import javax.persistence.*;
import com.google.inject.*;

@Singleton
public class ApplicationController {

	@Inject
	Provider<EntityManager> entityManagerProvider;
	
    public Result index() {

        return Results.html();

    }
    
    public Result main() {
    	
//    	EntityManager entityManager = entityManagerProvider.get();
//    	List<Book> l=entityManager.createQuery("FROM Book").getResultList();
    	return Results.html();
    }
    
    public Result helloWorldJson() {
        
        SimplePojo simplePojo = new SimplePojo();
        simplePojo.content = "Hello World! Hello Json!";

        return Results.json().render(simplePojo);

    }
    
//    public Result MyName() {
//    	SimplePojo simplePojo = new SimplePojo();
//    	simplePojo.content = "Hello Sameer Bishnoi";
//    	return Results.json().render(simplePojo);
//    }
    
    public static class SimplePojo {

        public String content;
        
    }
    @Transactional 
    public Result PrintingBook() {
    	// printing all the books available in the database
    	EntityManager entityManager = entityManagerProvider.get();
    	List<Book> l=entityManager.createQuery("From Book").getResultList();
    	return Results.json().render(l);
    }
    
    @Transactional
    public Result newBook(Book book) {
    	// Adding a new book in the database
    	EntityManager entityManager = entityManagerProvider.get();
    	entityManager.persist(book);
    	return Results.json().render("{success:true}");
    }
    
    @Transactional
    public Result deleteBook(Book book) {
    	EntityManager entityManager = entityManagerProvider.get();
//    	List<Book> l=entityManager.createQuery("From Book where Book.name = " + book.getName()).getResultList();
//    	entityManager.remove(l.get(0));
    	entityManager.createNativeQuery("delete from Book where name= '"+book.getName()+"'").executeUpdate();
//    	entityManager.remove(book);
    	return Results.json().render(book);
    }
    
    @Transactional
    public Result editBook(Book book) {
    	EntityManager entityManager = entityManagerProvider.get();
    	Book b=entityManager.find(Book.class,book.getName());
    	b.setName(book.getName());
    	b.setAuthor(book.getAuthor());
    	b.setPrice(book.getPrice());
    	b.setDescription(book.getDescription());
    	return Results.json().render("{success:true}");
    }
    
//    public Result func2()
//    {
//    	// printing books by authors
//    	EntityManager entityManager = entityManagerProvider.get();
//    	List<Book> l=entityManager.createQuery("From Book where ").getResultList();
//    	return Results.json().render(l);
//    }
}
