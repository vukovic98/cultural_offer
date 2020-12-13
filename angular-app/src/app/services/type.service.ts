import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TypeModel } from '../model/type-model';

@Injectable({
  providedIn: 'root'
})
export class TypeService {

  constructor(private http: HttpClient) { } 

  getTypesForCategory(id: string): Observable<Array<TypeModel>> {
    var headers = new HttpHeaders().set("Authorization", "Bearer " + localStorage.getItem("accessToken"));
    return this.http.get<Array<TypeModel>>('http://localhost:8080/cultural-offer-types/byCategory/' + id, {headers: headers});
  }

}
