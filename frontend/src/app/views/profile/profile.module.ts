import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProfileRoutingModule } from './profile-routing.module';
import {AdminComponent} from "./admin/admin.component";
import {UserComponent} from "./user/user.component";
import {BookmakerComponent} from "./bookmaker/bookmaker.component";
import {RedirectComponent} from "./redirector/redirect.component";
import {FormsModule} from "@angular/forms";
import {DropDownListModule} from "@syncfusion/ej2-angular-dropdowns";


@NgModule({
  declarations: [AdminComponent, UserComponent, BookmakerComponent, RedirectComponent],
  imports: [
    CommonModule,
    ProfileRoutingModule,
    FormsModule,
    DropDownListModule
  ]
})
export class ProfileModule { }
