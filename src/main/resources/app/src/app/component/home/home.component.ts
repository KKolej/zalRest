import { myDocument } from './../../model/myDocument';
import { Component, OnInit } from '@angular/core';
import { ConnectionService } from 'src/app/services/connection.service';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  stringResult: string = ""
  documents: myDocument[] = []
  documentForm: FormGroup
  name: FormControl
  pages: FormControl
  owner: FormControl

  constructor(private connectionService: ConnectionService) { }

  ngOnInit(): void {
    this.name = new FormControl('', Validators.required)
    this.pages = new FormControl('', Validators.required)
    this.owner = new FormControl('', Validators.required)
        
    this.documentForm = new FormGroup({
      name: this.name,
      pages: this.pages,
      owner: this.owner
    })
  }

  saveDocument(value: any): void {
    this.connectionService.saveDocument(value).subscribe(r => {
      console.log(r)
    })
  }

  getSimpleString(): void {
    this.connectionService.getString().subscribe(r => {
      this.stringResult = r
    })
  }

  getDocumentsList(): void {
    this.connectionService.getDocumentList().subscribe(r => {
      this.documents = r
    })
  }
}
