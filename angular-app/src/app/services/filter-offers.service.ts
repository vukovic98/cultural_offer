import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import Swal from 'sweetalert2';
import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {map} from "rxjs/operators";
import {userDto} from "../model/userDto";



@Injectable()
export class FilterOffersService{
  private  readonly getTypesPath = "cultural-offer-types/getAll";


  constructor(private http: HttpClient, private route: Router) {}

  getTypes(): any{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get<[]>(environment.apiUrl + this.getTypesPath, {headers: headers})
      .pipe(map(response => response))
  }
  filterOffers(): any{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get<String[]>(environment.apiUrl + this.getTypesPath, {headers: headers})
      .pipe(map(response => response))
  }



}

