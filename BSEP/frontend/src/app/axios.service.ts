import { Injectable } from '@angular/core';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class AxiosService {

  constructor() {
    axios.defaults.baseURL = 'http://localhost:8080';
    axios.defaults.headers.post['Content-Type'] = 'application/json';
  }

  getAuthToken(): string | null {
    return window.localStorage.getItem("auth_token");
  }
  getUser(): any {
    const token = this.getAuthToken();
    console.log('token kad se udje u axios', token)
    if (token) {
      // Dekodirajte token kako biste dobili podatke iz njega
      const tokenData = JSON.parse(atob(token.split('.')[1]));
      return tokenData;
    }
    return null;
  }

  setAuthToken(token: string | null): void {
    if (token !== null) {
      window.localStorage.setItem("auth_token", token);
    } else {
      window.localStorage.removeItem("auth_token");
    }
  }


  request(method: string, url: string, data: any): Promise<any> {
      let headers: any = {};

      if (this.getAuthToken() !== null) {
          headers = {"Authorization": "Bearer " + this.getAuthToken()};
      }

      return axios({
          method: method,
          url: url,
          data: data,
          headers: headers
      });
  }
}
