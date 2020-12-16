import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { CategoryService } from '../../services/category.service';
import { CategoryModel } from '../../model/category-model';
import { TypeService } from '../../services/type.service';
import { TypeModel } from '../../model/type-model';
import { CulturalOfferService } from '../../services/culturalOffer.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-add-offer',
  templateUrl: './add-offer.component.html',
  styleUrls: ['./add-offer.component.css']
})
export class AddOfferComponent implements OnInit {
  images = [] as any;
  categories: Array<CategoryModel> = [];
  types: Array<TypeModel> = [];
  files: string[] = [];

  myForm = new FormGroup({
    name: new FormControl('', Validators.required),
    description: new FormControl('', Validators.required),
    category: new FormControl('', Validators.required),
    type: new FormControl('', Validators.required),
    place: new FormControl('', Validators.required),
    location: new FormControl('', [Validators.required]),
    file: new FormControl('', [Validators.required]),
    fileSource: new FormControl('', [Validators.required])
  });

  constructor(private categoryService: CategoryService,
              private offerService: CulturalOfferService,
              private typeService: TypeService,
              private http: HttpClient) {
  }

  ngOnInit() {
    // @ts-ignore
    let mymap = L.map('mapid').setView([51.505, -0.09], 13);
    // @ts-ignore
    L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}', {
      attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
      maxZoom: 18,
      id: 'mapbox/streets-v11',
      tileSize: 512,
      zoomOffset: -1,
      accessToken: 'pk.eyJ1IjoiYXNqaGdhc2RqIiwiYSI6ImNraWx4a2x5dTBtNWUyeHBkZjZsOXdxYTYifQ.Xl_z4h3W3xCO1K2Aj-j2Iw'
    }).addTo(mymap);
    let marker: any;
    mymap.on('click', (e: any) => {
      if (marker !== undefined) {
        mymap.removeLayer(marker)
      }
      console.log(e.latlng);
      this.myForm.patchValue({
        location: e.latlng
      });
      // @ts-ignore
      marker = new L.Marker(e.latlng);
      mymap.addLayer(marker);
    });

    this.getCategories();
  }

  onChange(event: any): void {
    this.typeService.getTypesForCategory(event).subscribe(data => {
      this.types = data;
      console.log("got types = ");
      console.log(this.types);
    }, error => {
      this.types = []
      console.log(error);
    });
  }

  getCategories() {
    this.categoryService.getCategories().subscribe(data => {
      this.categories = data;
      console.log("got categories = ");
      console.log(this.categories);
    }, error => {
      console.log(error);
    });
  }

  get f() {
    return this.myForm.controls;
  }

  remove(url: any) {
    //console.log(this.images)
    console.log("remove = ");
    //console.log(url);
    this.images = this.images.filter((obj: any) => obj !== url);
  }

  onFileChange(event: any) {
    console.log("form changed")
    for (var i = 0; i < event.target.files.length; i++) {
      this.files.push(event.target.files[i]);
    }

    if (event.target.files && event.target.files[0]) {
      var filesAmount = event.target.files.length;
      for (let i = 0; i < filesAmount; i++) {
        var reader = new FileReader();
        reader.onload = (event: any) => {
          if (!this.images.includes(event.target.result)) {
            this.images.push(event.target.result);
            this.myForm.patchValue({
              fileSource: this.images
            });
          }
        }
        reader.readAsDataURL(event.target.files[i]);
      }
    }
  }

  submit() {
    console.log(this.myForm.value);

    let locationObj = {
      'place': this.myForm.value.place,
      'latitude': this.myForm.value.location.lat,
      'longitude': this.myForm.value.location.lng
    }

    let obj = {
      'name': this.myForm.value.name,
      'description': this.myForm.value.description,
      'type': this.myForm.value.type,
      'location': locationObj
    }
    console.log(obj);
    //console.log("post");
    this.offerService.createOffer(obj);
  }
}
