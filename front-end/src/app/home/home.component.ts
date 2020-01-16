import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppService } from '../app.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  title: string;
  greeting: object;

  constructor(private http: HttpClient, private app: AppService) {
    this.title = 'client';
    this.greeting = {};
  }

  ngOnInit() {
    this.getNewMessage();
  }

  refreshMessage() {
    this.getNewMessage();
  }

  getNewMessage() {
    if (this.app.authenticated) {
      this.http.get('token').subscribe(data => {
        const token = data['token'];
        this.http
          .get('resource',
               { headers: new HttpHeaders().set('X-Auth-Token', token) })
          .subscribe(response => this.greeting = response);
      });
    }
  }
  // getNewMessage() {
  //   if (this.authenticated()) {
  //     this.http.get('resource')
  //       .subscribe(data => this.greeting = data);
  //   }
  // }

  authenticated() {
    return this.app.authenticated;
  }
}
