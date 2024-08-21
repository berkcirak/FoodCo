import { Component, OnInit } from '@angular/core';
import { ConfigService } from '../../services/config-service';
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs'; // rxjs'i import edin

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css'],
})
export class HomePageComponent implements OnInit {
  postList: any[] = [];

  constructor(private configService: ConfigService) {}

  ngOnInit(): void {
    this.getData();
  }

  getData(): void {
    // Observable veri tipi belirliyoruz
    this.configService.getAllData().subscribe({
      next: (data) => {
        this.postList = data;
      },
      error: (error) => {
        console.error('Error fetching data: ', error);
      }
    });
  }
}
