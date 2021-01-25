import { Component, OnInit, Inject } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { CulturalOfferService } from '../../services/culturalOffer.service';
import { CulturalOffer, OfferDetailsModel, OfferModel, Location } from '../../model/offer-mode';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { MatSlideToggleChange } from '@angular/material/slide-toggle';
import { EditOfferComponent } from '../edit-offer/edit-offer.component';
import { AddPostComponent } from '../add-post/add-post.component';
import { AddPostModel } from '../../model/post-model';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import Swal from "sweetalert2";
import { MapService } from 'src/app/services/map.service';
import { LocationDialogComponent } from '../location-dialog/location-dialog.component';

@Component({
  selector: 'app-offer-details',
  templateUrl: './offer-details.component.html',
  styleUrls: ['./offer-details.component.css']
})
export class OfferDetailsComponent implements OnInit{
  stars: number[] = [1, 2, 3, 4, 5];
  selectedValue: number = 0;
  id: string = '';
  offer!: OfferDetailsModel;
  private subscribedItems: Array<CulturalOffer> = [];
  //newModel: AddPostModel = { id: 0, content: "", culturalOfferId: 0, title: "" };
  timer: number = 0;

  constructor(private route: ActivatedRoute,
    private service: CulturalOfferService,
    private authService: AuthService,
    public locationDialog: MatDialog) { }

  ngOnInit() {
    if (this.authService.isUser()) {
      this.service.getSubscribedItems().subscribe((data) => {
        this.subscribedItems = data;
      })
    }
    //@ts-ignore
    this.id = this.route.snapshot.paramMap.get('id');
    this.getOffer();
  }

  getOffer(): void {
    this.service.getOffer(this.id).subscribe((data: OfferDetailsModel) => {
      this.offer = data;

      let token = this.authService.getToken();
      let userData = this.authService.decodeToken(token);

      for (let p of this.offer.grades) {
        if (p.user.id === Number(userData?.user_id)) {
          this.selectedValue = p.value;
        }
      }
    }, (error: any) => {
      console.log(error);
    });
  }

  countStar(star: any) {

    this.selectedValue = star;

    let userId = this.authService.getUserId();
    if (userId == "-1") { return; }
    let offerId = this.offer.id;
    this.service.gradeOffer(parseInt(userId), parseInt(offerId), this.selectedValue)
      .subscribe(response => {
        console.log("gradeOffer = ", response);
        Swal.fire({
          title: 'Success!',
          text: 'We marked you grade! ' ,
          icon: 'success',
          confirmButtonText: 'OK'
        });
        let sum = this.selectedValue;
        let i = 1;
        for (let p of this.offer.grades) {
          sum += p.value;
          i+=1;
        }
        this.offer.avgGrade = sum/i;
    }, error => {
      Swal.fire({
        title: 'Error!',
        text: 'Something went wrong! ' + error.error,
        icon: 'error',
        confirmButtonColor: '#DC143C',
        confirmButtonText: 'OK'
      });
      return false;
    });

  }

  subscribeToggle(event: MatSlideToggleChange, offer_id: string) {
    let id: number = Number(offer_id);
    if (event.checked) { // subscribe to offer
      this.service.subscribeUser(id)
        .subscribe(response => {
          console.log("subscribeUser = ", response);
          Swal.fire({
            title: 'Success!',
            text: 'Successfully subscribed to offer! Now you will get all the latest updates!',
            icon: 'success',
            confirmButtonText: 'OK'
          });
          this.offer.subscribersCount += 1;
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
    else {//unsubscribe from offer
      this.service.unsubscribeUser(id)
        .subscribe(response => {
          console.log("unsubscribeUser = ", response);
          Swal.fire({
            title: 'Success!',
            text: 'Successfully unsubscribed from offer!',
            icon: 'success',
            confirmButtonText: 'OK'
          });
          this.offer.subscribersCount -= 1;
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
  }

  isUser(): boolean {
    return this.authService.isUser();
  }

  isAdmin(): boolean {
    return this.authService.isAdmin();
  }

  isSubscribed(offer: OfferDetailsModel): boolean {
    console.log("isSubscribed = ", offer);
    if (this.authService.isLoggedIn()) {
      for (let i of this.subscribedItems) {
        if (i.id === Number(offer.id))
          return true;
      }
    }
    return false;
  }

  showLocation(location: Location) {
    const dialogRef = this.locationDialog.open(LocationDialogComponent, {
      width: '450px',
      height: '450px',
      data: location
    });
  }
}
