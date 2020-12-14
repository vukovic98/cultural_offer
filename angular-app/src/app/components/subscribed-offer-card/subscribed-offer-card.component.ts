import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-subscribed-offer-card',
  templateUrl: './subscribed-offer-card.component.html',
  styleUrls: ['./subscribed-offer-card.component.css']
})
export class SubscribedOfferCardComponent implements OnInit {

  @Input() public offer: any;

  constructor() { }

  ngOnInit(): void {
  }

}
