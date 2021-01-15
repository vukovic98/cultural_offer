import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TypeModel, AllTypesModel } from '../model/type-model';
import { environment } from '../../environments/environment';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TypeService {

  private readonly manageTypesEndPoint = "cultural-offer-types/";
  private readonly manageTypesEndPointPage = "cultural-offer-types/byPage/";
  private readonly manageTypesEndPointByName = "cultural-offer-types/name/";

  constructor(private http: HttpClient) { }

  save(type: TypeModel): Observable<any>{
    let headers = new HttpHeaders().set("Authorization", "Bearer " + localStorage.getItem("accessToken"));

    return this.http.post(environment.apiUrl + this.manageTypesEndPoint, type, { headers: headers })

  }

  getByPage(page: number): any {
    const headers = new HttpHeaders().set("Authorization", "Bearer " + localStorage.getItem("accessToken"));

    return this.http.get(environment.apiUrl + this.manageTypesEndPointPage + page, { headers: headers });
  }

  getTypesForCategory(id: string): Observable<Array<TypeModel>> {
    let headers = new HttpHeaders().set("Authorization", "Bearer " + localStorage.getItem("accessToken"));

    return this.http.get<Array<TypeModel>>(environment.apiUrl + this.manageTypesEndPoint + 'byCategory/' + id, { headers: headers });
  }

  deleteType(id: number): Observable<Array<TypeModel>> {
    let headers = new HttpHeaders().set("Authorization", "Bearer " + localStorage.getItem("accessToken"));

    return this.http.delete<Array<TypeModel>>(environment.apiUrl + this.manageTypesEndPoint + id, { headers: headers })

  }

  getByName(name: string): Observable<AllTypesModel> {
    const headers = new HttpHeaders({'Content-Type': 'application/json','Accept': 'application/json'});

    return this.http.get<AllTypesModel>(environment.apiUrl + this.manageTypesEndPointByName + name, { headers: headers})
    .pipe(map(response => response));
  }

  updateType(type: AllTypesModel): Observable<any>{
    let headers = new HttpHeaders().set("Authorization", "Bearer " + localStorage.getItem("accessToken"));

    return this.http.put(environment.apiUrl + this.manageTypesEndPoint + type.id, type, { headers: headers })

  }
}
