import { Component } from '@angular/core';
import { User } from '../model/user-model';
import { UserService } from '../services/user.service';
import { DatePipe } from '@angular/common';
import { CertificateRequest } from '../model/certificate-request-model';

@Component({
  selector: 'app-create-certificate',
  templateUrl: './create-certificate.component.html',
  styleUrl: './create-certificate.component.css'
})

export class CreateCertificateComponent {
  issuers: User[] = [];
  subjects: User[] = [];
  selectedIssuer: User | null = null;
  selectedSubject: User | null = null;

  constructor(private userService: UserService, private datePipe: DatePipe) {}

  ngOnInit(): void {
    this.getIssuers();
    this.getSubjects();
  }

  getIssuers(): void {      
    console.log("uslo u metodu");
    this.userService.getIssuers().subscribe(
      (users: User[]) => {
        // Assign the retrieved issuers to the issuers property
        console.log("issuers:", users);
        this.issuers = users;
        // Log and format date for each issuer
      },
      (error) => {
        console.error('Error fetching issuers:', error);
      }
    );
  }

  getSubjects(): void {      
    this.userService.getSubjects().subscribe(
      (users: User[]) => {
        // Assign the retrieved subjects to the subjects property
        console.log("subjects:", users);
        this.subjects = users;
        // Log and format date for each subject
      },
      (error) => {
        console.error('Error fetching subjects:', error);
      }
    );
  }

  formatDate(date: any): string {
    return this.datePipe.transform(date, 'dd-MMM-yy') || '';
  }
  
  onSubmit(): void {
    if (this.selectedIssuer && this.selectedSubject) {
      const certificateRequest: CertificateRequest = {
        subjectData: this.selectedSubject,
        issuerData: this.selectedIssuer
      };
  
      this.userService.generateCertificateRequest(certificateRequest, "sifra").subscribe(
        () => {
          console.log('Certificate request sent successfully.');
          // Additional logic if needed
        },
        (error) => {
          console.error('Error sending certificate request:', error);
        }
      );
    } else {
      console.error('Please select both issuer, subject, and provide the keystore password.');
    }
  }
  
}
