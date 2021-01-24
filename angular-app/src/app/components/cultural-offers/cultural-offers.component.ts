import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CulturalOfferService} from '../../services/culturalOffer.service';
import {AuthService} from '../../services/auth.service';
import {CulturalOffer} from '../../model/offer-mode';
import {TokenModel} from '../../model/token.model';
import {of} from 'rxjs';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {EditOfferComponent} from '../edit-offer/edit-offer.component';
import {FilterObject} from "../../model/filter-model";
import {map} from 'rxjs/operators';
import Swal from "sweetalert2";
import { MapService } from 'src/app/services/map.service';

@Component({
  selector: 'app-cultural-offers',
  templateUrl: './cultural-offers.component.html',
  styleUrls: ['./cultural-offers.component.css']
})
export class CulturalOffersComponent implements OnInit {
<<<<<<< HEAD
  @Output() dataChangedEvent = new EventEmitter();
  private offers: Array<CulturalOffer> = [];
  private subscribedItems: Array<CulturalOffer> = [];
  private userId: number = -1;
=======

  public offers: Array<CulturalOffer> = [];
  public subscribedItems: Array<CulturalOffer> = [];
  public userId: number = -1;
>>>>>>> dc484f6486cfdced60074faccf8e0d84f17be6e7
  public pageNum: number = 1;
  public nextBtn: boolean = false;
  public isFilter: boolean = false;
  public totalPages: number = 1;
  public filterObj: FilterObject = {exp: '', types: []};

  constructor(private service: CulturalOfferService,
              private auth: AuthService,
              public dialog: MatDialog,
              public mapService: MapService) { }

  ngOnInit(): void {
<<<<<<< HEAD
    this.service.getByPage(this.pageNum).subscribe((data: string) => {
      this.offers = JSON.parse(data).content;
      this.mapService.myMethod(this.offers);
      this.dataChangedEvent.emit(null);
      this.nextBtn = JSON.parse(data).last;
      this.totalPages = JSON.parse(data).totalPages;
=======
    this.service.getByPage(this.pageNum).subscribe((data) => {
      this.offers = data.content;
      this.nextBtn = data.last;
      this.totalPages = data.totalPages;
>>>>>>> dc484f6486cfdced60074faccf8e0d84f17be6e7
    });

    if(this.auth.isUser()) {
      this.service.getSubscribedItems().subscribe((data) => {
        this.subscribedItems = data;
      })
    }

    if(this.auth.isLoggedIn()) {
      let token = this.auth.getToken();
      let userData: TokenModel | null = this.auth.decodeToken(token);
      this.userId = Number(userData?.user_id);
    }

  }

  removeOffer(id: number) {
    console.log("remove offer = " + id);
    this.service.deleteOffer(id)
      .subscribe(response => {
        Swal.fire({
          title: 'Success!',
          text: 'Cultural offer successfully deleted!',
          icon: 'success',
          confirmButtonText: 'OK'
        });
      }, error => {
        Swal.fire({
          title: 'Error!',
          text: 'Something went wrong!',
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'OK'
        });
      })
    this.offers = this.offers.filter(item => item.id != id);

    location.reload();
  }

  editOffer(offer: CulturalOffer){
    const dialogRef = this.dialog.open(EditOfferComponent, {
      width: '500px',
      data: offer
    });
    dialogRef.afterClosed().subscribe(result => {
      if(result != undefined){
        offer = result.data;
        this.service.updateOffer(offer)
          .subscribe(response => {
            Swal.fire({
              title: 'Success!',
              text: 'Cultural offer successfully updated!',
              icon: 'success',
              confirmButtonText: 'OK'
            });
          }, error => {
            Swal.fire({
              title: 'Error!',
              text: 'Something went wrong!',
              icon: 'error',
              confirmButtonColor: '#DC143C',
              confirmButtonText: 'OK'
            });
          })
      }
    });
  }

  getAllOffers() {
    return this.offers || [];
  }

  firstPage() {
    this.pageNum = 1;
    this.retrieveOffers();
  }

  lastPage() {
    this.pageNum = this.totalPages;
    this.retrieveOffers();
  }

  retrieveOffers() {
    if(!this.isFilter) {
<<<<<<< HEAD
      this.service.getByPage(this.pageNum).subscribe((data: string) => {
        this.offers = JSON.parse(data).content;
        this.nextBtn = JSON.parse(data).last;
        this.mapService.myMethod(this.offers);
        this.dataChangedEvent.emit(null);
      });
    } else {
      this.service.getByPageFilter(this.pageNum,this.filterObj.exp,this.filterObj.types).subscribe(
        (offers: string)=>{
          this.offers = JSON.parse(offers).content;
          this.nextBtn = JSON.parse(offers).last;
          this.mapService.myMethod(this.offers);
          this.dataChangedEvent.emit(null);
=======
      this.service.getByPage(this.pageNum).subscribe((data) => {
        this.offers = data.content;
        this.nextBtn = data.last;
      });
    } else {
      this.service.getByPageFilter(this.pageNum,this.filterObj.exp,this.filterObj.types)
        .subscribe((offers) => {
          this.offers = offers.content;
          this.nextBtn = offers.last;
>>>>>>> dc484f6486cfdced60074faccf8e0d84f17be6e7
        }
      )
    }

  }

  nextPage() {
    this.pageNum += 1;
    this.retrieveOffers();
  }

  previousPage() {
    this.pageNum -= 1;
    this.retrieveOffers();
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
    this.isFilter = true;
    this.pageNum = 1;
    this.filterObj = data;

    if(data.exp==="" && data.types.length === 0){
      this.isFilter = false;
<<<<<<< HEAD
      this.service.getByPage(this.pageNum).subscribe((data: string) => {
        this.offers = JSON.parse(data).content;
        this.nextBtn = JSON.parse(data).last;
        this.mapService.myMethod(this.offers);
        this.dataChangedEvent.emit(null);
=======
      this.service.getByPage(this.pageNum).subscribe((data) => {
        this.offers = data.content;
        this.nextBtn = data.last;
>>>>>>> dc484f6486cfdced60074faccf8e0d84f17be6e7
      });
    }
    else{
      this.service.getByPageFilter(this.pageNum,data.exp,data.types).subscribe(
<<<<<<< HEAD
        (offers: string)=>{
          this.offers = JSON.parse(offers).content;
          this.nextBtn = JSON.parse(offers).last;
          this.mapService.myMethod(this.offers);
          this.dataChangedEvent.emit(null);
=======
        (offers)=>{
          this.offers = offers.content;
          this.nextBtn = offers.last;
>>>>>>> dc484f6486cfdced60074faccf8e0d84f17be6e7
        }
      )
    }
  }
}
