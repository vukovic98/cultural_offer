import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { CulturalOfferService } from 'src/app/services/culturalOffer.service';
import { RouterTestingModule } from '@angular/router/testing';
import { CommentListComponent } from './comment-list.component';

describe('CommentListComponent', () => {
  let component: CommentListComponent;
  let fixture: ComponentFixture<CommentListComponent>;
  let offerService: CulturalOfferService;

  beforeEach(() => {

    const offerServiceMock = {

      getCommentsForOffer: jasmine.createSpy('getCommentsForOffer')
      .and.returnValue(of({
        "content": [
          {
            "id": 14,
            "commenterEmail": "dulex@maildrop.cc",
            "commenterName": "Test Testnic",
            "content": "ASD",
            "image": {
              "id": 15,
              "picByte": "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"
            },
            "offer": null
          }
        ],
        "totalElements": 1,
        "last": true,
        "totalPages": 1,
        "size": 5,
        "number": 0,
        "numberOfElements": 1,
        "first": true,
        "empty": false,
        "pageNumber": 0,
        "pageSize": 5
      }))

    };

    TestBed.configureTestingModule({
      declarations: [ CommentListComponent ],
      providers: [
        { provide: CulturalOfferService, useValue: offerServiceMock }, AuthService
      ],
      imports: [HttpClientTestingModule, RouterTestingModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CommentListComponent);
    offerService = TestBed.inject(CulturalOfferService);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should retrieve comments', () => {
    component.pageNum = 1;

    component.retrieveComments();

    expect(offerService.getCommentsForOffer).toHaveBeenCalled();
  });

  it('should reload comments on next page', () => {

    const pageNum = component.pageNum;
    component.nextPage();
    expect(offerService.getCommentsForOffer).toHaveBeenCalled();
    expect(component.pageNum).toBe(pageNum + 1);
  });

  it('should reload comments on previous page', () => {
    const pageNum = component.pageNum;
    component.previousPage();
    expect(offerService.getCommentsForOffer).toHaveBeenCalled();
    expect(component.pageNum).toBe(pageNum - 1);
  });

  it('should reload comments on first page', () => {

    component.firstPage();
    expect(offerService.getCommentsForOffer).toHaveBeenCalled();
    expect(component.pageNum).toBe(1);
  });

  it('should reload comments on last page', () => {

    component.lastPage();
    expect(offerService.getCommentsForOffer).toHaveBeenCalled();
    expect(component.pageNum).toBe(component.totalPages);

  });

});
