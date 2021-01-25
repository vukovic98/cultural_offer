import {fakeAsync, getTestBed, TestBed, tick} from '@angular/core/testing';

import { ChangePasswordService } from "../change-password.service";
import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";
import {HttpClient} from "@angular/common/http";
import {RouterTestingModule} from "@angular/router/testing";
import {passwordDto} from "../../model/password-model";
import {userDto} from "../../model/userDto";
import {statusCodeModel} from "../../model/auth-model";

describe('ChangePasswordService', () => {
  let service: ChangePasswordService;
  let httpMock: HttpTestingController;
  let httpClient: HttpClient;
  let injector;
  beforeEach(() => {

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule],
      providers:    [ChangePasswordService]
    });

    injector = getTestBed();
    service = TestBed.inject(ChangePasswordService);
    httpClient = TestBed.inject(HttpClient);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should pass simple test', () => {
    expect(true).toBe(true);
  });
  it('changePassword() should change password', fakeAsync(() => {
    let user: userDto =
      {
        firstName: "Ivana",
        lastName: "Vlaisavljevic",
        email: "ivana@maildrop.cc",
        password: "elena123nova",
        id: 2
      };

    let passDto = {
      "oldPassword": "elena123",
      "newPassword":"elena123nova"
    }

    const mockUserDto =
      {
        firstName: "Ivana",
        lastName: "Vlaisavljevic",
        email: "ivana@maildrop.cc",
        password: "elena123nova",
        id: 2
      };

    service.changePassword(JSON.stringify(passDto)).subscribe(res => user = res);

    const req = httpMock.expectOne('https://localhost:8080/users/changePassword');
    expect(req.request.method).toBe('PUT');
    req.flush(mockUserDto);

    tick();

    expect(user).toBeDefined();
    expect(user.id).toEqual(2);
    expect(user.email).toEqual("ivana@maildrop.cc");
    expect(user.firstName).toEqual("Ivana");
    expect(user.lastName).toEqual("Vlaisavljevic");
    expect(user.password).toEqual("elena123nova");


  }));

  it('changePassword() should fail to change password', fakeAsync(() => {
    let statusCode: statusCodeModel = {
      statusCode: 0
    };

    let passDto = {
      "oldPassword": "elena123",
      "newPassword":"elena123nova"
    }

    const mockCode: statusCodeModel = {
      statusCode: 400
    }

    service.changePassword(JSON.stringify(passDto)).subscribe(res =>{
      statusCode = res;
    });

    const req = httpMock.expectOne('https://localhost:8080/users/changePassword');
    expect(req.request.method).toBe('PUT');
    req.flush(mockCode);

    tick();

    expect(statusCode.statusCode).toEqual(400);



  }));

});
