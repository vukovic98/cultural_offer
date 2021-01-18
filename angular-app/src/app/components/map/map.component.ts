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
import * as L from 'leaflet';
@Component({
    selector: 'app-map',
    templateUrl: './map.component.html',
    styleUrls: ['./map.component.css']
  })
  export class MapComponent {
    private offers: Array<CulturalOffer> = [];
    
    constructor(private offerService: CulturalOfferService,
        private auth: AuthService) {
      
    }
  
    ngOnInit() {
      //@ts-ignore
      let mymap = L.map('map').setView([44.787197, 20.457273], 3);
      //@ts-ignore
      L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}', {
        attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
        maxZoom: 18,
        id: 'mapbox/streets-v11',
        tileSize: 512,
        zoomOffset: -1,
        accessToken: 'pk.eyJ1IjoiYXNqaGdhc2RqIiwiYSI6ImNraWx4a2x5dTBtNWUyeHBkZjZsOXdxYTYifQ.Xl_z4h3W3xCO1K2Aj-j2Iw'
      }).addTo(mymap);
      //@ts-ignore

      

      this.offerService.getByPage(1).subscribe((result: string) => {
        this.offers = JSON.parse(result).content;
        this.offers.forEach(offer => {
            let marker = L.marker([offer.location.latitude, offer.location.longitude]).addTo(mymap);
            marker.on('click', function(e) {
                //open popup;
                var popup = L.popup()
                 .setLatLng(marker.getLatLng()) 
                 .setContent(offer.name)
                 .openOn(mymap);
              });
        });
            

          
      });


      

    
    }
  }