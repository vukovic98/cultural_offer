import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { CulturalOffer } from '../../model/offer-mode';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { CulturalOfferService } from '../../services/culturalOffer.service';

@Component({
  selector: 'app-edit-offer',
  templateUrl: './edit-offer.component.html',
  styleUrls: ['./edit-offer.component.css']
})
export class EditOfferComponent implements OnInit {

  placeName: string = "";
  myForm = new FormGroup({
    name: new FormControl(this.data.name, Validators.required),
    description: new FormControl(this.data.description, Validators.required),
    place: new FormControl(this.data.location.place, Validators.required),
    location: new FormControl('', [Validators.required]),
  });

  constructor(
    public dialogRef: MatDialogRef<EditOfferComponent>,
    @Inject(MAT_DIALOG_DATA) public data: CulturalOffer,
    public service: CulturalOfferService) {
    
    this.placeName = data.location.place;
    this.myForm.controls['place'].disable();
  }

  ngOnInit(): void {
    this.initMap();
  }

  save(){
    this.data.name = this.myForm.value.name;
    this.data.description = this.myForm.value.description;
    this.data.location.place = this.myForm.value.place;
    this.dialogRef.close({data:this.data});
  }

  close(){
    this.dialogRef.close();
  }
  get f() {
    return this.myForm.controls;
  }

  initMap(){
    //@ts-ignore
    let mymap = L.map('mapid').setView([this.data.location.latitude, this.data.location.longitude], 13);
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
    let marker = L.marker([this.data.location.latitude, this.data.location.longitude]).addTo(mymap);
    mymap.on('click', (e: any) => {
      if (marker !== undefined) {
        mymap.removeLayer(marker)
      }
      this.myForm.patchValue({
        location: e.latlng
      });
      this.data.location.latitude = e.latlng.lat;
      this.data.location.longitude = e.latlng.lng;
      this.service.getLocationName(e.latlng).subscribe((data:any) => {
        this.placeName = data.address.road+", "+data.address.city+", "+data.address.country;
      }, (error:any) => {
        console.log(error);
      });

      //@ts-ignore
      marker = new L.Marker(e.latlng);
      mymap.addLayer(marker);
    });
  }
}
