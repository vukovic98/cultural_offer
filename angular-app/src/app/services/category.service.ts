import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CategoryModel } from '../model/category-model';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import {PageObject} from '../model/offer-mode';

@Injectable({
  providedIn: 'root'
})
export class CategoryService{

  private readonly manageCategoriesEndPoint = "cultural-offer-categories/";
  private readonly manageCategoriesEndPointByName = "cultural-offer-categories/name/";

  constructor(private http: HttpClient) { }

  getCategories(): Observable<Array<CategoryModel>> {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get<Array<CategoryModel>>(environment.apiUrl + this.manageCategoriesEndPoint,
                                                {headers: headers});
  }

  getCategoriesByPage(page: number): Observable<PageObject<CategoryModel>>{
    const headers = new HttpHeaders({'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")});
    return this.http.get<PageObject<CategoryModel>>(environment.apiUrl + this.manageCategoriesEndPoint + 'by-page/'+page, {headers: headers});
  }

  getByName(name: string): Observable<CategoryModel> {
    const headers = new HttpHeaders({'Content-Type': 'application/json','Accept': 'application/json'});

    return this.http.get<CategoryModel>(environment.apiUrl + this.manageCategoriesEndPointByName + name, { headers: headers})
  }

  deleteCategory(id: number): Observable<string>{
    let headers = new HttpHeaders().set("Authorization", "Bearer " + localStorage.getItem("accessToken"));
    return this.http.delete<string>(environment.apiUrl + this.manageCategoriesEndPoint + id , { headers: headers });
  }

  updateCategory(category: CategoryModel): Observable<any>{
    let headers = new HttpHeaders().set("Authorization", "Bearer " + localStorage.getItem("accessToken"));
    return this.http.put(environment.apiUrl + this.manageCategoriesEndPoint + category.id, category, { headers: headers })
                .pipe(map(response => response));
  }

  addCategory(category: CategoryModel): Observable<any>{
    let headers = new HttpHeaders().set("Authorization", "Bearer " + localStorage.getItem("accessToken"));
    return this.http.post(environment.apiUrl + this.manageCategoriesEndPoint, category, { headers: headers })
  }
}
