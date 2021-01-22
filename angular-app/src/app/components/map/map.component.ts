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
import { MapService } from 'src/app/services/map.service';
import { FilterObject } from 'src/app/model/filter-model';
@Component({
    selector: 'app-map',
    templateUrl: './map.component.html',
    styleUrls: ['./map.component.css']
  })
  export class MapComponent {
    private offers: Array<CulturalOffer> = [];
    private pageNum = 1;
    private mymap: any;
    private markers = new L.LayerGroup;

    constructor(private offerService: CulturalOfferService,
        private auth: AuthService,
        public mapService: MapService) {
      this.mapService.myMethod$.subscribe((offers) =>{
          this.offers = offers;
      });
    }
  
    ngOnInit() {
      //@ts-ignore
      this.mymap = L.map('map').setView([44.787197, 20.457273], 6);
      
      //@ts-ignore
      L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}', {
        attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
        maxZoom: 18,
        id: 'mapbox/streets-v11',
        tileSize: 512,
        zoomOffset: -1,
        accessToken: 'pk.eyJ1IjoiYXNqaGdhc2RqIiwiYSI6ImNraWx4a2x5dTBtNWUyeHBkZjZsOXdxYTYifQ.Xl_z4h3W3xCO1K2Aj-j2Iw'
      }).addTo(this.mymap);
      //@ts-ignore

      


    }

    dataChanged(){
      this.markers.clearLayers();
      console.log("Data changed");

      this.offers.forEach(offer => {
        console.log(offer.name);
          let marker = L.marker([offer.location.latitude, offer.location.longitude]).addTo(this.markers);
          marker.on('click', (e) => {
                //open popup;
              var popup = L.popup()
                .setLatLng(marker.getLatLng()) 
                .setContent("<a href='http://localhost:4200/cultural-offer/offer-details/"+offer.id+"'>"+offer.name+"</a>")
                .openOn(this.mymap);
            });
      });
            
      this.markers.addTo(this.mymap);
    }

  }