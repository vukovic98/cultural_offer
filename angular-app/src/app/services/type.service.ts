import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TypeModel } from '../model/type-model';
import { environment } from '../../environments/environment';
import { map } from 'rxjs/operators';
import Swal from "sweetalert2";

@Injectable({
  providedIn: 'root'
})
export class TypeService {

  private readonly manageTypesEndPoint = "cultural-offer-types/";

  constructor(private http: HttpClient) { }

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
}
