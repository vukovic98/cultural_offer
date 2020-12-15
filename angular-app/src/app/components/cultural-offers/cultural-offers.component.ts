import { Component, OnInit } from '@angular/core';
import {CulturalOfferService} from '../../services/culturalOffer.service';
import {AuthService} from '../../services/auth.service';
import {CulturalOffer} from '../../model/offer-mode';
import {TokenModel} from '../../model/token.model';

@Component({
  selector: 'app-cultural-offers',
  templateUrl: './cultural-offers.component.html',
  styleUrls: ['./cultural-offers.component.css']
})
export class CulturalOffersComponent implements OnInit {

  private offers: Array<CulturalOffer> = [];
  private subscribedItems: Array<CulturalOffer> = [];
  private userId: number = -1;
  public pageNum: number = 1;
  public nextBtn: boolean = false;

  constructor(private service: CulturalOfferService, private auth: AuthService) { }

  ngOnInit(): void {
    this.service.getByPage(this.pageNum).subscribe((data: string) => {
      this.offers = JSON.parse(data).content;
      this.nextBtn = JSON.parse(data).last;
    });

    this.service.getSubscribedItems().subscribe((data: string) => {
      this.subscribedItems = JSON.parse(data);
    })

    if(this.auth.isLoggedIn()) {
      let token = this.auth.getToken();
      let userData: TokenModel | null = this.auth.decodeToken(token);
      this.userId = Number(userData?.user_id);
    }
  }

  getAllOffers() {
    console.log(this.offers);
    return this.offers || [];
  }

  nextPage() {
    this.pageNum += 1;
    this.service.getByPage(this.pageNum).subscribe((data: string) => {
      this.offers = JSON.parse(data).content;
      this.nextBtn = JSON.parse(data).last;
    });
  }

  previousPage() {
    this.pageNum -= 1;
    this.service.getByPage(this.pageNum).subscribe((data: string) => {
      this.offers = JSON.parse(data).content;
      this.nextBtn = JSON.parse(data).last;
    });
  }

  isSubscribed(offer: CulturalOffer): boolean {
    if(this.auth.isLoggedIn()) {
      for (let i of this.subscribedItems) {
        if (i.id === offer.id)
          return true;
      }
    }
    return false;
  }
}
