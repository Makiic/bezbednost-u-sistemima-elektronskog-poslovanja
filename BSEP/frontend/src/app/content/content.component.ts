import { Component } from '@angular/core';
import { AxiosService } from '../axios.service';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent {
	componentToShow: string = "welcome";
	user: any;
	isAdmin : boolean = false;

	constructor(private axiosService: AxiosService) { 
		console.log('uslo je u konstukror')
		this.user = this.axiosService.getUser();
		console.log('ovo je user',this.user)
	}

	showComponent(componentToShow: string): void {
	this.user = this.axiosService.getUser();
	console.log('user u show component', this.user)
    this.componentToShow = componentToShow;
  }

  isUserLoggedIn(): boolean {
    return this.user !== null;
  }
  isUserAdmin(): boolean {
	return this.user !== null && this.user.role === 'ADMIN';
  }
  

  onLogout(): void {
    this.axiosService.setAuthToken(null); 

	// Clear authentication token
    //this.componentToShow = "welcome"; 
	this.showComponent("welcome");
	console.log('uslo u logout i ovo je user', this.user)
	// Show the welcome component
}

	onLogin(input: any): void {
		this.axiosService.request(
		    "POST",
		    "/login",
		    {
		        login: input.login,
		        password: input.password
		    }).then(
		    response => {
		        this.axiosService.setAuthToken(response.data.token);
		        //this.componentToShow = "messages";
				console.log(this.axiosService.getUser());
		    }).catch(
		    error => {
		        this.axiosService.setAuthToken(null);
		        this.componentToShow = "welcome";
		    }
		);

	}

	onLoginReal(input: any): void {
		this.axiosService.request(
		    "POST",
		    "/loginReal",
		    {
		        login: input.login,
		        password: input.password,
				otp: input.otp
		    }).then(
		    response => {
		        this.axiosService.setAuthToken(response.data.token);
				this.showComponent("messages");
		        //this.componentToShow = "messages";
				console.log('uslo u login i ovo je user', this.user)

				console.log(this.axiosService.getUser());
		    }).catch(
		    error => {
		        this.axiosService.setAuthToken(null);
		        this.componentToShow = "welcome";
		    }
		);

	}

	onRegister(input: any): void {
		this.axiosService.request(
		    "POST",
		    "/register",
		    {
		        firstName: input.firstName,
		        lastName: input.lastName,
		        login: input.login,
		        password: input.password,
				pib: input.pib,
				companyName: input.companyName,
				address:input.address,
				city:input.address,
				country:input.country,
				phoneNumber:input.phoneNumber,
				isLegal:input.isLegal,
				packageType:input.packageType
		    }).then(
		    response => {
		        this.axiosService.setAuthToken(response.data.token);
		        this.componentToShow = "messages";
				console.log('uslo u register i ovo je user', this.user)

		    }).catch(
		    error => {
		        this.axiosService.setAuthToken(null);
		        this.componentToShow = "welcome";
		    }
		);
	}

}
