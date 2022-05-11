
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminComponent } from './admin/admin.component'
import {UserComponent} from "./user/user.component";
import {BookmakerComponent} from "./bookmaker/bookmaker.component";
import {RedirectComponent} from "./redirector/redirect.component";

const routes: Routes = [
  { path: '', redirectTo: 'redirector'},
  { path: 'redirector', component: RedirectComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'user', component: UserComponent },
  { path: 'bookmaker', component: BookmakerComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfileRoutingModule { }
