import {getTestBed, TestBed, inject, tick, fakeAsync} from '@angular/core/testing';

import {AuthService} from '../auth.service';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {HttpClient} from '@angular/common/http';
import {RouterTestingModule} from '@angular/router/testing';
import {Role, TokenModel} from '../../model/token.model';
import {loginResponse, signupResponse, statusCodeModel, verifyResponse} from '../../model/auth-model';
import {Router} from '@angular/router';
import {Status} from 'tslint/lib/runner';

describe('AuthService', () => {

  let injector;
  let authService: AuthService;
  let httpMock: HttpTestingController;
  let httpClient: HttpClient;
  let router: any;

  let routerMock = {
    navigate: jasmine.createSpy('navigate')
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule],
      providers:    [
        AuthService,
        { provide: Router, useValue: routerMock }
      ]
    });

    injector = getTestBed();
    authService = TestBed.inject(AuthService);
    httpClient = TestBed.inject(HttpClient);
    httpMock = TestBed.inject(HttpTestingController);
    router = TestBed.inject(Router);

  });

  it('should be created', () => {
    expect(authService).toBeTruthy();
  });

  it('should login user', fakeAsync(() => {
    let token: loginResponse = {
      "authenticationToken": "",
      "expiresAt": 0,
      "email": "",
      "verified": false
    };

    let newUser = {
      email: "vlado@gmail.com",
      password: "vukovic"
    }

    let tokenVal: String =  "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJLdWx0dXJhIG5hIERsYW51Iiwic3ViIjoidmxhZG9AZ21haWwuY29tIiwiYXVkIjoid2ViIiwiaWF0IjoxNjEwNzk4MDk4LCJleHAiOjE2MTA3OTk4OTgsInVzZXJfZmlyc3ROYW1lIjoiVmxhZGltaXIiLCJ1c2VyX2xhc3ROYW1lIjoiVnVrb3ZpYyIsInVzZXJfaWQiOiIyIiwiYXV0aG9yaXR5IjpbeyJpZCI6MiwibmFtZSI6IlJPTEVfQURNSU4iLCJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dfQ.an5nm14dWB1ENGFQpWZjpN31i4pstfCvD0PFF55lHW7s-zb8vAylJLivLPliwfh1DHxPaMHbe-vP1126qD35_A";


    const mockUser: any =
      {
        "authenticationToken": tokenVal,
        "expiresAt": 1800000,
        "email": "vlado@gmail.com",
        "verified": true
      }

    authService.login(JSON.stringify(newUser)).subscribe(data => {
      token = data;
    });

    const req = httpMock.expectOne('https://localhost:8080/auth/log-in');
    expect(req.request.method).toBe('POST');
    req.flush(mockUser);

    tick();

    expect(token.email).toEqual("vlado@gmail.com");
    expect(token.expiresAt).toEqual(1800000);
    expect(token.verified).toEqual(true);
  }));

  it('should fail to login user', fakeAsync(() => {
    let token: statusCodeModel = {
      statusCode: 0
    };

    let newUser = {
      email: "vlado45@gmail.com",
      password: "vukovic"
    }

    const mockUser: statusCodeModel = {
      statusCode: 404
    }

    authService.login(JSON.stringify(newUser)).subscribe(data => {
      token = data;
    });

    const req = httpMock.expectOne('https://localhost:8080/auth/log-in');
    expect(req.request.method).toBe('POST');
    req.flush(mockUser);

    tick();

    expect(token.statusCode).toBe(404);
  }));

  it('should verify users verification code', fakeAsync(() => {
    let verifyRes: verifyResponse = {
      "code": "",
      "userEmail": ""
    }

    let verifyDto = {
      "code": "ASDFG4",
      "userEmail": "vlado@gmail.com"
    };

    let mockResponse = {
      "code": "ASDFG4",
      "userEmail": "vlado@gmail.com"
    }

    authService.verifyCode(JSON.stringify(verifyDto)).subscribe(data => {
      verifyRes = data;
    });

    const req = httpMock.expectOne('https://localhost:8080/auth/verify');
    expect(req.request.method).toBe('POST');
    req.flush(mockResponse);

    tick();

    expect(verifyRes.code).toEqual("ASDFG4");
    expect(verifyRes.userEmail).toEqual("vlado@gmail.com");
  }));

  it('should fail verify users verification code', fakeAsync(() => {
    let verifyRes: statusCodeModel = {
      statusCode: 0
    };

    let verifyDto = {
      "code": "ASDFG4",
      "userEmail": "vlad344o@gmail.com"
    };

    let mockResponse: statusCodeModel = {
      statusCode: 404
    };

    authService.verifyCode(JSON.stringify(verifyDto)).subscribe(data => {
      verifyRes = data;
    });

    const req = httpMock.expectOne('https://localhost:8080/auth/verify');
    expect(req.request.method).toBe('POST');
    req.flush(mockResponse);

    tick();

    expect(verifyRes.statusCode).toBe(404);
  }));

  it('should sign up new user', fakeAsync(() => {
    let signUpRes: signupResponse = {
      id: 0,
      firstName: "",
      lastName: "",
      email: "",
      password: ""
    };

    let signUpDto = {
      "id": 0,
      "firstName": 'Pera',
      "lastName": "Peric",
      "email": "pera@maildrop.cc",
      "password": "vukovic"
    };

    let mockResponse = {
      "id": 56,
      "firstName": 'Pera',
      "lastName": "Peric",
      "email": "pera@maildrop.cc",
      "password": "$2a$10$nMZfWfEBE2qY1BZ.4Z25ye/LKwRD.wNzgtxre.8MUArJWGwQOqE2e"
    };

    authService.signUp(JSON.stringify(signUpDto)).subscribe(data => {
      signUpRes = data;
    });

    const req = httpMock.expectOne('https://localhost:8080/auth/sign-up');
    expect(req.request.method).toBe('POST');
    req.flush(mockResponse);

    tick();

    expect(signUpRes.id).toEqual(56);
    expect(signUpRes.email).toEqual("pera@maildrop.cc");
    expect(signUpRes.firstName).toEqual("Pera");
    expect(signUpRes.lastName).toEqual("Peric");
    expect(signUpRes.password).toEqual("$2a$10$nMZfWfEBE2qY1BZ.4Z25ye/LKwRD.wNzgtxre.8MUArJWGwQOqE2e");
  }));

  it('should fail to sign up new user', fakeAsync(() => {
    let signUpRes: string = "";

    let signUpDto = {
      "id": 0,
      "firstName": 'Pera',
      "lastName": "Peric",
      "email": "pera@maildrop.cc",
      "password": "vukovic"
    };

    let mockResponse: string = "Username already exists";

    authService.signUp(JSON.stringify(signUpDto)).subscribe(data => {
      signUpRes = data;
    });

    const req = httpMock.expectOne('https://localhost:8080/auth/sign-up');
    expect(req.request.method).toBe('POST');
    req.flush(mockResponse);

    tick();

    expect(signUpRes).toEqual("Username already exists");
  }));

  it('should remove token from storage', () => {
    localStorage.setItem("accessToken", "12345");

    authService.logout();

    expect(localStorage.getItem("accessToken")).toBeNull();

    expect(router.navigate).toHaveBeenCalledWith(
      ['/']);
  });

  it('should determine that user is admin', () => {
    const token = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJLdWx0dXJhIG5hIERsYW51Iiwic3ViIjoidmxhZG9AZ21haWwuY29tIiwiYXVkIjoid2ViIiwiaWF0IjoxNjEwNzk4MDk4LCJleHAiOjE2MTA3OTk4OTgsInVzZXJfZmlyc3ROYW1lIjoiVmxhZGltaXIiLCJ1c2VyX2xhc3ROYW1lIjoiVnVrb3ZpYyIsInVzZXJfaWQiOiIyIiwiYXV0aG9yaXR5IjpbeyJpZCI6MiwibmFtZSI6IlJPTEVfQURNSU4iLCJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dfQ.an5nm14dWB1ENGFQpWZjpN31i4pstfCvD0PFF55lHW7s-zb8vAylJLivLPliwfh1DHxPaMHbe-vP1126qD35_A";

    localStorage.setItem("accessToken", token);

    let isAdmin: boolean = authService.isAdmin();

    expect(isAdmin).toBeTrue();

    localStorage.removeItem("accessToken");
  });

  it('should determine that user is not admin', () => {
    const token = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJLdWx0dXJhIG5hIERsYW51Iiwic3ViIjoidmxhZGltaXJ2dWtvdmljOThAbWFpbGRyb3AuY2MiLCJhdWQiOiJ3ZWIiLCJpYXQiOjE2MTA4MDExMzQsImV4cCI6MTYxMDgwMjkzNCwidXNlcl9maXJzdE5hbWUiOiJWbGFkaW1pciIsInVzZXJfbGFzdE5hbWUiOiJWdWtvdmljIiwidXNlcl9pZCI6IjEiLCJhdXRob3JpdHkiOlt7ImlkIjoxLCJuYW1lIjoiUk9MRV9VU0VSIiwiYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dfQ.GF8a22wtTSiyExipTFGWD6KHPMfMgleoPIclYWmy6XG35VvsSi7vbelU4j18VyBtmvjfNCwaFWjUP32QodZNIw";

    localStorage.setItem("accessToken", token);

    let isAdmin: boolean = authService.isAdmin();

    expect(isAdmin).toBeFalse();

    localStorage.removeItem("accessToken");
  });

  it('should determine that user is registered user', () => {
    const token = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJLdWx0dXJhIG5hIERsYW51Iiwic3ViIjoidmxhZGltaXJ2dWtvdmljOThAbWFpbGRyb3AuY2MiLCJhdWQiOiJ3ZWIiLCJpYXQiOjE2MTA4MDExMzQsImV4cCI6MTYxMDgwMjkzNCwidXNlcl9maXJzdE5hbWUiOiJWbGFkaW1pciIsInVzZXJfbGFzdE5hbWUiOiJWdWtvdmljIiwidXNlcl9pZCI6IjEiLCJhdXRob3JpdHkiOlt7ImlkIjoxLCJuYW1lIjoiUk9MRV9VU0VSIiwiYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dfQ.GF8a22wtTSiyExipTFGWD6KHPMfMgleoPIclYWmy6XG35VvsSi7vbelU4j18VyBtmvjfNCwaFWjUP32QodZNIw";

    localStorage.setItem("accessToken", token);

    let isUser: boolean = authService.isUser();

    expect(isUser).toBeTrue();

    localStorage.removeItem("accessToken");

  });

  it('should determine that user is not registered user', () => {
    const token = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJLdWx0dXJhIG5hIERsYW51Iiwic3ViIjoidmxhZG9AZ21haWwuY29tIiwiYXVkIjoid2ViIiwiaWF0IjoxNjEwNzk4MDk4LCJleHAiOjE2MTA3OTk4OTgsInVzZXJfZmlyc3ROYW1lIjoiVmxhZGltaXIiLCJ1c2VyX2xhc3ROYW1lIjoiVnVrb3ZpYyIsInVzZXJfaWQiOiIyIiwiYXV0aG9yaXR5IjpbeyJpZCI6MiwibmFtZSI6IlJPTEVfQURNSU4iLCJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dfQ.an5nm14dWB1ENGFQpWZjpN31i4pstfCvD0PFF55lHW7s-zb8vAylJLivLPliwfh1DHxPaMHbe-vP1126qD35_A";

    localStorage.setItem("accessToken", token);

    let isUser: boolean = authService.isUser();

    expect(isUser).toBeFalse();

    localStorage.removeItem("accessToken");

  });

  it('should determine that user is loggedIn', () => {
    const token = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJLdWx0dXJhIG5hIERsYW51Iiwic3ViIjoidmxhZGltaXJ2dWtvdmljOThAbWFpbGRyb3AuY2MiLCJhdWQiOiJ3ZWIiLCJpYXQiOjE2MTA4MDExMzQsImV4cCI6MTYxMDgwMjkzNCwidXNlcl9maXJzdE5hbWUiOiJWbGFkaW1pciIsInVzZXJfbGFzdE5hbWUiOiJWdWtvdmljIiwidXNlcl9pZCI6IjEiLCJhdXRob3JpdHkiOlt7ImlkIjoxLCJuYW1lIjoiUk9MRV9VU0VSIiwiYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dfQ.GF8a22wtTSiyExipTFGWD6KHPMfMgleoPIclYWmy6XG35VvsSi7vbelU4j18VyBtmvjfNCwaFWjUP32QodZNIw";

    localStorage.setItem("accessToken", token);

    let isLoggedIn: boolean = authService.isLoggedIn();

    expect(isLoggedIn).toBeTrue();

    localStorage.removeItem("accessToken");

  });

  it('should determine that user is not loggedIn', () => {

    localStorage.removeItem("accessToken");

    let isLoggedIn: boolean = authService.isLoggedIn();

    expect(isLoggedIn).toBeFalse();


  });

  it('should decode token from storage', () => {
    const token = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJLdWx0dXJhIG5hIERsYW51Iiwic3ViIjoidmxhZGltaXJ2dWtvdmljOThAbWFpbGRyb3AuY2MiLCJhdWQiOiJ3ZWIiLCJpYXQiOjE2MTA4MDExMzQsImV4cCI6MTYxMDgwMjkzNCwidXNlcl9maXJzdE5hbWUiOiJWbGFkaW1pciIsInVzZXJfbGFzdE5hbWUiOiJWdWtvdmljIiwidXNlcl9pZCI6IjEiLCJhdXRob3JpdHkiOlt7ImlkIjoxLCJuYW1lIjoiUk9MRV9VU0VSIiwiYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dfQ.GF8a22wtTSiyExipTFGWD6KHPMfMgleoPIclYWmy6XG35VvsSi7vbelU4j18VyBtmvjfNCwaFWjUP32QodZNIw";

    localStorage.setItem("accessToken", token);

    let decodedToken: TokenModel | null = authService.decodeToken(token);

    expect(decodedToken).toBeTruthy();
    expect(decodedToken?.user_id).toBe("1");
    expect(decodedToken?.user_firstName).toBe("Vladimir");
    expect(decodedToken?.user_lastName).toBe("Vukovic");
    expect(decodedToken?.authority[0].authority).toBe("ROLE_USER");
  });

  it('should get users authorities from token', () => {
    const token = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJLdWx0dXJhIG5hIERsYW51Iiwic3ViIjoidmxhZGltaXJ2dWtvdmljOThAbWFpbGRyb3AuY2MiLCJhdWQiOiJ3ZWIiLCJpYXQiOjE2MTA4MDExMzQsImV4cCI6MTYxMDgwMjkzNCwidXNlcl9maXJzdE5hbWUiOiJWbGFkaW1pciIsInVzZXJfbGFzdE5hbWUiOiJWdWtvdmljIiwidXNlcl9pZCI6IjEiLCJhdXRob3JpdHkiOlt7ImlkIjoxLCJuYW1lIjoiUk9MRV9VU0VSIiwiYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dfQ.GF8a22wtTSiyExipTFGWD6KHPMfMgleoPIclYWmy6XG35VvsSi7vbelU4j18VyBtmvjfNCwaFWjUP32QodZNIw";

    localStorage.setItem("accessToken", token);

    let authorities: Array<Role> = authService.getUserAuthorities();

    expect(authorities.length).toBe(1);
    expect(authorities[0]).toBeTruthy();
    expect(authorities[0].authority).toBe("ROLE_USER");
  });

  it('should get users id from token', () => {
    const token = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJLdWx0dXJhIG5hIERsYW51Iiwic3ViIjoidmxhZGltaXJ2dWtvdmljOThAbWFpbGRyb3AuY2MiLCJhdWQiOiJ3ZWIiLCJpYXQiOjE2MTA4MDExMzQsImV4cCI6MTYxMDgwMjkzNCwidXNlcl9maXJzdE5hbWUiOiJWbGFkaW1pciIsInVzZXJfbGFzdE5hbWUiOiJWdWtvdmljIiwidXNlcl9pZCI6IjEiLCJhdXRob3JpdHkiOlt7ImlkIjoxLCJuYW1lIjoiUk9MRV9VU0VSIiwiYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dfQ.GF8a22wtTSiyExipTFGWD6KHPMfMgleoPIclYWmy6XG35VvsSi7vbelU4j18VyBtmvjfNCwaFWjUP32QodZNIw";

    localStorage.setItem("accessToken", token);

    let id: string = authService.getUserId();

    expect(id).toBeTruthy();
    expect(id).toBe("1");
  });

  it('should send code to user again', fakeAsync(() => {
    let response: string = "";

    let email = "vlado@gmail.com";

    const mockResponse: string = "";

    authService.sendCodeAgain(email).subscribe(data => {
      response = data;
    });

    const req = httpMock.expectOne('https://localhost:8080/auth/sendCodeAgain');
    expect(req.request.method).toBe('POST');
    req.flush(mockResponse);

    tick();
  }));

  it('should fail to send code to user again', fakeAsync(() => {
    let response: statusCodeModel = {
      statusCode: 0
    };

    let email = "vlado333@gmail.com";

    const mockResponse: statusCodeModel = {
      statusCode: 404
    };

    authService.sendCodeAgain(email).subscribe(data => {
      response = data;
    });

    const req = httpMock.expectOne('https://localhost:8080/auth/sendCodeAgain');
    expect(req.request.method).toBe('POST');
    req.flush(mockResponse);

    tick();

    expect(response.statusCode).toBe(404);
  }));
});
