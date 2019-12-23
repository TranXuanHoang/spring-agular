import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppService } from '../app.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  title;
  greeting;

  constructor(private http: HttpClient, private app: AppService) {
    this.title = 'client';
    this.greeting = {};
  }

  ngOnInit() {
    if (this.app.authenticated) {
      this.http.get('resource').subscribe(data => this.greeting = data);
    }
  }

  authenticated() {
    return this.app.authenticated;
  }
}
