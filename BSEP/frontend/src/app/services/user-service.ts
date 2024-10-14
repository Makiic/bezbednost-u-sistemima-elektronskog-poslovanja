// registration.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { User } from '../model/user-model';

@Injectable({
  providedIn: 'root',
})
export class UserService {

  constructor(private http: HttpClient) {}


  getIssuers(): Observable<User[]> {
    console.log("uslo u servis");
    const url = `http://localhost:8080/api/users/getAllPending`;
    this.http.get<User[]>(url).subscribe(users => {
      console.log(users); // Dodaj breakpoint ovde
    });
    return this.http.get<User[]>(url);
  }

  confirmUserRegistration(userId: number): Observable<any> {
    console.log("Uslo u servis za potvrdu registracije korisnika");
    const url = `http://localhost:8080/api/users/${userId}/confirm-registration`;
    return this.http.put(url, {});
  }

  rejectRegistration(userId: number, reason: string): Observable<any> {
    console.log("Uslo u servis za odbijanje registracije korisnika");
    const url = `http://localhost:8080/api/users/confirm-rejection`;
    
    // Pravljanje parametara za HTTP zahtev
    const params = new HttpParams()
      .set('userId', userId.toString())
      .set('reason', reason);

    // Postavljanje zaglavlja ako je potrebno
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/x-www-form-urlencoded');

    // Slanje HTTP POST zahteva
    return this.http.post(url, params.toString(), { headers })
      .pipe(
        catchError(error => {
          // Obrađivanje grešaka ako je potrebno
          console.error('An error occurred:', error);
          return throwError('Error occurred while rejecting registration.');
        })
      );
  }
}
