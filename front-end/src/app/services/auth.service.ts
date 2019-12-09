import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { JwtHelperService} from "@auth0/angular-jwt"
import { Router } from '@angular/router';
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  url="http://localhost:8080"
  jwt;
  username;
  roles=[];
  header;
  constructor(private http: HttpClient,private router:Router) { }

  login(data){
      return this.http.post(this.url+"/login",data, {observe: 'response'})
  }
  saveJwt(jwt){
      localStorage.setItem("token",jwt)
      this.jwt = jwt;
      this.parseJWT();
  }
  parseJWT(){
    let jwtHealper = new JwtHelperService();
    let jwtObject = jwtHealper.decodeToken(this.jwt)
    this.username = jwtObject.obj
    this.roles = jwtObject.roles;
  }
  isAdmin(){
    return this.roles.indexOf("ADMIN")>=0
  }
  isUser(){
    return this.roles.indexOf("USER")>=0
  }
  isAuthentificed(){
    return this.roles && (this.isAdmin() || this.isUser())
  }
  loadToken(){
    if(localStorage.getItem("token")){
      this.jwt = localStorage.getItem("token")
      this.parseJWT();
    } else this.router.navigate(["/login"])
  }
  logout(){
    localStorage.removeItem("token");
    this.jwt = null;
    this.username = null;
    this.roles = null;
  }
}
