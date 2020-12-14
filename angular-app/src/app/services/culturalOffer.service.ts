import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map} from 'rxjs/operators';

@Injectable()
export class CulturalOfferService {
  private readonly offesrsPageEndPoint = "culturalOffers/by-page/";
  private readonly subscribedItemsEndPoint = "registeredUser/subscribedItems";

  constructor(private http: HttpClient) {
  }


  getByPage(page: number):any {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept'       : 'application/json',
    });

    return this.http.get(environment.apiUrl + this.offesrsPageEndPoint + page, {headers: headers})
      .pipe(map((response) => JSON.stringify(response)));
  }

  getSubscribedItems(): any {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept'       : 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.apiUrl + this.subscribedItemsEndPoint, {headers: headers})
      .pipe(map((response) => JSON.stringify(response)));
  }

}
