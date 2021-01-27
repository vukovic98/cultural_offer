import {ComponentFixture, fakeAsync, flush, TestBed} from '@angular/core/testing';
import { CommentsToBeApprovedListComponent } from './comments-to-be-approved-list.component';
import {CommentsToBeApprovedService} from "../../services/comments-to-be-approved.service";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {RouterTestingModule} from "@angular/router/testing";
import {NO_ERRORS_SCHEMA} from "@angular/core";
import {of} from "rxjs";

describe('CommentsToBeApprovedListComponent', () => {
  let component: CommentsToBeApprovedListComponent;
  let fixture: ComponentFixture<CommentsToBeApprovedListComponent>;
  let service: CommentsToBeApprovedService;


  beforeEach( () => {

    const commentToBeApprovedServiceMock = {
      getCommentsByPage: jasmine.createSpy('getCommentsByPage').
        and.returnValue(of(({
      "content":
      [{
        "id": 2,
        "commenterEmail": "ana@maildrop.cc",
        "commenterName": "Ana Maric",
        "content": "Nije lepo",
        "image": null,
        "offer": "Nishvill"
      },
        {
          "id": 3,
          "commenterEmail": "kalina@maildrop.cc",
          "commenterName": "Kalina Radic",
          "content": "Ponuda je odlicna",
          "image": null,
          "offer": "Exit"
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

      }))),
      approveComment: jasmine.createSpy('approveComment').
        and.returnValue(of({

      })),
      denyComment: jasmine.createSpy('denyComment').
      and.returnValue(of({

      }))
    };

    TestBed.configureTestingModule({
      declarations: [ CommentsToBeApprovedListComponent ],
      providers:    [
        { provide: CommentsToBeApprovedService, useValue: commentToBeApprovedServiceMock },
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(CommentsToBeApprovedListComponent);
    component = fixture.componentInstance;
    service = TestBed.inject(CommentsToBeApprovedService);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should get all pending comments by page', fakeAsync(() => {
    component.getCommentsByPage();
    expect(service.getCommentsByPage).toHaveBeenCalled();
    expect(component.commentsToBeApproved.length).toEqual(2);
  }));

  it('should approve comment', fakeAsync(() => {
    component.approveComment(1);
    flush();
    expect(service.approveComment).toHaveBeenCalledWith(1);
  }));
});
