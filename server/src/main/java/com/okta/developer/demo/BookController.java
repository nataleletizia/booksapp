package com.okta.developer.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.HttpStatus;
import java.util.Optional;

@RestController
@RequestMapping("/books")
class BookController {
    private BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

	@Autowired
    public void setBookRepository(BookRepository repository) {
        this.repository = repository;
    }
	
    @RequestMapping(value = "/books", method= RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
    public Collection<Book> books() {
        return repository.findAll().stream()
                .collect(Collectors.toList());
    }
	
	@RequestMapping(value = "/books/{id}", method= RequestMethod.GET)
    public Book showBook(@PathVariable Long id, Book model){
       Book book = ((Optional<Book>)repository.findById(id)).get();
        return book;
    }
  
    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public ResponseEntity saveBook(@RequestBody Book book){
        repository.save(book);
        return new ResponseEntity("Book saved successfully", HttpStatus.OK);
    } 
}
