import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs";
import {IRace, Race} from "./race";

@Injectable()
export class RaceService {

  _url : string = 'http://localhost:8088/races/';

  constructor(private http: HttpClient) {}

//Method used to get bets
  fetchRaces(){
    return this.http.get<Race[]>(this._url);
  }
//Method used to post bets
  postRace(data: any): void{
    this.http.post(this._url, data);
  }
}
