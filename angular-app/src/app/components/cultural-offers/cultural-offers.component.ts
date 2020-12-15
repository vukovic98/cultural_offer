import { Component, OnInit } from '@angular/core';
import {CulturalOfferService} from '../../services/culturalOffer.service';

@Component({
  selector: 'app-cultural-offers',
  templateUrl: './cultural-offers.component.html',
  styleUrls: ['./cultural-offers.component.css']
})
export class CulturalOffersComponent implements OnInit {

  private offers: Array<object> = [];
  public pageNum: number = 1;
  public nextBtn: boolean = false;

  constructor(private service: CulturalOfferService) { }

  ngOnInit(): void {
    this.service.getByPage(this.pageNum).subscribe((data: string) => {
      this.offers = JSON.parse(data).content;
      this.nextBtn = JSON.parse(data).last;
    });
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




}
