import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CulturalOfferService} from '../../services/culturalOffer.service';
import {AuthService} from '../../services/auth.service';
import {CulturalOffer} from '../../model/offer-mode';
import {TokenModel} from '../../model/token.model';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {EditOfferComponent} from '../edit-offer/edit-offer.component';
import {FilterObject} from "../../model/filter-model";
import Swal from "sweetalert2";
import { MapService } from 'src/app/services/map.service';

@Component({
  selector: 'app-cultural-offers',
  templateUrl: './cultural-offers.component.html',
  styleUrls: ['./cultural-offers.component.css']
})
export class CulturalOffersComponent implements OnInit {
  @Output() dataChangedEvent = new EventEmitter();
  private offers: Array<CulturalOffer> = [];
  private subscribedItems: Array<CulturalOffer> = [];
  private userId: number = -1;
  public pageNum: number = 1;
  public nextBtn: boolean = false;
  public isFilter: boolean = false;
  public totalPages: number = 1;
  public filterObj: FilterObject = { exp: '', types: [] };
  public isLoading: boolean = false;

  constructor(private service: CulturalOfferService,
              private auth: AuthService,
              public dialog: MatDialog,
              public mapService: MapService) { }

  ngOnInit(): void {
    this.isLoading = true;

    this.service.getByPage(this.pageNum).subscribe((data) => {
      this.offers = data.content;
      this.mapService.myMethod(this.offers);
      this.dataChangedEvent.emit(null);
      this.nextBtn = data.last;
      this.totalPages = data.totalPages;
      this.isLoading = false;
    });

    if (this.auth.isUser()) {
      this.service.getSubscribedItems().subscribe((data) => {
        this.subscribedItems = data;
      });
    }

    if (this.auth.isLoggedIn()) {
      const token = this.auth.getToken();
      const userData: TokenModel | null = this.auth.decodeToken(token);
      this.userId = Number(userData?.user_id);
    }

  }

  removeOffer(id: number) {
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
      });
    this.offers = this.offers.filter(item => item.id != id);
  }

  editOffer(offer: CulturalOffer) {
    const data_copy = JSON.parse(JSON.stringify(offer));
    const dialogRef = this.dialog.open(EditOfferComponent, {
      width: '500px',
      data: data_copy
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result != undefined) {
        this.service.updateOffer(result.data)
          .subscribe(response => {
            Swal.fire({
              title: 'Success!',
              text: 'Cultural offer successfully updated!',
              icon: 'success',
              confirmButtonText: 'OK'
            });
            offer = result.data;
          }, error => {
            Swal.fire({
              title: 'Error!',
              text: 'Something went wrong! Offer with that name already exists',
              icon: 'error',
              confirmButtonColor: '#DC143C',
              confirmButtonText: 'OK'
            });
          });
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
    this.isLoading = true;
    if (!this.isFilter) {
      this.service.getByPage(this.pageNum).subscribe((data) => {
        this.offers = data.content;
        this.nextBtn = data.last;
        this.mapService.myMethod(this.offers);
        this.dataChangedEvent.emit(null);
        this.isLoading = false;
      });
    } else {
      this.service.getByPageFilter(this.pageNum, this.filterObj.exp, this.filterObj.types).subscribe(
        (offers) => {
          this.offers = offers.content;
          this.nextBtn = offers.last;
          this.mapService.myMethod(this.offers);
          this.dataChangedEvent.emit(null);
          this.isLoading = false;
        }
        );
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
    if (this.auth.isLoggedIn()) {
      for (let i of this.subscribedItems) {
        if (i.id === offer.id)
          return true;
      }
    }
    return false;
  }

  filterOffers(data: FilterObject) {
    this.isLoading = true;
    this.isFilter = true;
    this.pageNum = 1;
    this.filterObj = data;

    if (data.exp === "" && data.types.length === 0) {
      this.isFilter = false;
      this.service.getByPage(this.pageNum).subscribe((response) => {
        this.offers = response.content;
        this.nextBtn = response.last;
        this.mapService.myMethod(this.offers);
        this.dataChangedEvent.emit(null);
        this.isLoading = false;
      });
    }
    else {
      this.service.getByPageFilter(this.pageNum, data.exp, data.types).subscribe(
        (offers) => {
          this.offers = offers.content;
          this.nextBtn = offers.last;
          this.mapService.myMethod(this.offers);
          this.dataChangedEvent.emit(null);
          this.isLoading = false;
        }
      );
    }
  }
}
