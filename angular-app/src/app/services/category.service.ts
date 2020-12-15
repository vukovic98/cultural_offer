import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { CategoryModel } from '../model/category-model';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CategoryService{

  constructor(private http: HttpClient) { } 

  getCategories(): Observable<Array<CategoryModel>> {
    console.log("getCategories");
    //var headers = new HttpHeaders().set("Authorization", "Bearer " + localStorage.getItem("accessToken"));
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    console.log(headers);
    return this.http.get<Array<CategoryModel>>(environment.apiUrl + 'cultural-offer-categories/', 
                                                {headers: headers});
  }

}
