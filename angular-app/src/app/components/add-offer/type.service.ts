import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { TypeModel } from './type-model';

@Injectable({
  providedIn: 'root'
})
export class TypeService {

  constructor(private http: HttpClient) { } 

  getTypesForCategory(id: string): Observable<Array<TypeModel>> {
    return this.http.get<Array<TypeModel>>('http://localhost:8080/cultural-offer-types/byCategory/' + id);
  }

}
