import { Component, OnInit, Inject } from '@angular/core';
import { CulturalOffer } from '../../model/offer-mode';
// @ts-ignore
import * as L from 'leaflet';
import { MapService } from 'src/app/services/map.service';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent {
  offers: Array<CulturalOffer> = [];
  private pageNum = 1;
  private mymap: any;
  markers = new L.LayerGroup;

  constructor(public mapService: MapService) {
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

    this.offers.forEach(offer => {
      let imageStr: string;

      if(offer.images.length != 0) {
        imageStr = "<img class='float-left' style='width: 100%; height: 100px; min-width: 100px;' src='data:image/png;base64,"+offer.images[0].picByte+"' alt=''>";
      } else {
        imageStr = "<img class=\"float-left\" style=\"height: 100px; width: 100%;\" [alt]=\"offer.name\" *ngIf=\"!offer.images\" src=\"https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg\">";
      }

      let htmlData: string = "<div class='row'>" + imageStr +
        "<a class='btn btn-light m-auto w-100' id='offerMarkerLink"+offer.id+"' href='https://localhost:4200/cultural-offer/offer-details/"+offer.id+"'>"+offer.name+"</a>" +
        "</div>";

      let marker = L.marker([offer.location.latitude, offer.location.longitude]).addTo(this.markers);
      marker.on('click', (e: any) => {
        //open popup;
        var popup = L.popup()
          .setLatLng(marker.getLatLng())
          .setContent(htmlData)
          .openOn(this.mymap);
      });
    });

    this.markers.addTo(this.mymap);
  }

}
