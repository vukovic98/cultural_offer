import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {CulturalOffer} from '../../model/offer-mode';

@Component({
  selector: 'app-cultural-offer-item',
  templateUrl: './cultural-offer-item.component.html',
  styleUrls: ['./cultural-offer-item.component.css']
})
export class CulturalOfferItemComponent implements OnInit {

  @Input() public offer: any;
  @Input() public isSubscribed: boolean = false;
  @Output() removeOffer = new EventEmitter<number>();
  @Output() editOffer = new EventEmitter<CulturalOffer>();

  constructor(private auth: AuthService) { }

  ngOnInit(): void {
  }

  deleteOffer(offer_id: number) {
    this.removeOffer.emit(offer_id);
  }

  edit(offer: CulturalOffer){
    this.editOffer.emit(offer);
  }

  isUser(): boolean {
    return this.auth.isUser();
  }

  isAdmin(): boolean {
    return this.auth.isAdmin();
  }


}
