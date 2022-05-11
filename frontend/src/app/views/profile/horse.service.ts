import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs";

@Injectable()
export class HorseService {

  constructor(private http: HttpClient) {}
  _url : string = 'http://localhost:8088/horses/';

  fetchHorses(): Observable<Object> {
    return this.http.get(this._url);
  }

  postHorse(data: any): void{
    this.http.post(this._url+data, data);
  }
}
