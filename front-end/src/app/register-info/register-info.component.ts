import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppService } from '../app.service';

@Component({
  selector: 'app-register-info',
  templateUrl: './register-info.component.html',
  styleUrls: ['./register-info.component.css']
})
export class RegisterInfoComponent implements OnInit {

  registerInfo: object;
  
  constructor(private http: HttpClient, private app: AppService) {
    this.registerInfo = {};
  }

  ngOnInit() {
    this.getRegistrationInfo();
  }

  refreshInfo() {
    this.getRegistrationInfo();
  }

  getRegistrationInfo() {
    if (this.authenticated()) {
      const id = Math.floor(Math.random() * 1000) + 1;
      // Get info
      this.http.get(`register/info/${id}`)
        .subscribe(data => this.registerInfo = data);
    }
  }

  authenticated() {
    return this.app.authenticated;
    //return true;
  }

}
