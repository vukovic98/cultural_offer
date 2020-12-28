import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { CulturalOfferService } from '../../services/culturalOffer.service';
import { OfferDetailsModel } from '../../model/offer-mode';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-offer-details',
  templateUrl: './offer-details.component.html',
  styleUrls: ['./offer-details.component.css']
})
export class OfferDetailsComponent implements OnInit {
  stars: number[] = [1, 2, 3, 4, 5];
  selectedValue: number = 0;
  id: string = '';
  offer!: OfferDetailsModel;

  constructor(private route: ActivatedRoute,
    private service: CulturalOfferService,
    private authService: AuthService) { }

  ngOnInit() {
    //@ts-ignore
    this.id = this.route.snapshot.paramMap.get('id');
    console.log(this.id);
    this.service.getOffer(this.id).subscribe((data: OfferDetailsModel) => {
      this.offer = data;
      console.log(this.offer);
    }, (error: any) => {
      console.log(error);
    });
  }

  countStar(star: any) {
    if (this.selectedValue == 0) {
      this.selectedValue = star;
      console.log('Value of star', star);
      let userId = this.authService.getUserId();
      if(userId == "-1"){return;}
      let offerId = this.offer.id;
      this.service.gradeOffer(parseInt(userId), parseInt(offerId), this.selectedValue);
    } else {
      console.log('already voted');
    }
  }
}
