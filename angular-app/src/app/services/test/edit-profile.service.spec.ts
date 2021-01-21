import {fakeAsync, getTestBed, TestBed, tick} from '@angular/core/testing';

import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";
import {HttpClient} from "@angular/common/http";
import {RouterTestingModule} from "@angular/router/testing";
import {EditProfileService} from "../edit-profile.service";
import {userDto} from "../../model/userDto";
import {statusCodeModel} from "../../model/auth-model";

describe('EditProfileService', () => {
  let service: EditProfileService;
  let httpMock: HttpTestingController;
  let httpClient: HttpClient;
  let injector;

  beforeEach(() => {

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule],
      providers: [EditProfileService]
    });

    injector = getTestBed();
    service = TestBed.inject(EditProfileService);
    httpClient = TestBed.inject(HttpClient);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should pass simple test', () => {
    expect(true).toBe(true);
  });
  it('editUser() should edit user by user id', fakeAsync(() => {
    let user = {
      "firstName": "Ivana",
      "lastName": "Vlaisavljevic",
      "email": "ivana@maildrop.cc",
      "id": 27,
      "password":"lozinka123"
    };


    const mockUser: userDto =
      {
       firstName : "Ivana",
        lastName : "Vlaisavljevic",
        email : "ivana@maildrop.cc",
        id : 27,
        password : "lozinka123"
      };

    service.editUser(JSON.stringify(user), user.id).subscribe(res => user = res);

    const req = httpMock.expectOne('http://localhost:8080/users/editProfile/'+user.id);
    expect(req.request.method).toBe('PUT');
    req.flush(mockUser);

    tick();

    expect(user).toBeDefined();
    expect(user.firstName).toEqual("Ivana");
    expect(user.lastName).toEqual("Vlaisavljevic");
    expect(user.email).toEqual("ivana@maildrop.cc");
    expect(user.id).toEqual(27);
    expect(user.password).toEqual("lozinka123");

  }));
  it('editUser() should fail to edit user by user id', fakeAsync(() => {
    let statusCode: statusCodeModel = {
      statusCode: 0
    };

    let user = {
      "firstName": "Ivana",
      "lastName": "Vlaisavljevic",
      "email": "ivana@maildrop.cc",
      "id": 27,
      "password":"lozinka123"
    };

    const mockCode: statusCodeModel = {
      statusCode: 400
    };

    service.editUser(JSON.stringify(user), user.id).subscribe(res => statusCode = res);

    const req = httpMock.expectOne('http://localhost:8080/users/editProfile/'+user.id);
    expect(req.request.method).toBe('PUT');
    req.flush(mockCode);

    tick();

    expect(statusCode).toBeDefined();
    expect(statusCode.statusCode).toEqual(400);


  }));
  it('getUser() should return currently looged user',fakeAsync(() => {
    let loggedUser : userDto = {
      firstName: "",
      lastName: "",
      email: "",
      id: 1,
      password: ""
    };

    const mockUser : userDto = {
      firstName : "Ivana",
      lastName : "Vlaisavljevic",
      email : "ivana@maildrop.cc",
      id : 27,
      password : "lozinka123"
    }

    service.getUser().subscribe(res => loggedUser = res);

    const req = httpMock.expectOne('http://localhost:8080/users/loggedInUser');
    expect(req.request.method).toBe('GET');
    req.flush(mockUser);

    tick();

    expect(loggedUser).toBeDefined();
    expect(loggedUser.firstName).toEqual("Ivana");
    expect(loggedUser.lastName).toEqual("Vlaisavljevic");
    expect(loggedUser.email).toEqual("ivana@maildrop.cc");
    expect(loggedUser.id).toEqual(27);
    expect(loggedUser.password).toEqual("lozinka123");

  }));
  it('getUser() should fail to return currently looged user',fakeAsync(() => {
    let statusCode: statusCodeModel = {
      statusCode: 0
    };

    const mockCode : statusCodeModel = {
      statusCode: 404
    };

    service.getUser().subscribe(res => statusCode = res);

    const req = httpMock.expectOne('http://localhost:8080/users/loggedInUser');
    expect(req.request.method).toBe('GET');
    req.flush(mockCode);

    tick();

    expect(statusCode).toBeDefined();
    expect(statusCode.statusCode).toEqual(404);


  }));
});
