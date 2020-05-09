import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatInput, MatDialog } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from '../authentication.service';
import { Utils } from '../utils/utils';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  username = new FormControl();
  password = new FormControl();
  errorMessage = 'Invalid Credentials';
  successMessage: string;
  invalidLogin = false;
  loginSuccess = false;
  
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    public dialog: MatDialog,
    public userService: UserService
  ) { }

  ngOnInit() {
  }

  handleLogin(){        
    this.userService.status(this.username.value).subscribe(status => {
      if(status == "0"){
        Utils.alertDialog(this.dialog, "Please verify your account before login. Please check the email.");      
      } else if(status == "-1"){
        Utils.alertDialog(this.dialog, "User name not found");      
      } else {
        this.authenticationService.authenticationService(this.username.value, this.password.value).subscribe((result)=>{
          this.invalidLogin = false;
          this.loginSuccess = true;
          this.successMessage = 'Login Successful';
          this.router.navigate(['/']);
        }, ()=>{
          this.invalidLogin = true;
          this.loginSuccess = false;
          Utils.alertDialog(this.dialog, "Invalid username or password");
        });
      }

    });
    
    
  }
}
