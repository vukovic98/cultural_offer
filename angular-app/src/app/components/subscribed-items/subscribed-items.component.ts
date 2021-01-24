import { Component, OnInit } from '@angular/core';
import {CulturalOfferService} from '../../services/culturalOffer.service';
import {CulturalOffer} from '../../model/offer-mode';

@Component({
  selector: 'app-subscribed-items',
  templateUrl: './subscribed-items.component.html',
  styleUrls: ['./subscribed-items.component.css']
})
export class SubscribedItemsComponent implements OnInit {

  private offers: Array<CulturalOffer> = [];
  constructor(private service: CulturalOfferService) { }

  ngOnInit(): void {
    this.service.getSubscribedItems().subscribe((data) => {
      this.offers = data;
    });
  }

  removeOffer(id: number) {
   this.service.unsubscribeUser(id);
   this.offers = this.offers.filter(item => item.id != id);
  }

  getSubscribedItems(): any {
    return this.offers;
  }

  getUserName(): any {
    let token = localStorage.getItem("accessToken");
    let payload: string = '';
    if (token) {
      payload = token.split(".")[1];
      payload = window.atob(payload);
      return JSON.parse(payload).user_firstName;
    }
  }

}
