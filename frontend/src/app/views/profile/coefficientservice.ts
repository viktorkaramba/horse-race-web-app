import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable, Subscription} from "rxjs";
import {Coefficient} from "./coefficient";

@Injectable()
export class CoefficientService {

  constructor(private http: HttpClient) {}
  _url : string = 'http://localhost:8088/coefficients/';

  //Method used to get coefficients
  fetchCoefficient(): Observable<Coefficient[]>{
    return this.http.get<Coefficient[]>(this._url);
  }

  //Method used to get coefficient by race id and horse id
  postCoefficient(idRace: any, idHorse : any){
    const headers = {
      'content-type': 'application/json'
    };
    //Create json for body of request
    let body = JSON.stringify({idFirst: idRace, idSecond: idHorse})
    return this.http.put<any>(this._url, body, {'headers':headers});
  }

  //Method used to post coefficient
  postCoefficients(body: any){
    const headers = {
      'content-type': 'application/json'
    };
    let _id = 0;
    let _coefficient = body.value["coefficient"];
    console.log(_coefficient);
    let _coefficientInfo = body.value["info"].split(', ');
    let _idRace = _coefficientInfo[0];
    let _idHorse = _coefficientInfo[1];
    let data = JSON.stringify({ID: _id, IDRA: _idRace, IDHO: _idHorse, value: _coefficient});
    console.log(data);
    this.http.post<any>(this._url, data, {'headers':headers}).subscribe();
  }

}
