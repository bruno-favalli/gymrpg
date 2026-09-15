import { Component, signal, OnInit, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment.development';

interface StatusResponse {
  message: string;
}

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App implements OnInit {
  protected readonly title = signal('gymrpg-web');
  apiStatus = signal('Carregando...');
  private readonly http = inject(HttpClient);

  ngOnInit() {
    this.http.get<StatusResponse>(`/api/status`).subscribe({
      next: (response) => this.apiStatus.set(response.message),
      error: (err) => {
        console.error('Erro retornado na chamada HTTP:', err);
        this.apiStatus.set('Erro ao obter status da API');
      }


    });
  }
}
