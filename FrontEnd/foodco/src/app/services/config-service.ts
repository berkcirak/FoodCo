import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PostDTO } from '../models/postdto';
@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  private apiUrl='http://localhost:8080/post';


  constructor(private http: HttpClient) { }


  getAllData(): Observable<any[]>{
    return this.http.get<any[]>(`${this.apiUrl}/list`);
  }

  addPost(post: PostDTO): Observable<PostDTO>{
    return this.http.post<PostDTO>(`${this.apiUrl}/add`,post);
  }




}
