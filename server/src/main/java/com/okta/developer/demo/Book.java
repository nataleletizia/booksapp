package com.okta.developer.demo;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;

import java.time.LocalDate; 



@Entity
@Data
public class Book {
    @Id @GeneratedValue
    private Long id;
    private @NonNull String author;
	private @NonNull String title;
	private @NonNull LocalDate published;
	private String notes;
	
	public Book() {}
	
	public Book(String anAuthor, String aTitle, LocalDate publishingDate, String aNote) {
		author = anAuthor;
		title = aTitle;
		published = publishingDate;
		notes = aNote;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String aTitle) {
		title = aTitle;
	}
	
	public void setAuthor(String anAuthor) {
		author = anAuthor;
	}
	
	public void setPublished(LocalDate publishingDate) {
		published = publishingDate;
	}
	
	public void setNotes(String aNote) {
		notes = aNote;
	}
}