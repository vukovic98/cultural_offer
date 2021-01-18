import {ComponentFixture, getTestBed, TestBed} from '@angular/core/testing';

import { CommentsToBeApprovedListComponent } from './comments-to-be-approved-list.component';
import {CommentsToBeApprovedService} from "../../services/comments-to-be-approved.service";
import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";
import {HttpClient} from "@angular/common/http";
import {RouterTestingModule} from "@angular/router/testing";

describe('CommentsToBeApprovedListComponent', () => {
  let component: CommentsToBeApprovedListComponent;
  let fixture: ComponentFixture<CommentsToBeApprovedListComponent>;
  let service: CommentsToBeApprovedService;
  let injector;
  let httpMock: HttpTestingController;
  let httpClient: HttpClient;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule],
      declarations: [ CommentsToBeApprovedListComponent ],
      providers: [CommentsToBeApprovedService]
    })
    .compileComponents();
    injector = getTestBed();
    service = TestBed.inject(CommentsToBeApprovedService);
    httpClient = TestBed.inject(HttpClient);
    httpMock = TestBed.inject(HttpTestingController);
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CommentsToBeApprovedListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
