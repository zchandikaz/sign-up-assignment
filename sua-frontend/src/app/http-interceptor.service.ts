import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthenticationService } from './authentication.service';
import { Utils } from './utils/utils'

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorService implements HttpInterceptor{

  constructor(private authenticationService: AuthenticationService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {    
    if (this.authenticationService.isUserLoggedIn() && req.url.indexOf('basicauth') === -1 && req.url != Utils.AUTH_URL) {
        // normal request
        const authReq = req.clone({
            headers: new HttpHeaders(
              {
                'Content-Type': 'application/json',
                'Authorization': this.authenticationService.getAuthToken()
              }
            )
        });        
        return next.handle(authReq);
      } else {
          //login request          
          return next.handle(req);
      }
  }
}
