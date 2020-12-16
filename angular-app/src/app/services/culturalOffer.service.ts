import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {map} from 'rxjs/operators';
import Swal from "sweetalert2";
import { CulturalOffer } from '../model/offer-mode';

@Injectable()
export class CulturalOfferService {
  private readonly offersPageEndPoint = "culturalOffers/by-page/";
  private readonly subscribedItemsEndPoint = "registeredUser/subscribedItems";
  private readonly unsubscribeEndPoint = "registeredUser/unsubscribe";
  private readonly manageOffersEndPoint = "culturalOffers/";
  private readonly subscribeUserEndPoint = "registeredUser/subscribe";

  constructor(private http: HttpClient) {
  }


  getByPage(page: number):any {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept'       : 'application/json'
    });

    return this.http.get(environment.apiUrl + this.offersPageEndPoint + page, {headers: headers})
      .pipe(map((response) => JSON.stringify(response)));
  }

  updateOffer(offer: CulturalOffer){
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    this.http.put(environment.apiUrl + this.manageOffersEndPoint + offer.id, offer, {headers: headers})
      .pipe(map(response => response))
      .subscribe(response => {
        Swal.fire({
          title: 'Success!',
          text: 'Cultural offer successfully updated!',
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

  subscribeUser(offer_id: number): void {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    let params = new HttpParams().set('offer_id', offer_id.toString())

    this.http.post(environment.apiUrl + this.subscribeUserEndPoint, null , {params: params, headers: headers})
      .pipe(map(response => response))
      .subscribe(response => {
        Swal.fire({
          title: 'Success!',
          text: 'Successfully subscribed to offer! Now you will get all the latest updates!',
          icon: 'success',
          confirmButtonText: 'OK'
        });
      }, error => {
        Swal.fire({
          title: 'Error!',
          text: 'Something went wrong!',
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'OK'
        });
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
      }, error => {
        Swal.fire({
          title: 'Error!',
          text: 'Something went wrong!',
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'OK'
        });
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
