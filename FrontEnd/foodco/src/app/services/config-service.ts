import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  private apiUrl='http://localhost:8080/post';


  constructor(private http: HttpClient) { }


  getAllData(): Observable<any[]>{
    return this.http.get<any[]>(`${this.apiUrl}/list`);
  }






}
