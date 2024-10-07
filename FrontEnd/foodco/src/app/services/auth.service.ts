import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { LoginPageComponent } from '../components/login-page/login-page.component';

const apiUrl = ['http://localhost:8080/'];

@Injectable({
  providedIn: 'root'
})
export class AuthService {

 
  constructor( private router: Router, private http: HttpClient){}
  signup(signupRequest: any): Observable<any>{
    return this.http.post(apiUrl + "member/register", signupRequest)
  }
  
  login(loginRequest: any): Observable<any>{
    return this.http.post('http://localhost:8080/member/login', loginRequest, {responseType: 'text'})
  }
  
  

}
