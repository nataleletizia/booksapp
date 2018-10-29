import { Component, OnInit } from '@angular/core';
import { BookService } from '../shared/book/book.service';
import { GiphyService } from '../shared/giphy/giphy.service';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {

  books: Array<any>;

  constructor(private bookService: BookService, private giphyService: GiphyService) { }

  ngOnInit() {
    this.bookService.getAll().subscribe(data => {
      this.books = data;
	  for (const book of this.books) {
        this.giphyService.get(book.title).subscribe(url => book.giphyUrl = url);
      }
    });
  }

}
