import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { CarListComponent } from './car-list/car-list.component';

import { MatButtonModule, MatCardModule, MatInputModule, MatListModule, MatToolbarModule, MatDatepickerModule, MatNativeDateModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CarEditComponent } from './car-edit/car-edit.component';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { OktaCallbackComponent, OktaAuthModule } from '@okta/okta-angular';
import { AuthInterceptor } from './shared/okta/auth.interceptor';
import { HomeComponent } from './home/home.component';
import { BookListComponent } from './book-list/book-list.component';
import { BookEditComponent } from './book-edit/book-edit.component';

const appRoutes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'car-list',
    component: CarListComponent
  },
  {
    path: 'car-add',
    component: CarEditComponent
  },
  {
    path: 'car-edit/:id',
    component: CarEditComponent
  },
  {
    path: 'book-list',
    component: BookListComponent
  },
  {
    path: 'book-add',
    component: BookEditComponent
  },
  {
    path: 'book-edit/:id',
    component: BookEditComponent
  },
  {
    path: 'implicit/callback',
    component: OktaCallbackComponent
  }
];

const config = {
  issuer: 'https://dev-624781.oktapreview.com/oauth2/default',
  redirectUri: 'http://localhost:4200/implicit/callback',
  clientId: '0oagzjpf9cSb08eS90h7'
};

@NgModule({
  declarations: [
    AppComponent,
    CarListComponent,
    CarEditComponent,
    HomeComponent,
    BookListComponent,
    BookEditComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
	MatDatepickerModule, MatNativeDateModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
    OktaAuthModule.initAuth(config)
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
