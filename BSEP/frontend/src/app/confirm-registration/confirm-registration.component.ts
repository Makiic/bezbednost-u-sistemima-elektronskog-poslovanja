// confirm-registration.component.ts

import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user-service';
import { User } from '../model/user-model';

@Component({
  selector: 'app-confirm-registration',
  templateUrl: './confirm-registration.component.html',
  styleUrls: ['./confirm-registration.component.css']
})
export class ConfirmRegistrationComponent implements OnInit {
  users: User[] = [];
  rejectedUserId: any; // Promenljiva za praćenje ID-a korisnika koji je odbijen
  rejectionReason: string = ''; 
  constructor(private userService: UserService) {}

  ngOnInit() {
    this.getPendingUsers();
  }
  getPendingUsers(): void {
    this.userService.getIssuers()
      .subscribe(users => {
        this.users = users;
      });
  }
  confirmRegistration(userId: number): void {
    this.userService.confirmUserRegistration(userId)
      .subscribe(() => {
        console.log('Registracija korisnika je potvrđena.');
        // Možete dodati logiku za osvežavanje liste korisnika na čekanju
        this.getPendingUsers();
      }, error => {
        console.error('Došlo je do greške prilikom potvrde registracije:', error);
        // Možete dodati logiku za prikazivanje greške korisniku
      });
  }
  rejectRegistration(userId: any) {
    this.rejectedUserId = userId;
  }
  confirmRejection(userId: any, reason: string) {
    this.userService.rejectRegistration(userId, reason).subscribe(
      response => {
        console.log("uspesno odbijanje")
        this.getPendingUsers();
      },
      error => {
        // Obrada greške
      }
    );
  }
}
