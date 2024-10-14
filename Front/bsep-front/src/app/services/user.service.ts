// registration.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../env/environment';
import { User } from '../model/user-model';
import { CertificateRequest } from '../model/certificate-request-model';
import { Certificate } from '../model/certificate-model';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private readonly apiUrl = `${environment.apiHost}`;

  constructor(private http: HttpClient) {}


  getUserByUsername(username?: string): Observable<User> {
    return this.http.get<User>('http://localhost:8080/api/users/getUserByUsername/' + username);
  }

  getIssuers(): Observable<User[]> {
    console.log("uslo u servis");
    const url = `http://localhost:8080/api/users/getAllByIsIssuerTrue`;
    return this.http.get<User[]>(url);
  }

  
  getSubjects(): Observable<User[]> {
    console.log("uslo u servis");
    const url = `http://localhost:8080/api/users/getAllByIsSubjectTrue`;
    return this.http.get<User[]>(url);
  }
  
  generateCertificateRequest(request: CertificateRequest, keyStorePassword: string): Observable<void> {
    console.log(request);
    return this.http.post<void>(`http://localhost:8080/api/users/generateCertificate/${keyStorePassword}`, request);
  }
  loadCertificate(): Observable<Certificate> {
    console.log("USLO U SERVIS")
    return this.http.get<Certificate>(`http://localhost:8080/api/users/loadCertificate`);
  }
  loadCertificates(): Observable<Certificate[]> {
    console.log("USLO U SERVIS")
    return this.http.get<Certificate[]>(`http://localhost:8080/api/users/loadCertificates`);
  }
  
}
