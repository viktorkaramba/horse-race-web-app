import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {KeycloakService} from "keycloak-angular";

@Component({
  selector: 'app-redirect',
  templateUrl: 'redirect.component.html',
  styleUrls: ['./redirect.component.scss']
})

//Used for redirect to page by role of user
export class RedirectComponent implements OnInit {

  constructor(private router: Router, private keycloakService: KeycloakService) { }

  ngOnInit(): void {
    this.initializeUserOptions();
  }

  private initializeUserOptions(): void {
    if(this.keycloakService.isUserInRole('admin')){
      this.router.navigateByUrl('/profile/admin')
    }
    else if(this.keycloakService.isUserInRole('user')){
      this.router.navigateByUrl('/profile/user')
    }
    else if(this.keycloakService.isUserInRole('bookmaker')){
      this.router.navigateByUrl('/profile/bookmaker')
    }
  }
}
