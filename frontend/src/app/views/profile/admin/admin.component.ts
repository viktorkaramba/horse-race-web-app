import { KeycloakService} from "keycloak-angular";
import {Component, OnInit} from "@angular/core";
import {RaceService} from "../race.service";
import {NgForm} from "@angular/forms";
import {MakeResultService} from "../makeresult.service";
import {BetService} from "../bet.service";
import {UserService} from "../user.service";
import {CoefficientService} from "../coefficientservice";
import {Race} from "../race";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})

export class AdminComponent implements OnInit{

  userName =' ';
  user: any = [];
  races: Race[] = [];
  coefficients: any[] =[];
  constructor(private keycloakService: KeycloakService, private raceService: RaceService,
              private makeResultService: MakeResultService, private betService: BetService,
              private userService: UserService, private coefficientService: CoefficientService) {}


  ngOnInit(): void {
    this.initializeUserOptions();
  }

  private initializeUserOptions(): void {
    this.userName = this.keycloakService.getUsername();
    this.fetchRaces();
  }

  fetchRaces(){
    this.raceService.fetchRaces().subscribe(
      data =>{
        console.log(data);
        for (let race of data) {
          //Check if race is over
          let b = race.ISOVER;
          console.log(b);
          if (!race.ISOVER) {
            let array: any[] = [];
            let isAdd: boolean = true;
            for (let horse of race.horses) {
              this.coefficientService.fetchCoefficient().subscribe(
                coefficients => {
                  for (let c of coefficients) {
                    //Check if there are coefficient on this horse in this race
                    //If true add to coefficients array
                    if (c.IDRA == race.ID && c.IDHO == horse.ID) {
                      array.push(c.value);
                      if (isAdd) {
                        this.races.push(race);
                        isAdd = false;
                      }
                    }
                  }
                }
              );
            }
            this.coefficients.push(array);
          }
        }
      }
    );
  }

//Method for logout
  logout(): void{
    this.keycloakService.logout('http://localhost:4200');
  }

//Method for add result
  addResult(horseForm: NgForm): void{
    this.makeResultService.postHorse(horseForm);
    location.reload();
  }

};
