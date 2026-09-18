// app.routes.ts

import { Routes } from '@angular/router';
import { authGuard } from './core/guards/auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  {
    path: 'login',
    loadComponent: () => import('./features/auth/login-page/login-page')
      .then(m => m.LoginPageComponent)
  },
  {
    path: 'register',
    loadComponent: () => import('./features/auth/register-page/register-page')
      .then(m => m.RegisterPageComponent)
  },
  {
    path: 'dashboard',
    canActivate: [authGuard],
    loadComponent: () => import('./app')
      .then(m => m.App) // placeholder temporário até criarmos o Dashboard de verdade
  }
];
