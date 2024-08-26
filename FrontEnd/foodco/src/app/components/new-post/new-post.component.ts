import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
@Component({
  selector: 'app-new-post',
  standalone: true,
  imports: [ReactiveFormsModule,
    MatCardModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule
  ],
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css']
})
export class NewPostComponent implements OnInit {
  postForm!: FormGroup;
  selectedFile!: File;  // Seçilen dosyayı tutmak için

  constructor(private formBuilder: FormBuilder, private http: HttpClient) {}

  ngOnInit(): void {
    this.postForm = this.formBuilder.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      memberId: [null, Validators.required]
    });
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];  // Seçilen dosyayı aldık
  }

  onSubmit() {
    if (this.postForm.valid) {
      const formData = new FormData();
      formData.append('title', this.postForm.get('title')?.value);
      formData.append('description', this.postForm.get('description')?.value);
      formData.append('memberId', this.postForm.get('memberId')?.value);
      formData.append('image', this.selectedFile);  // Dosyayı formData'ya ekliyoruz

      this.http.post('http://localhost:8080/post/add', formData).subscribe(
        (response) => {
          console.log('Başarılı:', response);
        },
        (error) => {
          console.error('Hata:', error);
        }
      );
    } else {
      console.log('Form geçerli değil');
    }
  }
}
