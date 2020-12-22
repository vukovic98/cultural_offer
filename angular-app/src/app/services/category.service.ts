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
  
  constructor(private http: HttpClient) { } 

  getCategories(): Observable<Array<CategoryModel>> {
    console.log("getCategories");
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    console.log(headers);
    return this.http.get<Array<CategoryModel>>(environment.apiUrl + 'cultural-offer-categories/', 
                                                {headers: headers});
  }

  addCategory(category: CategoryModel){
    console.log("add catgegory");
    console.log(category);

    var headers = new HttpHeaders().set("Authorization", "Bearer " + localStorage.getItem("accessToken"));
    this.http.post(environment.apiUrl + this.manageCategoriesEndPoint, category, { headers: headers })
      .pipe(map(response => response))
      .subscribe(response => {
        Swal.fire({
          title: 'Success!',
          text: 'Cultural offer successfully created!',
          icon: 'success',
          confirmButtonText: 'OK'
        });
        return true;
      }, error => {
        Swal.fire({
          title: 'Error!',
          text: 'Something went wrong!',
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'OK'
        });
        return false;
      })
  }
}
