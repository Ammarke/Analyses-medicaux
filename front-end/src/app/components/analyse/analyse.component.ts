import { Component, OnInit } from '@angular/core';
import { ClientService } from 'src/app/services/client.service';
import { ActivatedRoute } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
import { AnalyseService } from 'src/app/services/analyse.service';

@Component({
  selector: 'app-analyse',
  templateUrl: './analyse.component.html',
  styleUrls: ['./analyse.component.css']
})
export class AnalyseComponent implements OnInit {

  client={
    code:"",
    nom:"",
    prenom:"",
    photo:"",
  };
  form = new FormGroup({
    identifiant: new FormControl('', Validators.required),
    nom: new FormControl('', Validators.required),
    date: new FormControl('', Validators.required),
    prix: new FormControl('', Validators.required),
    resultat: new FormControl('', Validators.required),
    status: new FormControl('', Validators.required),
  });
  analyses;
  code;
  constructor(private clientService : ClientService,private route:ActivatedRoute,private analyseService:AnalyseService) { }
  ngOnInit() {
    this.code = this.route.snapshot.params.code;
    this.getAll(this.code);
    this.getClient(this.code);
  }
  getAll(code){
    this.clientService.getAnalyses(code).subscribe(res=>{
      this.analyses = res["_embedded"].analyses;
    })
  }

  getClient(code){
    this.clientService.getClient(code).subscribe(res=>{
      this.client.nom = res['nom'];
      this.client.prenom =  res['prenom'];
      this.client.code =  res['code'];
      this.client.photo = res['photo'];
    })
  }

  update(id: number, property: string, event: any) {
      const editField = event.target.textContent;
      console.log(this.analyses[id][property])
      console.log(editField)
    }

    remove(id: any) {
    }

    addAnalyse(){
        this.analyseService.addAnalyse(this.client,this.form.value).subscribe(res=>{
          this.form.clearValidators();
          this.form.reset();
          Swal.fire({
            icon: 'success',
            title: 'Done...',
            text: 'New Analyse in the list!',
          })
        },error=>{
          Swal.fire({
            icon: 'error',
            title: 'sorry ...',
            text: 'error, try Again',
          })
        })
    }
}
