import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable, Subscription} from "rxjs";
import {Coefficient} from "./coefficient";

@Injectable()
export class CoefficientService {

  constructor(private http: HttpClient) {}
  _url : string = 'http://localhost:8088/coefficients/';

  fetchCoefficient(): Observable<Coefficient[]>{
    return this.http.get<Coefficient[]>(this._url);
  }

  postCoefficient(idRace: any, idHorse : any){
    const headers = {
      'content-type': 'application/json'
    };
    let body = JSON.stringify({idFirst: idRace, idSecond: idHorse})
    return this.http.put<any>(this._url, body, {'headers':headers});
  }

  postCoefficients(body: any){
    const headers = {
      'content-type': 'application/json'
    };
    let _id = 0;
    let _coefficient = body.value["coefficient"];
    let _coefficientInfo = body.value["info"].split(', ');
    let _idRace = _coefficientInfo[0];
    let _idHorse = _coefficientInfo[1];
    let data = JSON.stringify({id: _id, idRace: _idRace, idHorse: _idHorse, value: _coefficient});
    console.log(data);
    this.http.post<any>(this._url, data, {'headers':headers}).subscribe();
  }

}
