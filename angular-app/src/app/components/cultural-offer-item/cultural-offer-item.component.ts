import {Component, Input, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-cultural-offer-item',
  templateUrl: './cultural-offer-item.component.html',
  styleUrls: ['./cultural-offer-item.component.css']
})
export class CulturalOfferItemComponent implements OnInit {

  @Input() public offer: any;
  @Input() public isSubscribed: boolean | undefined;

  constructor(private auth: AuthService) { }

  ngOnInit(): void {
    console.log(this.isSubscribed);
  }


}
