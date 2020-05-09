import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatInput, MatDialog } from '@angular/material';
import { Utils } from '../utils/utils';
import { UserService } from '../user.service';
import { Router } from '@angular/router';



@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  firstName = new FormControl();
  lastName = new FormControl();
  username = new FormControl();
  address = new FormControl();
  email = new FormControl();
  password = new FormControl();
  cpassword = new FormControl();
  gender = new FormControl();
  dob = new FormControl();

  constructor(
    public dialog: MatDialog,
    public userService: UserService,
    public router: Router
  ) { }

  ngOnInit() {
  }

  isEmpty(val, name){
    if(val == null){
      Utils.alertDialog(this.dialog, name + " cannot be empty");
      return true;
    }
    return false;
  }

  register(){       
    // Utils.alertDialog(this.dialog, this.gender.value);

    [this.firstName, this.lastName, this.username, this.address, this.email, this.password, this.cpassword].forEach(v=>v.markAllAsTouched());  
    this.firstName.markAllAsTouched(); // trigger validate

    if(this.isEmpty(this.firstName.value, "First Name"))
      return false;

    if(this.isEmpty(this.lastName.value, "Last Name"))
      return false;

    let usernameValidate = Utils.validateUsername(this.username.value)
    if(!usernameValidate[0])
      return Utils.alertDialog(this.dialog, usernameValidate[1].toString());

    if(this.isEmpty(this.address.value, "Address"))
      return false;

    if(this.isEmpty(this.email.value, "Email"))
      return false;

    if(!Utils.isValidEmail(this.email.value))
      return Utils.alertDialog(this.dialog, "Invalid email address. Please check again.");

    if(this.isEmpty(this.password.value, "Password"))
      return false;

    if(this.password.value!=this.cpassword.value){
      return Utils.alertDialog(this.dialog, "Password and Confirm Password does not match");
    }

    if(!(this.gender.value in ["0", "1", "2"])){
      return Utils.alertDialog(this.dialog, "Please select your gender");
    }

    let userData = {
      firstName: this.firstName.value,
      lastName: this.lastName.value,
      username: this.username.value,
      address: this.address.value,
      email: this.email.value,
      password: this.password.value,
      gender: this.gender.value
    };

    this.userService.status(userData.username).subscribe(status => {
      if(status == "0"){
        Utils.alertDialog(this.dialog, "This user account need to be verified. No need to register again.");      
      } else if(status == "-1"){
        this.userService.requestSignUp(userData).subscribe( result => {       
          if(result['success'])
            this.router.navigate(['/msg'], { queryParams: { msg: result['text'] } });          
          else
            Utils.alertDialog(this.dialog, result['text']);
        });    
      } else {
        Utils.alertDialog(this.dialog, "User name already exist. Try another");
      }

    });

    
  }
}
