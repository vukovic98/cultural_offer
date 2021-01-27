import { Component, OnInit } from '@angular/core';
import {CulturalOfferService} from '../../services/culturalOffer.service';
import {CulturalOffer} from '../../model/offer-mode';
import Swal from "sweetalert2";

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
   this.service.unsubscribeUser(id).subscribe((data) => {
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
   });
   this.offers = this.offers.filter(item => item.id != id);
  }

  getSubscribedItems(): Array<CulturalOffer> {
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
