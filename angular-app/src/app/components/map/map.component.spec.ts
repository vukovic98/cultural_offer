import { ComponentFixture, TestBed } from "@angular/core/testing";
import { MapService } from "src/app/services/map.service";
import { MapComponent } from "./map.component";

describe('MapComponent', () => {
    let component: MapComponent;
    let fixture: ComponentFixture<MapComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
          declarations: [ MapComponent ],
          providers:    [ MapService ]
        })
        .compileComponents();
      });
    
      beforeEach(() => {
        fixture = TestBed.createComponent(MapComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    component.offers = [{
        "id": 1,
        "name": "Exit",
        "images": [],
        "location": {
          "locationId": 3,
          "latitude": 43.02593743203676,
          "longitude": 21.74925614031963,
          "place": "Bojnik"
        },
        "description": "ridiculus mus vivamus vestibulum sagittis sapien cum sociis natoque penatibus et magnis",
        "avgGrade": 4.0,
        "subscribersCount": 1.0
      },
      {
        "id": 2,
        "name": "Exit",
        "images": [],
        "location": {
          "locationId": 1,
          "latitude": 45.254410,
          "longitude": 19.842550,
          "place": "Novi Sad"
        },
        "description": "magnis dis parturient montes nascetur",
        "avgGrade": 5.0,
        "subscribersCount": 3.0
      }]
      });
    
      it('should create', () => {
        expect(component).toBeTruthy();
      });

      it('should place markers', () =>{

        expect(component.markers.getLayers().length).toBe(0);
        component.dataChanged();

        expect(component.markers.getLayers().length).toBe(2);


      });


    });