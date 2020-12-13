import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { CategoryModel } from './category-model';

@Injectable({
  providedIn: 'root'
})
export class CategoryService{

  constructor(private http: HttpClient) { } 

  getCategories(): Observable<Array<CategoryModel>> {
    return this.http.get<Array<CategoryModel>>('http://localhost:8080/cultural-offer-categories/');
  }

}
