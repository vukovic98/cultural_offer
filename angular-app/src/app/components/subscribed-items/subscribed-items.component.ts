import { Component, OnInit } from '@angular/core';
import {CulturalOfferService} from '../../services/culturalOffer.service';

@Component({
  selector: 'app-subscribed-items',
  templateUrl: './subscribed-items.component.html',
  styleUrls: ['./subscribed-items.component.css']
})
export class SubscribedItemsComponent implements OnInit {

  private offers: Array<object> = [];
  constructor(private service: CulturalOfferService) { }

  ngOnInit(): void {
    this.service.getSubscribedItems().subscribe((data: string) => {
      console.log(data);
      this.offers = JSON.parse(data);
    });
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
