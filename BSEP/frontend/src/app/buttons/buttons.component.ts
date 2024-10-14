import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-buttons',
  templateUrl: './buttons.component.html',
  styleUrls: ['./buttons.component.css']
})
export class ButtonsComponent {
	@Output() loginEvent = new EventEmitter();
	@Output() logoutEvent = new EventEmitter();
  @Output() register = new EventEmitter();
  @Input() isLoggedIn: boolean = false; // Dodajemo ulazno svojstvo za prikaz dugmeta za odjavljivanje
  @Input() isAdmin: boolean = false; 

}
