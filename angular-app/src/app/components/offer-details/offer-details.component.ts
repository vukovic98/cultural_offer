import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { CulturalOfferService } from '../../services/culturalOffer.service';
import {CulturalOffer, OfferDetailsModel, OfferModel} from '../../model/offer-mode';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import {AuthService} from '../../services/auth.service';
import {MatSlideToggleChange} from '@angular/material/slide-toggle';

@Component({
  selector: 'app-offer-details',
  templateUrl: './offer-details.component.html',
  styleUrls: ['./offer-details.component.css']
})
export class OfferDetailsComponent implements OnInit {
  stars: number[] = [1, 2, 3, 4, 5];
  selectedValue: number = 0;
  id: string = '';
  offer!: OfferDetailsModel;
  private subscribedItems: Array<CulturalOffer> = [];

  constructor(private route: ActivatedRoute,
    private service: CulturalOfferService,
    private authService: AuthService) { }

  ngOnInit() {
    if(this.authService.isUser()) {
      this.service.getSubscribedItems().subscribe((data: string) => {
        this.subscribedItems = JSON.parse(data);
      })
    }
    //@ts-ignore
    this.id = this.route.snapshot.paramMap.get('id');
    console.log(this.id);
    this.service.getOffer(this.id).subscribe((data: OfferDetailsModel) => {
      this.offer = data;
      console.log(this.offer);
    }, (error: any) => {
      console.log(error);
    });
  }

  countStar(star: any) {
    if (this.selectedValue == 0) {
      this.selectedValue = star;
      console.log('Value of star', star);
      let userId = this.authService.getUserId();
      if(userId == "-1"){return;}
      let offerId = this.offer.id;
      this.service.gradeOffer(parseInt(userId), parseInt(offerId), this.selectedValue);
    } else {
      console.log('already voted');
    }
  }

  subscribeToggle(event: MatSlideToggleChange, offer_id: string) {
    let id: number = Number(offer_id);
    if(event.checked) { // subscribe to offer
      this.service.subscribeUser(id);
    }
    else {//unsubscribe from offer
      this.service.unsubscribeUser(id);
    }
  }

  isUser(): boolean {
    return this.authService.isUser();
  }

  isSubscribed(offer: OfferDetailsModel): boolean {
    if(this.authService.isLoggedIn()) {
      for (let i of this.subscribedItems) {
        if (i.id === Number(offer.id))
          return true;
      }
    }
    return false;
  }
}
