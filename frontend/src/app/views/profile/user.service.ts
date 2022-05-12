import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs";


@Injectable()
export class UserService {

  _url : string = 'http://localhost:8088/users/';

  constructor(private http: HttpClient) {}

  //Method used to get user
  fetchUser(userName: string){
    let url = this._url + userName;
    return this.http.get<any>(url);
  }

  //Method used to update balance
  updateBalance(data: any): void{
    const headers = {
      'content-type': 'application/json'
    };
    let bet = data.value["bet"];
    let betInfo = data.value["info"].split(', ');
    let idUser = betInfo[1];
    let body = JSON.stringify({idFirst: idUser, idSecond: bet});
    this.http.post<any>(this._url, body, {'headers':headers}).subscribe();
  }
}
