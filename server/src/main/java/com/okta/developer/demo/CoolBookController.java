package com.okta.developer.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
class CoolBookController {
    private BookRepository repository;

    public CoolBookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/cool-books")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<Book> coolBooks() {
        return repository.findAll().stream()
                .filter(this::isCool)
                .collect(Collectors.toList());
    }

    private boolean isCool(Book book) {
        return !book.getTitle().equals("AMC Gremlin") &&
                !book.getTitle().equals("Triumph Stag") &&
                !book.getTitle().equals("Ford Pinto") &&
                !book.getTitle().equals("Yugo GV");
    }
}