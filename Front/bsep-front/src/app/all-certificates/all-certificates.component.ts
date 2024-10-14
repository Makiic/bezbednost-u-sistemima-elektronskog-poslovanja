import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { UserService } from '../services/user.service';
import { Certificate } from '../model/certificate-model';

@Component({
  selector: 'app-all-certificates',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './all-certificates.component.html',
  styleUrl: './all-certificates.component.css'
})


export class AllCertificatesComponent {

  certificates: Certificate[] = [];

  constructor(private userService: UserService) {}
  ngOnInit(): void {
    console.log("USLO U INIT");
    this.loadCertificate();
  }

  loadCertificate(): void{
    console.log("uslo u metodu");
    this.userService.loadCertificates().subscribe(
      (cert: Certificate[]) => {
        this.certificates=cert;
        console.log("certificate:", this.certificates);
        // Log and format date for each issuer
      },
      (error) => {
        console.error('ERROR RETURNING CERTIFICATE', error);
      }
    );
    
  }
  getCommonName(subject: string): string {
    const parts = subject.split(','); // Razdvaja string na delove koristeći zarez kao separator
    let commonName = ''; // Inicijalizacija praznog stringa za čuvanje imena
  
    // Iterira kroz delove stringa
    parts.forEach(part => {
      if (part.trim().startsWith('CN=')) { // Pronalazi deo koji počinje sa "CN="
        commonName = part.trim().substring(3); // Izdvaja deo posle "CN="
      }
    });
  
    return commonName; // Vraća izdvojeno ime
  }
  formatDate(dateString: string): string {
    const parts = dateString.split(' '); // Razdvaja datum i vreme
    const datePart = parts.slice(0, 3).join(' '); // Zadržava samo deo datuma
    return datePart; // Vraća formatirani datum
  }

}
function ngOnInit() {
  throw new Error('Function not implemented.');
}

