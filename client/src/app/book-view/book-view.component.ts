import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from '../shared/book/book.service';
import { GiphyService } from '../shared/giphy/giphy.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-book-view',
  templateUrl: './book-view.component.html',
  styleUrls: ['./book-view.component.css']
})
export class BookViewComponent implements OnInit, OnDestroy {
  book: any = {};

  sub: Subscription;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private bookService: BookService,
              private giphyService: GiphyService) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.bookService.get(id).subscribe((book: any) => {
          if (book) {
            this.book = book;
            this.book.href = book._links.self.href;
            this.giphyService.get(book.title).subscribe(url => book.giphyUrl = url);
          } else {
            console.log(`Book with id '${id}' not found, returning to list`);
            this.gotoList();
          }
        });
      }
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  gotoList() {
    this.router.navigate(['/book-list']);
  }

  save(form: NgForm) {
    this.bookService.save(form).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

  remove(href) {
    this.bookService.remove(href).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }
}