import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2'
import { FormGroup, FormControl } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form = new FormGroup({
    'username': new FormControl(),
    'password': new FormControl()
  })
  constructor(private auth:AuthService,private router: Router) { }
  header;
  ngOnInit() {
    if(this.auth.isAuthentificed())
      this.router.navigate(["/clients"]);
  }
  login(){
      this.auth.login(this.form.value)
                  .subscribe(res=>{
                    this.header = res;
                    let jwt = res.headers.get("Authorization")
                    this.auth.saveJwt(jwt)
                    Swal.fire({
                      icon: 'success',
                      title: 'Done...',
                      text: 'Welcome!',
                      timer: 2000,
                      showConfirmButton: false,


                    })
                    this.router.navigate(["/clients"])
                  },error=>{
                    Swal.fire({
                      icon: 'error',
                      title: 'error...',
                      text: 'Try it again',
                      timer: 2000,

                    })
                  })
  }
}
