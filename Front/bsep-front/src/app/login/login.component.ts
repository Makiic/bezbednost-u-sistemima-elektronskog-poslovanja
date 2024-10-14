import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { User } from '../model/user-model';
import { UserStateService } from '../services/user-state.service';
import { DatePipe } from "@angular/common";
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {


  constructor(private router: Router, private userService: UserService,
     private userStateService: UserStateService) {

  }

  username: string = '';
  password: string = '';
  userDoesNotExist: boolean = false;
  disabled: boolean = false;
  wrongPassword:boolean=false;
  user?: User;

  

  login() {
    this.userService.getUserByUsername(this.username).subscribe(
      (response) => {
        if(response != null){
          this.user = response;
          this.handleLogIn();
          console.log('ULOGOVAN USER NAKON PROMENE PENALTY POINTS: ', this.userStateService.getLoggedInUser())
        }
      },
      (error) => {
        this.userDoesNotExist = true;
        console.log('User does not exist');
        console.log(error);
      }
    );
  }

  handleLogIn(){
    

      
      console.log(this.user);

      if (this.user && this.comparePasswords(this.password, this.user.password)) {
        this.userStateService.setLoggedInUser(this.user);
        this.router.navigate(['/homepage']);
      } else {
        this.wrongPassword=true;
      }
   
  }

  private comparePasswords(enteredPassword: string, storedPassword: string): boolean {
   return enteredPassword==storedPassword;
   //return true; //izmena?
  }

  
}