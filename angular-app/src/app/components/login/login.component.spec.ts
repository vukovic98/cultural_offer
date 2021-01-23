import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginComponent } from './login.component';
import {AuthService} from '../../services/auth.service';
import {Test} from 'tslint';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {Router} from '@angular/router';
import {By} from '@angular/platform-browser';
import {MatSlideToggle} from '@angular/material/slide-toggle';
import {of} from 'rxjs';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let authService: AuthService;
  let router: Router;

  let authServiceMock = {
    login: jasmine.createSpy('login')
      .and.returnValues(of({
        "authenticationToken": "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJLdWx0dXJhIG5hIERsYW" +
          "51Iiwic3ViIjoidmxhZGltaXJ2dWtvdmljOThAbWFpbGRyb3AuY2MiLCJhdWQiOiJ3ZWIiLCJpYXQiOjE" +
          "2MTEzMjUzNzIsImV4cCI6MTYxMTMyNzE3MiwidXNlcl9maXJzdE5hbWUiOiJWbGFkaW1pciIsInVzZXJfbGFzdE5h" +
          "bWUiOiJWdWtvdmljIiwidXNlcl9pZCI6IjEiLCJhdXRob3JpdHkiOlt7ImlkIjoxLCJuYW1lIjoiUk9MRV9VU" +
          "0VSIiwiYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dfQ.AiY45A_plo94hfDrojPtYKIrVnKI_xgwytimnHsKGVSFDG" +
          "YyPDheeHQUu4Acqd7FFKOJLrd7dJfwrWUpSmTWNQ",
        "expiresAt": 1800000,
        "email": "vladimirvukovic98@maildrop.cc",
        "verified": true
      }),
        of({
          "authenticationToken": "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJLdWx0dXJhIG5hIERsYW" +
            "51Iiwic3ViIjoidmxhZGltaXJ2dWtvdmljOThAbWFpbGRyb3AuY2MiLCJhdWQiOiJ3ZWIiLCJpYXQiOjE" +
            "2MTEzMjUzNzIsImV4cCI6MTYxMTMyNzE3MiwidXNlcl9maXJzdE5hbWUiOiJWbGFkaW1pciIsInVzZXJfbGFzdE5h" +
            "bWUiOiJWdWtvdmljIiwidXNlcl9pZCI6IjEiLCJhdXRob3JpdHkiOlt7ImlkIjoxLCJuYW1lIjoiUk9MRV9VU" +
            "0VSIiwiYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dfQ.AiY45A_plo94hfDrojPtYKIrVnKI_xgwytimnHsKGVSFDG" +
            "YyPDheeHQUu4Acqd7FFKOJLrd7dJfwrWUpSmTWNQ",
          "expiresAt": 1800000,
          "email": "vladimirvukovic98@maildrop.cc",
          "verified": false
        }))
  };

  let routerMock = {
    navigate: jasmine.createSpy('navigate')
  };

  beforeEach( () => {
    TestBed.configureTestingModule({
      imports: [
        BrowserAnimationsModule
      ],
      declarations: [ LoginComponent ],
      providers: [
        { provide: AuthService, useValue: authServiceMock },
        { provide: Router, useValue: routerMock }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    authService = TestBed.inject(AuthService);
    router = TestBed.inject(Router);

    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should login verified and unverified user', () => {
    component.loginForm.controls['email'].setValue('vladimirvukovic98@maildrop.cc');
    component.loginForm.controls['password'].setValue('vukovic');

    let loginDto = {
      "email": 'vladimirvukovic98@maildrop.cc',
      "password": 'vukovic'
    };

    fixture.detectChanges();

    component.loginUser();

    expect(authService.login).toHaveBeenCalled();
    expect(authService.login).toHaveBeenCalledWith(JSON.stringify(loginDto));
    expect(router.navigate).toHaveBeenCalled();
    expect(router.navigate).toHaveBeenCalledWith(["/"]);

    component.loginUser();
    expect(authService.login).toHaveBeenCalled();
    expect(authService.login).toHaveBeenCalledWith(JSON.stringify(loginDto));
    expect(router.navigate).toHaveBeenCalled();
    expect(router.navigate).toHaveBeenCalledWith(["/auth/verify"], Object({ queryParams: Object({ email: 'vladimirvukovic98@maildrop.cc' }) }));
  });

});
