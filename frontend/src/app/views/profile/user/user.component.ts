import { KeycloakService} from "keycloak-angular";
import {Component, OnInit} from "@angular/core";
import {RaceService} from "../race.service";
import {CoefficientService} from "../coefficientservice";
import {Race} from "../race";
import {BetService} from "../bet.service";
import {UserService} from "../user.service";



@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['../admin/admin.component.scss']
})

export class UserComponent implements OnInit{

  userName =' ';
  user: any = [];
  races: Race[] = [];
  coefficients: any[] =[];
  bets: any[] = [];
  isValid: any[]=[];
  prices: any[]=[];
  constructor(private keycloakService: KeycloakService, private raceService: RaceService,
              private coefficientService: CoefficientService, private betService: BetService,
              private userService: UserService) {}

  ngOnInit(): void {
    this.initializeUserOptions();
    this.fetchUser();
    this.fetchBets();
    this.fetchRaces();
  }

  private initializeUserOptions(): void {
    this.userName = this.keycloakService.getUsername();
  }

  fetchBets(){
    this.betService.fetchBets().subscribe(
      response =>{
        this.bets = response;
      }
    );
  }

  //Method for get user
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
            if (this.checkRace(race)) {
              this.isValid.push(race.id);
            }
            let array: any[] = [];
            let isAdd: boolean = true;
            for (let horse of race.horses) {
              this.coefficientService.fetchCoefficient().subscribe(
                coefficients => {
                  for (let c of coefficients) {
                    //Check if there are coefficient on this horse in this race
                    //If true add to coefficients array
                    if (c.idRace == race.id && c.idHorse == horse.id) {
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

  //Method for add price of user bets
  checkRace(race: any):boolean{
    for(let bet of this.bets){
      if(bet.idRace == race.id && bet.idUser == this.user[0].id){
        this.prices.push(bet);
        return true;
      }
    }
    return false;
  }

  //Method for add bet
  addBet(bet: any){
    this.betService.postBet(bet);
    this.userService.updateBalance(bet);
    location.reload();
  }

  //Method for logout
  logout(): void{
    this.keycloakService.logout('http://localhost:4200');
  }

}
