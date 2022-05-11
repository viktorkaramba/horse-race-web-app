import {APP_INITIALIZER, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {UserService} from "./views/profile/user.service";
import {BetService} from "./views/profile/bet.service";
import {CoefficientService} from "./views/profile/coefficientservice";
import {MakeResultService} from "./views/profile/makeresult.service";
import {HorseService} from "./views/profile/horse.service";
import {RaceService} from "./views/profile/race.service";
import {KeycloakAngularModule, KeycloakService} from "keycloak-angular";
import {initializeKeycloak} from "./utility/app.init";
import {AppRoutingModule} from "./app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {DropDownListModule} from "@syncfusion/ej2-angular-dropdowns";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    KeycloakAngularModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    DropDownListModule
  ],
  providers: [{
    provide: APP_INITIALIZER,
    useFactory: initializeKeycloak,
    multi: true,
    deps: [KeycloakService]
  },
    RaceService,
    HorseService,
    MakeResultService,
    CoefficientService,
    BetService,
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
