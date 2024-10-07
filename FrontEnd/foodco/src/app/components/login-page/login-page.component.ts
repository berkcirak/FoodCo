import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css'] // `styleUrl` yerine `styleUrls` olmalı
})
export class LoginPageComponent {

  @Output() loginSuccess = new EventEmitter<void>();
  loginForm: FormGroup;
  loginObj: LoginPageComponent;

  constructor(private service: AuthService, private fb: FormBuilder,
              private router: Router, private http: HttpClient){
     }

  ngOnInit(){
    this.loginForm = this.fb.group({
      username : ['', Validators.required],
      password : ['', Validators.required]
    })
  }

  
  login() {
    console.log(this.loginForm.value);
    this.service.login(this.loginForm.value).subscribe(
      (jwtToken: string) => {
        console.log(jwtToken);
        if (jwtToken) {
          localStorage.setItem('JWT', jwtToken);
          this.loginSuccess.emit();
          this.router.navigateByUrl('/mainpage/homepage');
        }
      },
      (error) => {
        console.error('Login hatası:', error);
      }
    );
  } 

}
