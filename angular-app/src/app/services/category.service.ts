import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CategoryModel } from '../model/category-model';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { map } from 'rxjs/operators';
import Swal from "sweetalert2";

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

  getCategoriesByPage(page: number): Observable<Array<CategoryModel>>{
    const headers = new HttpHeaders({'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")});
    return this.http.get<Array<CategoryModel>>(environment.apiUrl + this.manageCategoriesEndPoint + 'by-page/'+page, {headers: headers});
  }

  getByName(name: string): Observable<CategoryModel> {
    const headers = new HttpHeaders({'Content-Type': 'application/json','Accept': 'application/json'});

    return this.http.get<CategoryModel>(environment.apiUrl + this.manageCategoriesEndPointByName + name, { headers: headers})
    .pipe(map(response => response));
  }

  deleteCategory(id: number, updateTable: Function){
    let headers = new HttpHeaders().set("Authorization", "Bearer " + localStorage.getItem("accessToken"));
    this.http.delete(environment.apiUrl + this.manageCategoriesEndPoint + id , { headers: headers })
      .pipe(map(response => response))
      .subscribe(response => {
        updateTable();
        Swal.fire({
          title: 'Success!',
          text: 'Category successfully deleted!',
          icon: 'success',
          confirmButtonText: 'OK'
        });
        return true;
      }, error => {
        console.log(error);
        Swal.fire({
          title: 'Error!',
          text: 'Something went wrong! ' + error.error,
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'OK'
        });
        return false;
      })
  }

  updateCategory(category: CategoryModel, updateTable: Function){
    console.log("update category");
    console.log(category);
    var headers = new HttpHeaders().set("Authorization", "Bearer " + localStorage.getItem("accessToken"));
    this.http.put(environment.apiUrl + this.manageCategoriesEndPoint + category.id, category, { headers: headers })
    .pipe(map(response => response))
    .subscribe(response => {
      updateTable();
      Swal.fire({
        title: 'Success!',
        text: 'Category successfully updated!',
        icon: 'success',
        confirmButtonText: 'OK'
      });
      return true;
    }, error => {
      Swal.fire({
        title: 'Error!',
        text: 'Something went wrong! Category name already exists',
        icon: 'error',
        confirmButtonColor: '#DC143C',
        confirmButtonText: 'OK'
      });
      return false;
    })
  }

  addCategory(category: CategoryModel, updateTable: Function){
    console.log("add catgegory");
    console.log(category);

    var headers = new HttpHeaders().set("Authorization", "Bearer " + localStorage.getItem("accessToken"));
    this.http.post(environment.apiUrl + this.manageCategoriesEndPoint, category, { headers: headers })
      .pipe(map(response => response))
      .subscribe(response => {
        updateTable();
        Swal.fire({
          title: 'Success!',
          text: 'Category successfully created!',
          icon: 'success',
          confirmButtonText: 'OK'
        });
        return true;
      }, error => {
        Swal.fire({
          title: 'Error!',
          text: 'Something went wrong! Category already exist',
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'OK'
        });
        return false;
      })
  }
}
