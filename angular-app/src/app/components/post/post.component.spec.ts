import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, fakeAsync, flush, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { CulturalOfferService } from 'src/app/services/culturalOffer.service';

import { PostComponent } from './post.component';

describe('PostComponent', () => {
  let component: PostComponent;
  let fixture: ComponentFixture<PostComponent>;
  let offerService: CulturalOfferService;

  beforeEach( () => {
    let offerServiceMock = {
      deletePost: jasmine.createSpy('deletePost')
        .and.returnValue(of({body: {statusCode: 200}}))
    }

    let routerMock = {
      navigate: jasmine.createSpy('navigate')
    };

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule],
      declarations: [ PostComponent ],
      providers:    [ {provide: CulturalOfferService, useValue: offerServiceMock }, AuthService]
    });

    fixture = TestBed.createComponent(PostComponent);
    component    = fixture.componentInstance;
    offerService = TestBed.inject(CulturalOfferService);

    component.post = {
      "id": 4,
      "title": "PostTitle",
      "content": "Conent",
      "postTime": "2021-01-24T12:18:29.987908Z",
      "offer": {
        "id": 1,
        "name": "Exit",
        "images": [],
        "location": {
          "locationId": 1,
          "latitude": 15,
          "longitude": 15,
          "place": "NoviSad"
        },
        "description": "ASdfg",
        "avgGrade": 5,
        "subscribersCount": 0
      }
    };

    fixture.detectChanges();

  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should delete comment from table for existing id', fakeAsync(() => {
    component.onClickDeletePost();

    expect(offerService.deletePost).toHaveBeenCalledWith(4);
    flush();
  }));

});
