import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TypeModel, AllTypesModel } from '../model/type-model';
import { environment } from '../../environments/environment';
import { map } from 'rxjs/operators';
import Swal from "sweetalert2";

@Injectable({
  providedIn: 'root'
})
export class TypeService {

  private readonly manageTypesEndPoint = "cultural-offer-types/";
  private readonly manageTypesEndPointPage = "cultural-offer-types/byPage/";

  constructor(private http: HttpClient) { }

  save(type: TypeModel, updateTable: Function){
    var headers = new HttpHeaders().set("Authorization", "Bearer " + localStorage.getItem("accessToken"));
    this.http.post(environment.apiUrl + this.manageTypesEndPoint, type, { headers: headers })
      .pipe(map(response => response))
      .subscribe(response => {
        updateTable();
        Swal.fire({
          title: 'Success!',
          text: 'Type successfully created!',
          icon: 'success',
          confirmButtonText: 'OK'
        });
        return true;
      }, error => {
        Swal.fire({
          title: 'Error!',
          text: 'Something went wrong! Type already exists',
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'OK'
        });
        return false;
      })
  }
  getByPage(page: number): any {
    var headers = new HttpHeaders().set("Authorization", "Bearer " + localStorage.getItem("accessToken"));

    /*return this.http.get(environment.apiUrl + this.manageTypesEndPointPage + page, { headers: headers })
      .pipe(map((response) => {
        console.log("response = ");
        console.log(response);
        response
      }));*/

    return this.http.get(environment.apiUrl + this.manageTypesEndPoint + 'byPage/1', { headers: headers });
  }

  /*getAllTypes(): Observable<Array<AllTypesModel>>{
    var headers = new HttpHeaders().set("Authorization", "Bearer " + localStorage.getItem("accessToken"));
    return this.http.get<Array<AllTypesModel>>(environment.apiUrl + this.manageTypesEndPoint + 'byPage/1', { headers: headers });
  }*/

  getTypesForCategory(id: string): Observable<Array<TypeModel>> {
    var headers = new HttpHeaders().set("Authorization", "Bearer " + localStorage.getItem("accessToken"));
    return this.http.get<Array<TypeModel>>(environment.apiUrl + this.manageTypesEndPoint + 'byCategory/' + id, { headers: headers });
  }

  deleteType(id: number, updateTable: Function) {
    var headers = new HttpHeaders().set("Authorization", "Bearer " + localStorage.getItem("accessToken"));
    this.http.delete<Array<TypeModel>>(environment.apiUrl + this.manageTypesEndPoint + id, { headers: headers })
      .pipe(map(response => response))
      .subscribe(response => {
        updateTable();
        Swal.fire({
          title: 'Success!',
          text: 'Type successfully deleted!',
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

  updateType(type: AllTypesModel, updateTable: Function){
    console.log("update type");
    console.log(type);
    var headers = new HttpHeaders().set("Authorization", "Bearer " + localStorage.getItem("accessToken"));
    this.http.put(environment.apiUrl + this.manageTypesEndPoint + type.id, type, { headers: headers })
    .pipe(map(response => response))
    .subscribe(response => {
      updateTable();
      Swal.fire({
        title: 'Success!',
        text: 'Type successfully updated!',
        icon: 'success',
        confirmButtonText: 'OK'
      });
      return true;
    }, error => {
      Swal.fire({
        title: 'Error!',
        text: 'Something went wrong! Type name already exists',
        icon: 'error',
        confirmButtonColor: '#DC143C',
        confirmButtonText: 'OK'
      });
      return false;
    })
  }
}
