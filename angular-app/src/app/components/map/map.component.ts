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
    console.log("Data changed");

    this.offers.forEach(offer => {
      console.log(offer.name);
      let marker = L.marker([offer.location.latitude, offer.location.longitude]).addTo(this.markers);
      marker.on('click', (e: any) => {
        //open popup;
        var popup = L.popup()
          .setLatLng(marker.getLatLng())
          .setContent("<a id='offerMarkerLink"+offer.id+"' href='http://localhost:4200/cultural-offer/offer-details/"+offer.id+"'>"+offer.name+"</a>")
          .openOn(this.mymap);
      });
    });

    this.markers.addTo(this.mymap);
  }

}
