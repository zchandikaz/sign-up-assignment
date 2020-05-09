import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  // BASE_PATH: 'http://localhost:8080'
  USER_NAME_SESSION_ATTRIBUTE_NAME = AuthenticationService._encrypt('authenticatedUser');
  AUTH_TOKEN_SESSION_ATTRIBUTE_NAME = AuthenticationService._encrypt('authToken');

  public username: String;
  public password: String;

  constructor(private http: HttpClient) {

  }

  authenticationService(username: String, password: String) {
    this.username = username;
    this.password = password;    
    return this.http.get(
        `http://localhost:8080/api/auth/`,    
        { 
          headers: { 
            authorization: this.createBasicAuthToken(username, password), 
          }
        }
      ).pipe(
        map(
          (res) => {
            this.username = username;
            this.password = password;
            this.registerSuccessfulLogin(username, password);
          }
        )
      );
  }

  private static _encrypt(str:any):string{ return window.btoa(str.toString()); }
  private static _decrypt(str:any):string{ return window.atob(str.toString()); }

  createBasicAuthToken(username: string|String, password: string|String):string {
    return 'Basic ' + AuthenticationService._encrypt(username + ":" + password)
  }

  registerSuccessfulLogin(username, password) {
    sessionStorage.setItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME, AuthenticationService._encrypt(username));
    sessionStorage.setItem(this.AUTH_TOKEN_SESSION_ATTRIBUTE_NAME, AuthenticationService._encrypt(this.createBasicAuthToken(username, password)));
  }

  logout() {
    sessionStorage.removeItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    sessionStorage.removeItem(this.AUTH_TOKEN_SESSION_ATTRIBUTE_NAME);
    this.username = null;
    this.password = null;
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    if (user === null) return false;
    return true;
  }

  getAuthToken(){    
    let encAuthToken = sessionStorage.getItem(this.AUTH_TOKEN_SESSION_ATTRIBUTE_NAME);    
    if(encAuthToken==null) return "";
    return AuthenticationService._decrypt(encAuthToken);
  }

  getLoggedInUserName() {
    let user = AuthenticationService._decrypt(sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME))
    if (user === null) return ''
    return user
  }
}