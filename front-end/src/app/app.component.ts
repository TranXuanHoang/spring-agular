import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

// import 'rxjs/add/operator/finally';
// https://stackoverflow.com/questions/51192925/angular-how-to-call-finally-with-rxjs-6
// finally was renamed to finalize and you'll use it inside pipe() among other operators.
import { from } from 'rxjs';
import { catchError, finalize } from 'rxjs/operators';

import { AppService } from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
/*export class AppComponent {

  constructor(private http: HttpClient, private router: Router, private app: AppService) {
    // TODO
    // this.app.authenticate(undefined, undefined);
  }

  logout() {
    console.log('Logout');

    this.http.post('logout', {}).pipe(
      catchError((err, caught) => {
        // TODO - Handle error
        console.error(">>>Error: " + err);
        throw err;
      }),
      finalize(() => {
        // Forcely logout
        this.app.authenticated = false;
        this.router.navigateByUrl('/login');
      })
    ).subscribe();
  }

  authenticated() {
    return this.app.authenticated;
  }
}*/

export class AppComponent {
  authenticated = false;
  greeting = {};

  constructor(private http: HttpClient) {
    this.authenticate();
  }

  authenticate() {
    this.http.get('user').subscribe(response => {
      if (response['name']) {
        this.authenticated = true;
        console.log(response);
        this.http.get('resource').subscribe(data => this.greeting = data);
      } else {
        this.authenticated = false;
      }
    }, error => { this.authenticated = false; });
  }

  logout() {
    this.http.post('logout', {}).pipe(
      catchError((err, caught) => {
        // TODO - Handle error
        console.error(">>>Error: " + err);
        throw err;
      }),
      finalize(() => {
        // Forcely logout
        this.authenticated = false;
      })
    ).subscribe();
  }
}