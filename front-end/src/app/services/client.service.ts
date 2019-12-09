import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  url="http://localhost:8081/api/clients";

  constructor(private http:HttpClient,private auth: AuthService) { }

  getAll(){
    let headers = new HttpHeaders({'Authorization':"Bearer "+this.auth.jwt})
    return this.http.get(this.url,{headers:headers});
  }

  getAnalyses(code){
    let headers = new HttpHeaders({'Authorization':"Bearer "+this.auth.jwt})
    return this.http.get(this.url+"/"+code+"/analyses",{headers:headers});
  }

  getClient(code){
    let headers = new HttpHeaders({'Authorization':"Bearer "+this.auth.jwt})
    return this.http.get(this.url+"/"+code,{headers:headers});
  }

  addClient(data,photo){
    let headers = new HttpHeaders({'Authorization':"Bearer "+this.auth.jwt})
    const formData: FormData = new FormData();
    formData.append('photo', photo, photo.name);
    formData.append('code', data.code);
    formData.append('nom', data.nom);
    formData.append('prenom', data.prenom);
    return this.http.post(this.url+"/add", formData,{headers:headers});
  }
  updateClient(data){
    let headers = new HttpHeaders({'Authorization':"Bearer "+this.auth.jwt})
    return this.http.post(this.url,data,{headers:headers});
  }
  deleteClient(code){
    let headers = new HttpHeaders({'Authorization':"Bearer "+this.auth.jwt})
    return this.http.delete(this.url+"/remove/"+code,{headers:headers});
  }

}
