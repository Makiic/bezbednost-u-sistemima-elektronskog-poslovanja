import { EventEmitter, Component, Output, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

interface FormSubmitData {
  login: string;
  password: string;
  firstName?: string;
  lastName?: string;
  companyName?: string;
  pib?: string;
  address?: string;
  city?: string;
  country?: string;
  phoneNumber?: string;
  isLegal?: boolean;
  packageType? : string;
  otp?:string;
}

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css'],
})
export class LoginFormComponent implements OnInit {
  protected aFormGroup!:FormGroup;
  constructor(private formBuilder: FormBuilder) {}
  ngOnInit(): void {
    this.aFormGroup = this.formBuilder.group({
      recaptcha: ['', Validators.required]
    });  }

  @Output() onSubmitLoginEvent = new EventEmitter<FormSubmitData>();
  @Output() onSubmitRegisterEvent = new EventEmitter<FormSubmitData>();
  @Output() onSubmitLoginRealEvent = new EventEmitter<FormSubmitData>();

  siteKey:string="6LcXG70pAAAAAMPPfa-MNX11kVJcTeGoEWrZEqXt";
  solved: boolean = false;
  active: string = "login";
  firstName: string = "";
  lastName: string = "";
  companyName: string = "";
  pib: string = "";
  address: string = "";
  city: string = "";
  country: string = "";
  phoneNumber: string = "";
  login: string = "";
  password: string = "";
  userType: string = "individual";
  isLegal: boolean = false;
  confirmPassword:string="";
  packageType: string="";
  otp: string = ""; // Dodajemo otp polje
  showOTP:boolean=false;


  onLoginTab(): void {
    this.active = "login";
  }

  onRegisterTab(): void {
    this.active = "register";
  }

  onSubmitLogin(): void {
    console.log(this.solved)
    if(this.solved){
      this.showOTP=!this.showOTP;
      console.log('otp je',this.otp)
      if(this.otp!==""){
        console.log("uslo u real event component")
        this.onSubmitLoginRealEvent.emit({"login": this.login, "password": this.password, "otp": this.otp});

      } else {
        this.onSubmitLoginEvent.emit({"login": this.login, "password": this.password});
      }
    }
  }

  

  handleSuccess(event: any): void {
    this.solved = true;
    console.log(this.solved)
    console.log('reCAPTCHA resolved with response:', event);
    // Možete dodati ovde dodatnu logiku ako je potrebno nakon uspešnog rešavanja reCAPTCHA.
  }

  onSubmitRegister(): void {
    if (this.password !== this.confirmPassword) {
      // Možemo ovde dodati neku logiku za prikazivanje poruke o grešci
      console.log("Lozinke se ne poklapaju");
      return; // Prekidamo izvršavanje funkcije ako se lozinke ne poklapaju
    }
    const formData: FormSubmitData = {
      "login": this.login,
      "password": this.password
    };

    if (this.userType === 'individual') {
      formData["firstName"] = this.firstName;
      formData["lastName"] = this.lastName;
    } else if (this.userType === 'legal') {
      formData["companyName"] = this.companyName;
      formData["pib"] = this.pib;
    }

    formData["address"] = this.address;
    formData["city"] = this.city;
    formData["country"] = this.country;
    formData["phoneNumber"] = this.phoneNumber;
    formData["packageType"] = this.packageType;
    // Adding isLegal boolean value
    formData["isLegal"] = this.userType === 'legal';
    console.log(formData);
    this.onSubmitRegisterEvent.emit(formData);
  }

  onUserTypeToggle(type: string): void {
    this.userType = type;
    this.firstName = "";
    this.lastName = "";
    this.companyName = "";
    this.pib = "";
    this.address = "";
    this.phoneNumber="";
    this.city="";
    this.country="";
    this.packageType="";
  }
  containsNumber(password: string): boolean {
    return /\d/.test(password);
  }
  
  containsSpecialCharacter(password: string): boolean {
    // Specijalni karakteri definisani su regularnim izrazom [^A-Za-z0-9],
    // koji traži sve karaktere koji nisu slova (velika ili mala) ili brojevi.
    return /[^A-Za-z0-9]/.test(password);
  }

}
