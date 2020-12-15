import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {map} from 'rxjs/operators';
import Swal from "sweetalert2";

@Injectable()
export class CulturalOfferService {
  private readonly offesrsPageEndPoint = "culturalOffers/by-page/";
  private readonly subscribedItemsEndPoint = "registeredUser/subscribedItems";
  private readonly unsubscribeEndPoint = "registeredUser/unsubscribe";
  private readonly  manageOffersEndPoint = "culturalOffers/";

  constructor(private http: HttpClient) {
  }


  getByPage(page: number):any {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept'       : 'application/json'
    });

    return this.http.get(environment.apiUrl + this.offesrsPageEndPoint + page, {headers: headers})
      .pipe(map((response) => JSON.stringify(response)));
  }

  deleteOffer(offer_id: number): void {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    this.http.delete(environment.apiUrl + this.manageOffersEndPoint + offer_id, {headers: headers})
      .pipe(map(response => response))
      .subscribe(response => {
        Swal.fire({
          title: 'Success!',
          text: 'Cultural offer successfully deleted!',
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

  unsubscribeUser(offer_id: number): void {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    let params = new HttpParams().set('offer_id', offer_id.toString())

    this.http.delete(environment.apiUrl + this.unsubscribeEndPoint, {params: params, headers:headers})
      .pipe(map(response => response))
      .subscribe(response => {
        Swal.fire({
          title: 'Success!',
          text: 'Successfully unsubscribed from offer!',
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
