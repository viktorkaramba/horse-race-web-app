import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Injectable()
export class BetService {

  _url : string = 'http://localhost:8088/bets/';

  constructor(private http: HttpClient) {}

  fetchBets(){
    return this.http.get<any>(this._url);
  }

  postBet(data: any): void{
    const headers = {
      'content-type': 'application/json'
    };
    let id = 0;
    let bet = data.value["bet"];
    let betInfo = data.value["info"].split(', ');
    let coefficient = betInfo[0];
    let idUser = betInfo[1];
    let idRace = betInfo[2];
    let price = bet * coefficient;
    let idHorse = betInfo[3];
    let top = "0";
    let body = JSON.stringify({id: id, idUser: idUser, idRace: idRace, idHorse: idHorse, price: price, top: top});
    console.log(body);
    this.http.post<any>(this._url, body, {'headers':headers}).subscribe();
  }
}
