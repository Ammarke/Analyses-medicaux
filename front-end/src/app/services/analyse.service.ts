import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AnalyseService {
  url="http://localhost:8081/api/analyses";

  constructor(private http:HttpClient,private auth: AuthService) { }

  addAnalyse(client,analyse){
    let headers = new HttpHeaders({'Authorization':"Bearer "+this.auth.jwt})
    analyse.client = client;
    return this.http.post(this.url+"/add",analyse,{headers:headers});
  }
}
