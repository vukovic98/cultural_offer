import {ComponentFixture, fakeAsync, flush, TestBed} from '@angular/core/testing';

import { UserVerificationComponent } from './user-verification.component';
import {AuthService} from "../../services/auth.service";
import {of} from "rxjs";
import {NO_ERRORS_SCHEMA} from "@angular/core";
import {SignUpComponent} from "../sign-up/sign-up.component";
import {ActivatedRoute, ActivatedRouteSnapshot, convertToParamMap, Router} from "@angular/router";
import Swal, {SweetAlertResult} from "sweetalert2";
import {ControlContainer, FormBuilder, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClient, HttpHandler} from "@angular/common/http";
import {RouterTestingModule} from "@angular/router/testing";
import {A} from "@angular/cdk/keycodes";

describe('UserVerificationComponent', () => {
  let component: UserVerificationComponent;
  let fixture: ComponentFixture<UserVerificationComponent>;
  let authService: any;
  let router: any;
  let activateRouter: any;
  let swal: any;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserVerificationComponent],
      providers: [AuthService, HttpClient, HttpHandler]

    })
    .compileComponents();
  });

  beforeEach(() => {
    let authServiceMock = {
      verifyCode: jasmine.createSpy('verifyCode').
      and.returnValue(of({
        "code": 'CODE123',
        "userEmail": 'test@maildrop.cc',
      })),
      sendCodeAgain: jasmine.createSpy('sendCodeAgain').
        and.returnValue(of("CODE123"))
    }

    let routerMock = {
      navigate: jasmine.createSpy('navigate')
    };


    let swalMock = {
      fire: jasmine.createSpy('fire').
      and.returnValue(of({}))
    };


    TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      declarations: [UserVerificationComponent],
      imports: [RouterTestingModule],
      providers:[
        {provide: AuthService, useValue: authServiceMock},
        {provide: Router, useValue: routerMock},
        { provide: Swal, useValue: swalMock},
        {
          provide: ActivatedRoute, useValue: {
            queryParams: of({ email: 'test@maildrop.cc' })
          }
        },
        FormsModule,
        FormBuilder,
        ReactiveFormsModule
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(UserVerificationComponent);
    component = fixture.componentInstance;
    authService = TestBed.inject(AuthService);
    router = TestBed.inject(Router);
    // @ts-ignore
    swal = TestBed.inject(Swal);
    activateRouter = TestBed.inject(ActivatedRoute);

    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should verify user', fakeAsync(() => {
    expect(component.verifyForm).toBeTruthy();

    expect(component.verifyForm.value.code).not.toEqual('CODE123');

    (<ControlContainer> <unknown> component.verifyForm.controls['code']).reset('CODE123');

    const verifyDto = {
      "code": "CODE123",
      "userEmail": "test@maildrop.cc",
    };

    component.verifyUser();

    expect(authService.verifyCode).toHaveBeenCalledWith(JSON.stringify(verifyDto));

    expect(component.verifyForm.value.code).toEqual('CODE123');

    swal.fire({
      title: 'Successful verification!',
      icon: 'success',
      confirmButtonColor: '#287507',
      confirmButtonText: 'Go to login page'
    });

    expect(swal.fire).toHaveBeenCalledWith({
      title: 'Successful verification!',
        icon: 'success',
        confirmButtonColor: '#287507',
        confirmButtonText: 'Go to login page'
    });
  flush();
  }));

  it('should send code again', fakeAsync(() => {
    component.sendCodeAgain("test@maildrop.cc");
    expect(authService.sendCodeAgain).toHaveBeenCalledWith("test@maildrop.cc");
    flush();
  }));
});
