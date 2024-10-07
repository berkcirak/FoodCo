import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CommonModule
  ],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {

    signupForm: FormGroup | undefined;

    constructor(
      private service: AuthService,
      private fb: FormBuilder
    ){}

      ngOnInit(){
        this.signupForm = this.fb.group({
          firstName : ['', Validators.required],
          lastName : ['', Validators.required],
          email : ['', Validators.required, Validators.email],
          username : ['', Validators.required],
          password : ['', Validators.required],
          confirmPassword : ['', Validators.required],
          gender : ['', Validators.required],
          age : ['', Validators.required]
        }, { validator: this.passwordMatchValidator })
      }

    private passwordMatchValidator(formGroup: FormGroup){
      const password = formGroup.get('password')?.value;
      const confirmPassword = formGroup.get('confirmPassword')?.value;
      if(password != confirmPassword){
        formGroup.get("confirmPassword")?.setErrors({ passwordMisMatch: true });
      } else{
        formGroup.get('confirmPassword')?.setErrors(null);
      }
    }  
    signUp(){
       console.log(this.signupForm.value);
       this.service.signup(this.signupForm.value).subscribe((response) => {
        console.log(response);
       })
    }

}
