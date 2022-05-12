import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {KeycloakService} from "keycloak-angular";

@Injectable()
export class MakeResultService {

  constructor(private keycloakService: KeycloakService, private http: HttpClient) {}
  _url : string = 'http://localhost:8088/make-result/';

  //Used to make a result for bet
  postHorse(data: any): void {
    const headers = {
      'content-type': 'application/json'
    };
    let array = data.value["result"].split(', ');
    let body = JSON.stringify({idFirst: array[0], idSecond: array[1]})
    console.log(body);
    this.http.post<any>(this._url, body, {'headers':headers}).subscribe();
  }
}
