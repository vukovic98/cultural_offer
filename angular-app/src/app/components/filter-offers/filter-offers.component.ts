import {Component, OnInit, Output, EventEmitter} from '@angular/core';
import {FilterOffersService} from "../../services/filter-offers.service";
import {FilterObject} from "../../model/filter-model";
import {CulturalOffer} from "../../model/offer-mode";


@Component({
  selector: 'app-filter-offers',
  templateUrl: './filter-offers.component.html',
  styleUrls: ['./filter-offers.component.css']
})
export class FilterOffersComponent implements OnInit {

  @Output() filterEvent: EventEmitter<FilterObject> = new EventEmitter<FilterObject>();

  types: string[] = [];
  selectedTypes: Array<any> = [];
  expression = '';
  filteredOffers: Array<CulturalOffer> = [];

  constructor(private service: FilterOffersService) { }

  onApplyFilter() {
    const filterParams: FilterObject = {
      exp: this.expression,
      types: this.selectedTypes
    };
    this.filterEvent.emit(filterParams);
  }


  getTypes(): void {
    this.service.getTypes()
      .subscribe(types => {
        this.types = types;
      });
  }

  addType($event: any){
    this.selectedTypes.push($event);
  }

  removeType($event: any){
    const index = this.selectedTypes.indexOf($event, 0);
    if (index > -1)
      this.selectedTypes.splice(index, 1);
  }

  ngOnInit(): void {
    this.getTypes();
  }

}
