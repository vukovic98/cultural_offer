import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, fakeAsync, flush, TestBed } from '@angular/core/testing';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { RouterTestingModule } from '@angular/router/testing';
import { assert } from 'console';
import { of } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { CulturalOfferService } from 'src/app/services/culturalOffer.service';

import { PostListComponent } from './post-list.component';

describe('PostListComponent', () => {
  let component: PostListComponent;
  let fixture: ComponentFixture<PostListComponent>;
  let offerService: CulturalOfferService;
  let matDialog: MatDialog;
  beforeEach(() => {

    let offerServiceMock = {

      getPostsForOffer: jasmine.createSpy('getPostsForOffer')
        .and.returnValue(of({

          "content": [
            {
              "id": 3,
              "title": "PostTitle",
              "content": "Conent",
              "postTime": "2021-01-24T12:18:28.487940Z",
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
            },
            {
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
            }
          ],
          "totalElements": 2,
          "last": true,
          "totalPages": 1,
          "size": 5,
          "number": 0,
          "numberOfElements": 2,
          "first": true,
          "empty": false,
          "pageNumber": 0,
          "pageSize": 5

        }))
    };

    let matDialogMock = {
      open: jasmine.createSpy('open')
    };

    TestBed.configureTestingModule({
      declarations: [ PostListComponent ],
      providers: [
        {provide: CulturalOfferService, useValue: offerServiceMock},{ provide: MatDialog, useValue: matDialogMock }, AuthService]
      ,
      imports: [RouterTestingModule, HttpClientTestingModule, MatDialogModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostListComponent);
    offerService = TestBed.inject(CulturalOfferService);
    component = fixture.componentInstance;
    fixture.detectChanges();


  });

  it('should create', () => {
    expect(component).toBeTruthy();
    expect(offerService.getPostsForOffer).toHaveBeenCalled();
  });

  it('should retrieve posts', () => {

    component.pageNum = 1;

    component.retrievePosts();

    expect(offerService.getPostsForOffer).toHaveBeenCalled();

  });

  it('should reload posts on next page', () =>{

    let pageNum = component.pageNum;
    component.nextPage();
    expect(offerService.getPostsForOffer).toHaveBeenCalled();
    expect(component.pageNum).toBe(pageNum + 1);
  });

  it('should reload posts on previous page', () =>{
    let pageNum = component.pageNum;
    component.previousPage();
    expect(offerService.getPostsForOffer).toHaveBeenCalled();
    expect(component.pageNum).toBe(pageNum - 1);
  });

  it('should reload posts on first page', () =>{

    component.firstPage();
    expect(offerService.getPostsForOffer).toHaveBeenCalled();
    expect(component.pageNum).toBe(1);
  });

  it('should reload posts on last page', () =>{

    component.lastPage();
    expect(offerService.getPostsForOffer).toHaveBeenCalled();
    expect(component.pageNum).toBe(component.totalPages);

  });

});
