import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-cultural-offer-item',
  templateUrl: './cultural-offer-item.component.html',
  styleUrls: ['./cultural-offer-item.component.css']
})
export class CulturalOfferItemComponent implements OnInit {

  @Input() public offer: any;

  constructor() { }

  ngOnInit(): void {
  }

}
