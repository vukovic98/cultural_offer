import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {Observable} from "rxjs";



@Injectable()
export class FilterOffersService{
  private  readonly getTypesPath = "cultural-offer-types/getAll";


  constructor(private http: HttpClient) {}

  getTypes(): Observable<String[]>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get<String[]>(environment.apiUrl + this.getTypesPath, {headers: headers});
  }

}

