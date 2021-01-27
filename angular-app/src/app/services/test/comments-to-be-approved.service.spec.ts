import {fakeAsync, getTestBed, TestBed, tick} from '@angular/core/testing';

import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";
import {HttpClient, HttpEventType, HttpHeaders, HttpResponse} from "@angular/common/http";
import {RouterTestingModule} from "@angular/router/testing";
import {CommentsToBeApprovedService} from "../comments-to-be-approved.service";
import {CommentToBeApprovedModel} from "../../model/comment-to-be-approved-model";
import {statusCodeModel} from "../../model/auth-model";
import {PageObject} from '../../model/offer-mode';

describe('CommentsToBeApprovedService', () => {
  let service: CommentsToBeApprovedService;
  let httpMock: HttpTestingController;
  let httpClient: HttpClient;
  let injector;

  beforeEach(() => {

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule],
      providers: [CommentsToBeApprovedService]
    });

    injector = getTestBed();
    service = TestBed.inject(CommentsToBeApprovedService);
    httpClient = TestBed.inject(HttpClient);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should pass simple test', () => {
    expect(true).toBe(true);
  });
  it('getCommentsByPage() should return some pending comments',fakeAsync(() => {
    let pendingCommentsPage : PageObject<CommentToBeApprovedModel> = {
      content: [],
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
    };

    const mockComments  = {
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
    };

    service.getCommentsByPage(1).subscribe((response) => pendingCommentsPage = response);


    const req = httpMock.expectOne('https://localhost:8080/comments/pendingComments/1');
    expect(req.request.method).toBe('GET');
    req.flush(mockComments);

    tick();

    expect(pendingCommentsPage).toBeDefined();

    let comments = pendingCommentsPage;

    expect(comments.content.length).toEqual(2);

    expect(comments.content[0].id).toEqual(2);
    expect(comments.content[0].commenterEmail).toEqual("ana@maildrop.cc");
    expect(comments.content[0].commenterName).toEqual("Ana Maric");
    expect(comments.content[0].offer).toEqual("Nishvill");
    expect(comments.content[0].content).toEqual("Nije lepo");
    expect(comments.content[0].image).toBeNull();

    expect(comments.content[1].id).toEqual(3);
    expect(comments.content[1].commenterEmail).toEqual("kalina@maildrop.cc");
    expect(comments.content[1].commenterName).toEqual("Kalina Radic");
    expect(comments.content[1].offer).toEqual("Exit");
    expect(comments.content[1].content).toEqual("Ponuda je odlicna");
    expect(comments.content[1].image).toBeNull();

  }));
  it('getCommentsByPage() should not return comments for not existing page',fakeAsync(() => {
    let pendingCommentsPage : PageObject<CommentToBeApprovedModel> = {
      "content": [],
      "totalElements": 0,
      "last": true,
      "totalPages": 0,
      "size": 5,
      "number": 0,
      "numberOfElements": 0,
      "first": true,
      "empty": true,
      "pageNumber": 0,
      "pageSize": 5

    };

    const mockComments  = {
        "content": [],
        "totalElements": 0,
        "last": true,
        "totalPages": 0,
        "size": 5,
        "number": 0,
        "numberOfElements": 0,
        "first": true,
        "empty": true,
        "pageNumber": 0,
        "pageSize": 5

    };

    service.getCommentsByPage(1111).subscribe(response => pendingCommentsPage = response);


    const req = httpMock.expectOne('https://localhost:8080/comments/pendingComments/1111');
    expect(req.request.method).toBe('GET');
    req.flush(mockComments);

    tick();

    let comments = pendingCommentsPage;
    expect(comments).toBeDefined();
    expect(comments.content.length).toBe(0);
    expect(comments.numberOfElements).toEqual(0);
    expect(comments.totalElements).toEqual(0);
    expect(comments.empty).toEqual(true);




  }));
  it('approveComment() should approve pending comment by id', fakeAsync(() => {
    let approvedComment : CommentToBeApprovedModel = {
      commenterEmail: "",
      image: {
        picByte: "",
        id: "1"
      },
      commenterName: "",
      id: 1,
      content: "",
      offer: ""
    };

    const mockComment : CommentToBeApprovedModel = {
      commenterEmail: "ana@maildrop.cc",
      image: {
        picByte: "",
        id: "1"
      },
      commenterName: "Ana Maric",
      id: 1,
      content: "Lepa ponuda.",
      offer: "Exit"
    };

    service.approveComment(1).subscribe(res => approvedComment = res);
    const req = httpMock.expectOne("https://localhost:8080/comments/approve/1")
    expect(req.request.method).toBe('PUT');
    req.flush(mockComment);

    tick();

    expect(approvedComment).toBeDefined();
    expect(approvedComment.offer).toEqual("Exit");
    expect(approvedComment.content).toEqual("Lepa ponuda.");
    expect(approvedComment.commenterName).toEqual("Ana Maric");
    expect(approvedComment.commenterEmail).toEqual("ana@maildrop.cc");
    expect(approvedComment.id).toEqual(1);

  }));
  it('approveComment() should fail to approve pending comment by id', fakeAsync(() => {
    let code: statusCodeModel = {
     statusCode: 0
    };

    const mockCode : statusCodeModel = {
      statusCode: 400
    }

    service.approveComment(1).subscribe(res => code = res);
    const req = httpMock.expectOne("https://localhost:8080/comments/approve/1")
    expect(req.request.method).toBe('PUT');
    req.flush(mockCode);

    tick();

    expect(code.statusCode).toEqual(400);


  }));
  it('denyComment() should deny pending comment by id', () => {
    let responseStatus: statusCodeModel = {
      statusCode: 0
    };

    const mockCode: statusCodeModel = {
      statusCode: 200
    }
    service.denyComment(1).subscribe(res=> responseStatus = res.body);


    const req = httpMock.expectOne("https://localhost:8080/comments/1")
    expect(req.request.method).toBe('DELETE');
    req.flush(mockCode);
    expect(responseStatus.statusCode).toEqual(200);
  });
  it('denyComment() should fail to deny comment by id', () => {
    let responseStatus: statusCodeModel = {
      statusCode: 0
    };

    const mockCode: statusCodeModel = {
      statusCode: 404
    }
    service.denyComment(125).subscribe(res=> responseStatus = res.body);


    const req = httpMock.expectOne("https://localhost:8080/comments/125")
    expect(req.request.method).toBe('DELETE');
    req.flush(mockCode);
    expect(responseStatus.statusCode).toEqual(404);
  });

});
