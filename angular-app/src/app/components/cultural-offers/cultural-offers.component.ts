import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CulturalOfferService} from '../../services/culturalOffer.service';
import {AuthService} from '../../services/auth.service';
import {CulturalOffer} from '../../model/offer-mode';
import {TokenModel} from '../../model/token.model';
import {of} from 'rxjs';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {EditOfferComponent} from '../edit-offer/edit-offer.component';
import {FilterObject} from "../../model/filter-model";

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



  constructor(private service: CulturalOfferService,
              private auth: AuthService,
              public dialog: MatDialog) { }

  ngOnInit(): void {
    this.service.getByPage(this.pageNum).subscribe((data: string) => {
      this.offers = JSON.parse(data).content;
      this.nextBtn = JSON.parse(data).last;
    });

    if(this.auth.isUser()) {
      this.service.getSubscribedItems().subscribe((data: string) => {
        this.subscribedItems = JSON.parse(data);
      })
    }

    if(this.auth.isLoggedIn()) {
      let token = this.auth.getToken();
      let userData: TokenModel | null = this.auth.decodeToken(token);
      this.userId = Number(userData?.user_id);
    }

  }

  removeOffer(id: number) {
    this.service.deleteOffer(id);
    this.offers = this.offers.filter(item => item.id != id);
  }

  editOffer(offer: CulturalOffer){
    const dialogRef = this.dialog.open(EditOfferComponent, {
      width: '500px',
      data: offer
    });
    dialogRef.afterClosed().subscribe(result => {
      if(result != undefined){
        offer = result.data;
        this.service.updateOffer(offer);
      }
    });
  }

  getAllOffers() {
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

  filterOffers(data: FilterObject){
    this.pageNum = 1;
    if(data.exp==="" && data.types.length === 0){
      this.service.getByPage(this.pageNum).subscribe((data: string) => {
        this.offers = JSON.parse(data).content;
        this.nextBtn = JSON.parse(data).last;
      });
    }
    else{
      this.service.getByPageFilter(this.pageNum,data.exp,data.types).subscribe(
        (offers: string)=>{
          this.offers = JSON.parse(offers).content;
          this.nextBtn = JSON.parse(offers).last;
        }
      )
    }

  }
}
