import { Component, OnInit } from '@angular/core';
import { User } from '../model/user-model';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { HttpErrorResponse } from '@angular/common/http';
import { UserStateService } from '../services/user-state.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls:[ './navbar.component.css']
})
export class NavbarComponent implements OnInit {
  user!: User;
  
  constructor(
    private router: Router, 
    private userService: UserService, 
    public userStateService: UserStateService,) {
  }

  ngOnInit():void{
  }

  
  redirectToLogin() {
    // Get the redirect URL from the service
    //const redirectUrl = this.userService.getLoginRedirectUrl();

    // Redirect to the external URL
    //window.location.href = redirectUrl;
    this.router.navigate(['/login']);
  }

  logout(){
    this.userStateService.clearLoggedInUser();
    this.router.navigate(['/']);
  }
  
}
