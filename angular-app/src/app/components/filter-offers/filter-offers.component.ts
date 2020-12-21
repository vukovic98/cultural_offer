import {Component, OnInit, Output, EventEmitter, Input} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {FilterOffersService} from "../../services/filter-offers.service";
import {CulturalOfferService} from "../../services/culturalOffer.service";
import {FilterObject} from "../../model/filter-model";
import {CulturalOffer} from "../../model/offer-mode";


@Component({
  selector: 'app-filter-offers',
  templateUrl: './filter-offers.component.html',
  styleUrls: ['./filter-offers.component.css']
})
export class FilterOffersComponent implements OnInit {

  @Output() filterEvent: EventEmitter<FilterObject> = new EventEmitter<FilterObject>();

  types: String[] = [];
  selectedTypes: Array<any> = [];
  expression = '';
  filteredOffers: Array<CulturalOffer> = [];

 filterForm = new FormGroup({
    "expression": new FormControl('',null),
    "types": new FormControl(this.types)
  });

  constructor(private service: FilterOffersService) { }

  onApplyFilter(){
    let filterParams: FilterObject = {
      exp: this.expression,
      types: this.selectedTypes
    };

    this.filterEvent.emit(filterParams);
    console.log("Expression: "+this.expression);
    console.log("Types: "+ this.selectedTypes);

}
  filterOffers(){
    //this.culturalOfferService.getByPageFilter(1,this.expression,this.selectedTypes);


  }

  getTypes():void{
    this.service.getTypes()
      .subscribe((types: String[]) => {
        this.types = types;
      });
  }

  addType($event: any){
    this.selectedTypes.push($event);
    console.log("Dodat! selected:"+this.selectedTypes);

  }

  removeType($event: any){
    let index = this.selectedTypes.indexOf($event.value, 0);
    if (index > -1)
      this.selectedTypes.splice(index, 1);

    console.log("Obrisan! selected:"+this.selectedTypes);
  }

  ngOnInit(): void {
    this.getTypes();

  }


}
