import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { map } from 'rxjs/operators';
import Swal from "sweetalert2";
import {CulturalOffer, OfferDetailsModel, PageObject} from '../model/offer-mode';
import {AddPostModel, PostModel} from '../model/post-model';
import {CommentModel, ImageModel} from '../model/comment-model';
import {DomEvent} from 'leaflet';
import off = DomEvent.off;
import {PageEvent} from '@angular/material/paginator';

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

  getCommentsForOffer(offer_id: number, page: number): Observable<PageObject<CommentModel>> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json',
    });
    return this.http.get<PageObject<CommentModel>>(environment.apiUrl + this.commentsForOfferEndPoint + offer_id + "/" + page);
  }

  getPostsForOffer(offer_id: number, page: number): Observable<PageObject<PostModel>> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json',
    });
    return this.http.get<PageObject<PostModel>>(environment.apiUrl + this.postsForOfferEndPoint + offer_id + "/" + page);
  }

  getByPage(page: number): Observable<PageObject<CulturalOffer>> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    });
    return this.http.get<PageObject<CulturalOffer>>(environment.apiUrl + this.offersPageEndPoint + page, {headers: headers})
  }

  createOffer(offer: any): Observable<any> {
    let headers = new HttpHeaders().set("Authorization", "Bearer " + localStorage.getItem("accessToken"));
    return this.http.post(environment.apiUrl + this.manageOffersEndPoint, offer, { headers: headers })
  }

  updateOffer(offer: CulturalOffer): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.put(environment.apiUrl + this.manageOffersEndPoint + offer.id, offer, { headers: headers })

  }

  deleteOffer(offer_id: number): Observable<any> {
    console.log("delete offer = " + offer_id);
    const headers = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.delete(environment.apiUrl + this.manageOffersEndPoint + offer_id, { headers: headers })
  }

  subscribeUser(offer_id: number): Observable<any> {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    let params = new HttpParams().set('offer_id', offer_id.toString())

    return this.http.post(environment.apiUrl + this.subscribeUserEndPoint, null , {params: params, headers: headers})

  }

  unsubscribeUser(offer_id: number): Observable<any> {
    const headers = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem("accessToken")
    });

    let params = new HttpParams().set('offer_id', offer_id.toString())

    return this.http.delete(environment.apiUrl + this.unsubscribeEndPoint, { params: params, headers: headers })

  }

  getSubscribedItems(): Observable<Array<CulturalOffer>> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get<Array<CulturalOffer>>(environment.apiUrl + this.subscribedItemsEndPoint, { headers: headers })
  }

  addPost(postDto: AddPostModel): Observable<any> {

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.post(environment.apiUrl + this.addPostEndPoint, postDto, {headers: headers})

  }

  getOffer(id: string): Observable<OfferDetailsModel> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get<OfferDetailsModel>(environment.apiUrl + this.offerDetailsById + id, { headers: headers })
      .pipe(map((response) => response));
  }

  getByPageFilter(page: number, exp: string, types: string[]): Observable<PageObject<CulturalOffer>> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept'       : 'application/json'
    });

    // @ts-ignore
    let params = new HttpParams().set('expression', exp).set('types', types);
    return this.http.get<PageObject<CulturalOffer>>(environment.apiUrl + this.offersPageEndPointFilter + page, {params: params,headers: headers})
  }

  gradeOffer(userId: number, offerId: number, value: number): Observable<any> {
    let gradeObj = {
      "value": value,
      "user": { "id": userId },
      "culturalOffer": { "id": offerId }
    }

    let headers = new HttpHeaders().set("Authorization", "Bearer " + localStorage.getItem("accessToken"));
    return this.http.post(environment.apiUrl + "grades", gradeObj, { headers: headers })

  }

  addComment(offerId: number,commentText: string,image: any): Observable<any>{
    let newCommentDto = {
      "offerId": offerId,
      "content": commentText,
      "image": image
    }

    let headers = new HttpHeaders().set("Authorization", "Bearer " + localStorage.getItem("accessToken"));
    return this.http.post(environment.apiUrl + "comments", newCommentDto, { headers: headers })

  }

  deleteComment(commentId: number): Observable<any>{

    let headers = new HttpHeaders().set("Authorization", "Bearer " + localStorage.getItem("accessToken"));
    return this.http.delete(environment.apiUrl + "comments/"+commentId, { headers: headers })

  }

  deletePost(postId : number): Observable<any>{

    let headers = new HttpHeaders().set("Authorization", "Bearer " + localStorage.getItem("accessToken"));
    return this.http.delete(environment.apiUrl + "posts/"+postId, { headers: headers })

  }
}
