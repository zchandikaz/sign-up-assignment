import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Utils } from './utils/utils';
import { UserData } from './utils/entity';

const API_URL = "http://localhost:8080/api/";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  public requestSignUp(userData: UserData) {        
    return this.http.post(API_URL +`user/register/`, userData);
  }
}
