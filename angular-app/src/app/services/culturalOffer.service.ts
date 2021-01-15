import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { map } from 'rxjs/operators';
import Swal from "sweetalert2";
import { CulturalOffer } from '../model/offer-mode';
import {AddPostModel} from '../model/post-model';

@Injectable()
export class CulturalOfferService {
  private readonly offersPageEndPoint = "culturalOffers/by-page/";
  private readonly subscribedItemsEndPoint = "registeredUser/subscribedItems";
  private readonly unsubscribeEndPoint = "registeredUser/unsubscribe";
  private readonly manageOffersEndPoint = "culturalOffers/";
  private readonly offerDetailsById = "culturalOffers/detail/";
  private readonly subscribeUserEndPoint = "registeredUser/subscribe";
  private readonly offersPageEndPointFilter = "culturalOffers/filter/";
  private readonly addPostEndPoint = "posts/";
  private readonly commentsForOfferEndPoint = "comments/for-offer/";
  private readonly postsForOfferEndPoint = "posts/for-offer/";

  constructor(private http: HttpClient) {
  }

  getLocationName(location: any): Observable<any>{
    return this.http.get('https://us1.locationiq.com/v1/reverse.php?key=pk.fc61974e4d8d7d9a2df2bdc98b5ad87e&format=json&lat='+location.lat+'&lon='+location.lng)
  }

  getCommentsForOffer(offer_id: number, page: number): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json',
    });
    return this.http.get(environment.apiUrl + this.commentsForOfferEndPoint + offer_id + "/" + page);
  }

  getPostsForOffer(offer_id: number, page: number): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json',
    });
    return this.http.get(environment.apiUrl + this.postsForOfferEndPoint + offer_id + "/" + page);
  }

  getByPage(page: number): any {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    });
    return this.http.get(environment.apiUrl + this.offersPageEndPoint + page, {headers: headers})
      .pipe(map((response) => JSON.stringify(response)));
  }

  createOffer(offer: any) {
    var headers = new HttpHeaders().set("Authorization", "Bearer " + localStorage.getItem("accessToken"));
    this.http.post(environment.apiUrl + this.manageOffersEndPoint, offer, { headers: headers })
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
          text: 'Image size must be less than 64Kb!',
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'OK'
        });
        return false;
      })

  }

  updateOffer(offer: CulturalOffer) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("accessToken")
    });
    this.http.put(environment.apiUrl + this.manageOffersEndPoint + offer.id, offer, { headers: headers })
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
      'Authorization': 'Bearer ' + localStorage.getItem("accessToken")
    });

    this.http.delete(environment.apiUrl + this.manageOffersEndPoint + offer_id, { headers: headers })
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
      'Authorization': 'Bearer ' + localStorage.getItem("accessToken")
    });

    let params = new HttpParams().set('offer_id', offer_id.toString())

    this.http.delete(environment.apiUrl + this.unsubscribeEndPoint, { params: params, headers: headers })
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
      'Accept': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.apiUrl + this.subscribedItemsEndPoint, { headers: headers })
      .pipe(map((response) => JSON.stringify(response)));
  }

  addPost(postDto: AddPostModel, update: Function) {

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("accessToken")
    });
    console.log("add post = ", postDto);

    this.http.post(environment.apiUrl + this.addPostEndPoint, postDto, {headers: headers})
      .pipe(map((response) => response))
      .subscribe(response => {
        Swal.fire({
          title: 'Success!',
          text: 'Post added successfully! ',
          icon: 'success',
          confirmButtonText: 'OK'
        });
        update();
        return true;
      }, error => {
        Swal.fire({
          title: 'Error!',
          text: 'Something went wrong! ' + error.error,
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'OK'
        });
        return false;
      });
  }


  getOffer(id: string): any {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.apiUrl + this.offerDetailsById + id, { headers: headers })
      .pipe(map((response) => response));
  }

  getByPageFilter(page: number, exp: string, types: string[]):any {
    console.log(exp,types)
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept'       : 'application/json'
    });

    // @ts-ignore
    let params = new HttpParams().set('expression', exp).set('types', types);
    return this.http.get(environment.apiUrl + this.offersPageEndPointFilter + page, {params: params,headers: headers})
      .pipe(map((response) => JSON.stringify(response)));
  }

  gradeOffer(userId: number, offerId: number, value: number) {
    let gradeObj = {
      "value": value,
      "user": { "id": userId },
      "culturalOffer": { "id": offerId }
    }

    let headers = new HttpHeaders().set("Authorization", "Bearer " + localStorage.getItem("accessToken"));
    this.http.post(environment.apiUrl + "grades", gradeObj, { headers: headers })
      .pipe(map((response) => response)).subscribe(response => {
        return true;
      }, error => {
        Swal.fire({
          title: 'Error!',
          text: 'Something went wrong! ' + error.error,
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'OK'
        });
        return false;
      });
  }
}
