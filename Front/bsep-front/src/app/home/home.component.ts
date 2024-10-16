// home.component.ts
import { Component } from '@angular/core';
import { Route } from '@angular/router';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  constructor(private userService: UserService, private router: Router) {}

  redirectToLogin() {
    this.router.navigate(['/login']);
  }


}
