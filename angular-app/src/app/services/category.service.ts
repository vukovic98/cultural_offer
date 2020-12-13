import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { CategoryModel } from '../model/category-model';

@Injectable({
  providedIn: 'root'
})
export class CategoryService{

  constructor(private http: HttpClient) { } 

  getCategories(): Observable<Array<CategoryModel>> {
    var headers = new HttpHeaders().set("Authorization", "Bearer " + localStorage.getItem("accessToken"));
    return this.http.get<Array<CategoryModel>>('http://localhost:8080/cultural-offer-categories/', 
                                                {headers: headers});
  }

}
