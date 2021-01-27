import {Component, Input, OnInit, Output, EventEmitter} from '@angular/core';
import {CulturalOffer} from '../../model/offer-mode';

@Component({
  selector: 'app-subscribed-items-list',
  templateUrl: './subscribed-items-list.component.html',
  styleUrls: ['./subscribed-items-list.component.css']
})
export class SubscribedItemsListComponent implements OnInit {

  @Input() public offers: Array<CulturalOffer> = [];
  @Output() removeOffer = new EventEmitter<number>();

  constructor() { }

  ngOnInit(): void {
  }

  unsubscribe(offerId: number) {
    this.removeOffer.emit(offerId);
  }

}
