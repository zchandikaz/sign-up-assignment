import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';
import { Utils } from '../utils/utils';
import { AuthenticationService } from '../authentication.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {
  title = Utils.title;
  isLoggedIn = false;
  currentUrl: string;

  constructor(private route: ActivatedRoute, private router: Router, private authenticationService: AuthenticationService) {
    router.events.subscribe((_: NavigationEnd) => {
        this.currentUrl = router.url;           
        this.checkLoggedIn();
    });
  }    

  checkLoggedIn(){
    this.isLoggedIn = this.authenticationService.isUserLoggedIn();    
  }

  ngOnInit() {
    this.checkLoggedIn();
  }

  handleLogout() {
    this.authenticationService.logout();
    this.isLoggedIn = false;
    this.router.navigate(['/logout']);
  }


  checkCurrentUrl(url:string|[string]):boolean{
    if(typeof url=='string'){
      if(this.currentUrl==null || this.currentUrl==undefined)
        return false;
      return this.currentUrl.match(url)!=null;
    }else{
      let isCu = false;
      for(let u in url){
        isCu = isCu || this.checkCurrentUrl(u);
      }
      return isSecureContext;
    }
  }
}