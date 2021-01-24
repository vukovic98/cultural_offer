import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { FilterObject } from "../model/filter-model";
import { CulturalOffer } from "../model/offer-mode";


@Injectable()
export class MapService {
    myMethod$: Observable<any>;
    private myMethodSubject = new Subject<any>();

    constructor() {
        this.myMethod$ = this.myMethodSubject.asObservable();
    }

    myMethod(offers: Array<CulturalOffer>): void {
        this.myMethodSubject.next(offers);
    }
}