import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {CulturalOffer} from '../../model/offer-mode';
import {MatSlideToggleChange} from '@angular/material/slide-toggle';
import {CulturalOfferService} from '../../services/culturalOffer.service';
import { Router} from '@angular/router';

@Component({
  selector: 'app-cultural-offer-item',
  templateUrl: './cultural-offer-item.component.html',
  styleUrls: ['./cultural-offer-item.component.css']
})
export class CulturalOfferItemComponent implements OnInit {

  @Input() public offer: any;
  offerDescription: string = "";

  @Input() public isSubscribed: boolean = false;
  @Output() removeOffer = new EventEmitter<number>();
  @Output() editOffer = new EventEmitter<CulturalOffer>();

  constructor(
    private auth: AuthService,
    private service: CulturalOfferService,
    private route: Router
  )
  { }

  ngOnInit(): void {
    const regex = /((\s*\S+){25})([\s\S]*)/;
    const subst = `$1...`;
    const result = this.offer.description.replace(regex, subst);
    this.offerDescription = result;
  }

  deleteOffer(offer_id: number) {
    this.removeOffer.emit(offer_id);
  }

  edit(offer: CulturalOffer){
    this.editOffer.emit(offer);
  }

  subscribeToggle(event: MatSlideToggleChange, offer_id: number) {
    if(event.checked) { // subscribe to offer
      this.service.subscribeUser(offer_id);
    }
    else {//unsubscribe from offer
      this.service.unsubscribeUser(offer_id);
    }
  }

  isUser(): boolean {
    return this.auth.isUser();
  }

  isAdmin(): boolean {
    return this.auth.isAdmin();
  }

  showDetails(): void {
    this.route.navigate(['/offer-details/'+this.offer.id]);
  }

}
