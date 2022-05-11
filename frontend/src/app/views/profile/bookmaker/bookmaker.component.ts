import { KeycloakService} from "keycloak-angular";
import {Component, OnInit} from "@angular/core";
import {RaceService} from "../race.service";
import {Race} from "../race";
import {CoefficientService} from "../coefficientservice";
import {BetService} from "../bet.service";
import {UserService} from "../user.service";



@Component({
  selector: 'app-bookmaker',
  templateUrl: './bookmaker.component.html',
  styleUrls: ['../admin/admin.component.scss']
})

export class BookmakerComponent implements OnInit{

  userName =' ';
  user: any = [];
  races: Race[] = [];
  horses: any;
  coefficients: any[] =[];
  constructor(private keycloakService: KeycloakService, private raceService: RaceService,
              private coefficientService: CoefficientService, private betService: BetService,
              private userService: UserService) {}

  ngOnInit(): void {
    this.initializeUserOptions();
    this.fetchRaces();
    this.fetchUser();
  }

  private initializeUserOptions(): void {
    this.userName = this.keycloakService.getUsername();
  }

  fetchUser(){
    this.userService.fetchUser(this.userName).subscribe(
      response =>{
        this.user = response;
      }
    );
  }

  fetchRaces(){
    this.raceService.fetchRaces().subscribe(
      data =>{
        for (let race of data) {
          if (!race.isOver) {
            let isAdd: boolean = true;
            for(let horse of race.horses) {
              this.coefficientService.postCoefficient(race.id, horse.id).subscribe(
                coefficients => {
                    if (coefficients == null) {
                      if (isAdd) {
                        this.races.push(race);
                        isAdd = false;
                      }
                    }
                    else {
                      let index = race.horses.indexOf(horse);
                      race.horses.splice(index,1);
                    }
                }
              );
            }
          }
        }
      }
    );
  }

  addCoefficient(body: any){
    this.coefficientService.postCoefficients(body);
    location.reload();
  }

  logout(): void{
    this.keycloakService.logout('http://localhost:4200');
  }

}
