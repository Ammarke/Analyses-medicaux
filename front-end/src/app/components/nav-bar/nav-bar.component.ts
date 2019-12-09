import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor(public authSer:AuthService,private router: Router) { }
  admin;
  ngOnInit() {
  this.authSer.loadToken();

  if(!this.authentificed)
    this.router.navigate(["/login"])

  this.admin = this.authSer.isAdmin();
  }

  logout(){
    this.authSer.logout()
    this.router.navigate(["/login"])
  }
  authentificed(){
    return this.authSer.isAuthentificed();
  }

}

