import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-captcha',
  templateUrl: './captcha.component.html',
  styleUrls: ['./captcha.component.css']
})
export class CaptchaComponent implements OnInit{
  @Output() captchaResolved: EventEmitter<void> = new EventEmitter<void>();

  protected aFormGroup!: FormGroup;
  siteKey:string="6LcXG70pAAAAAMPPfa-MNX11kVJcTeGoEWrZEqXt";
  constructor(private formBuilder: FormBuilder){}
  ngOnInit(){
    this.aFormGroup=this.formBuilder.group({
      recaptcha:['',Validators.required]
    });
  }
  onCaptchaResolved(): void {
    this.captchaResolved.emit();
  }
  

}
