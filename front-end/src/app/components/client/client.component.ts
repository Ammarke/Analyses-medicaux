import { Component, OnInit, ElementRef } from '@angular/core';
import { ClientService } from 'src/app/services/client.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {
  clients = [];
  form = new FormGroup({
    code: new FormControl('', Validators.required),
    nom: new FormControl('', Validators.required),
    prenom: new FormControl('', Validators.required),
    photoName: new FormControl(null, Validators.required),
  });
  private photo: File | null = null;

  constructor(private clientService : ClientService,private host: ElementRef<HTMLInputElement>) { }
  ngOnInit() {
    this.getAll();
  }
  handleFileInput(files: FileList) {
    this.photo = files.item(0);
}
  getAll(){
    this.clientService.getAll().subscribe(res=>{
      this.clients = res["_embedded"].clients;
    })
  }
  addClient(){
    this.clientService.addClient(this.form.value,this.photo).subscribe(data => {
      Swal.fire({
        icon: 'success',
        title: 'Done...',
        text: 'New Client in the list!',
      })
     }, error => {
       console.log(error)
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Something went wrong!',
      })
      });

   }
  update(id: number, property: string, event: any) {
      if(this.clients[id][property] == event.target.textContent)
        return;
      Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, update it!'
      }).then((result) => {
        if (result.value) {
          this.clients[id][property] = event.target.textContent;
          this.clientService.updateClient(this.clients[id]).subscribe(res=>{
            Swal.fire(
              'Updated!',
              'Your file has been updated.',
              'success'
            )
          },error=>{
            Swal.fire(
              'Erreur!',
              'Your file has not been updated.',
              'error'
            )
          });

        }else{
          event.target.textContent = this.clients[id][property];
        }
      })
    }

    removeClient(code,id) {
        this.clientService.deleteClient(code).subscribe(res=>{
          this.clients.splice(id,1);
          Swal.fire({
            icon: 'success',
            title: 'Done...',
            text: 'New Client in the list!',
          })
         }, error => {
           console.log(error)
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Something went wrong!',
          })
        })
    }


}
