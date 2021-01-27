import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { CategoryService } from '../../services/category.service';
import { CategoryModel } from '../../model/category-model';
import { TypeService } from '../../services/type.service';
import { TypeModel } from '../../model/type-model';
import { CulturalOfferService } from '../../services/culturalOffer.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import Swal from "sweetalert2";
import { Router } from '@angular/router';

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
  placeName: string = "";

  myForm = new FormGroup({
    name: new FormControl('', Validators.required),
    description: new FormControl('', Validators.required),
    category: new FormControl('', Validators.required),
    type: new FormControl('', Validators.required),
    place: new FormControl('', [Validators.required]),
    location: new FormControl('', [Validators.required]),
    file: new FormControl('', [Validators.required]),
    fileSource: new FormControl('', [Validators.required])
  });

  constructor(
    private categoryService: CategoryService,
    private offerService: CulturalOfferService,
    private typeService: TypeService,
    private route: Router
  ) {
  }

  ngOnInit() {
    // @ts-ignore
    let mymap = L.map('mapid').setView([44.787197, 20.457273], 6);
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
        mymap.removeLayer(marker);
      }
      this.myForm.patchValue({
        location: e.latlng
      });

      this.offerService.getLocationName(e.latlng).subscribe((data: any) => {
        this.placeName = data.address.road + ", " + data.address.city + ", " + data.address.country;
      }, (error: any) => {
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
    }, error => {
      this.types = [];
    });
  }

  getCategories() {
    this.categoryService.getCategories().subscribe(data => {
      console.log("getCategories = ", data);
      this.categories = data;
    }, error => {
      console.log(error);
    });
  }

  get f() {
    return this.myForm.controls;
  }

  remove(url: any) {
    this.images = this.images.filter((obj: any) => obj !== url);
  }

  onFileChange(event: any) {
    for (let i = 0; i < event.target.files.length; i++) {
      this.files.push(event.target.files[i]);
    }

    if (event.target.files && event.target.files[0]) {
      const filesAmount = event.target.files.length;
      for (let i = 0; i < filesAmount; i++) {
        let reader = new FileReader();
        reader.onload = (event: any) => {
          if (!this.images.includes(event.target.result)) {
            this.images.push(event.target.result);
            this.myForm.patchValue({
              fileSource: this.images
            });
          }
        };
        reader.readAsDataURL(event.target.files[i]);
      }
    }
  }

  submit() {
    Swal.fire({
      title: 'It will take just a second to upload images!',
      allowOutsideClick: false,
      showCancelButton: false,
      showConfirmButton: false,
      onBeforeOpen: () => {
        Swal.showLoading();
      },
    });

    const locationObj = {
      'place': this.myForm.value.place,
      'latitude': this.myForm.value.location.lat,
      'longitude': this.myForm.value.location.lng
    };

    let imagesCopy: any = [];

    for (let p of this.images) {
      imagesCopy.push(p.split(',')[1]);
    }

    const obj = {
      'name': this.myForm.value.name,
      'description': this.myForm.value.description,
      'type': this.myForm.value.type,
      'images': imagesCopy,
      'location': locationObj
    };

    this.offerService.createOffer(obj)
      .subscribe(response => {
        Swal.close();
        Swal.fire({
          title: 'Success!',
          text: 'Cultural offer successfully created!',
          icon: 'success',
          confirmButtonText: 'OK'
        });
        this.route.navigate(['/home-page/']);

      }, error => {
        let msg = "";
        if (error.status === 413) {  // PAYLOAD_TOO_LARGE
          msg = "Image size must be less than 64Kb!";
        } else if (error.status === 400) {  // BAD_REQUEST
          msg = "Cultural offer with that name already exists!";
        }
        Swal.fire({
          title: 'Error!',
          text: msg,
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'OK'
        });
      });
  }
}
