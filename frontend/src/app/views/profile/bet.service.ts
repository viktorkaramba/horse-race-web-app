import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Injectable()
export class BetService {

  _url : string = 'http://localhost:8088/bets/';

  constructor(private http: HttpClient) {}

  //Method used to get all races
  fetchBets(){
    return this.http.get<any>(this._url);
  }

  //Method used to post races
  postBet(data: any): void{
    const headers = {
      'content-type': 'application/json'
    };
    //Firstly parse user data
    let id = 0;
    let bet = data.value["bet"];
    let betInfo = data.value["info"].split(', ');
    let coefficient = betInfo[0];
    let idUser = betInfo[1];
    let idRace = betInfo[2];
    let price = bet * coefficient;
    let idHorse = betInfo[3];
    //Create json for body of request
    let body = JSON.stringify({ID: id, IDUS: idUser, IDRA: idRace, IDHO: idHorse, price: price});
    console.log(body);
    this.http.post<any>(this._url, body, {'headers':headers}).subscribe();
  }
}
