import { Component, OnInit, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Location } from '../../model/offer-mode';
import * as L from 'leaflet';

@Component({
  selector: 'app-location-dialog',
  templateUrl: './location-dialog.component.html',
  styleUrls: ['./location-dialog.component.css']
})
export class LocationDialogComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<LocationDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Location) {

  }

  ngOnInit() {
    //@ts-ignore
    let mymap = L.map('mapid').setView([this.data.latitude, this.data.longitude], 13);
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
    let marker = L.marker([this.data.latitude, this.data.longitude]).addTo(mymap);
  }

  close() {
    this.dialogRef.close();
  }
}
