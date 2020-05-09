import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatInput } from '@angular/material';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  firstName = new FormControl();
  lastName = new FormControl();
  address = new FormControl();
  email = new FormControl();
  password = new FormControl();
  gender = new FormControl();
  dob = new FormControl();

  constructor() { }

  ngOnInit() {
  }

}
