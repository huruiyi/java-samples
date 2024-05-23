import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-pet-list',
  templateUrl: './pet-list.component.html',
  styleUrls: [ './pet-list.component.css' ]
})
export class PetListComponent implements OnInit {
  pets: Object;

  constructor(private router: Router, private http: HttpClient) {
  }

  ngOnInit() {
    this.pets = this.router.getNavigatedData();
    if (!this.pets) {
      this.http.get('http://localhost:8080/petStore/pets', {
        withCredentials: true
      }).subscribe(
        data => {
          this.pets = data;
        }
      );
    }

  }
}
