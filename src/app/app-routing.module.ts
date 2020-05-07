import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SignInComponent } from './sign-in/sign-in.component';


const routes: Routes = [
  {path: 'signin', component: SignInComponent},
  {path: 'signup', component: SignInComponent},
  {path: 'signup', component: SignInComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
