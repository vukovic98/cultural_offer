import {ComponentFixture, fakeAsync, TestBed} from '@angular/core/testing';

import { SignUpComponent } from './sign-up.component';
import {AuthService} from "../../services/auth.service";
import {HttpClient, HttpHandler} from "@angular/common/http";
import {Router} from "@angular/router";
import {RouterTestingModule} from "@angular/router/testing";
import {of} from "rxjs";
import {Test} from "tslint";
import {ControlContainer, FormBuilder, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NO_ERRORS_SCHEMA} from "@angular/core";
import Swal from "sweetalert2";

describe('SignUpComponent', () => {
  let component: SignUpComponent;
  let fixture: ComponentFixture<SignUpComponent>;
  let authService: any;
  let router: any;
  let swal: any;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SignUpComponent ],
      providers: [AuthService, HttpClient, HttpHandler, RouterTestingModule, Router]
    })
    .compileComponents();
  });

  beforeEach(() => {
    let authServiceMock = {
      signUp: jasmine.createSpy('signUp').
        and.returnValue(of({
        "id": 0,
        "firstName": "Test",
        "lastName": 'Test',
        "email": 'test@maildrop.cc',
        "password": '123456da'
      }))
    }
    let routerMock = {
      navigate: jasmine.createSpy('navigate')
    };
    let swalMock = {
      fire: jasmine.createSpy('fire').
      and.returnValue(of({
        title: 'Error!',
        text: 'User with this email already exists!',
        icon: 'error',
        confirmButtonColor: '#DC143C',
        confirmButtonText: 'OK'
      }))
    };
    TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      declarations: [SignUpComponent],
      providers:[
        {provide: AuthService, useValue: authServiceMock},
        {provide: Router, useValue: routerMock},
        { provide: Swal, useValue: swalMock},
        FormsModule,
        FormBuilder,
        ReactiveFormsModule
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(SignUpComponent);
    component = fixture.componentInstance;
    authService = TestBed.inject(AuthService);
    router = TestBed.inject(Router);
    // @ts-ignore
    swal = TestBed.inject(Swal);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should successfully sign up', fakeAsync( () => {
    expect(component.signupForm.invalid).toBeTruthy();

    expect(component.signupForm.value.firstName).not.toEqual('Test');


    (<ControlContainer> <unknown> component.signupForm.controls['firstName']).reset('Test');
    (<ControlContainer> <unknown> component.signupForm.controls['lastName']).reset('Test');
    (<ControlContainer> <unknown> component.signupForm.controls['email']).reset('test@maildrop.cc');
    (<ControlContainer> <unknown> component.signupForm.controls['password']).reset('123456da');
    (<ControlContainer> <unknown> component.signupForm.controls['passConfirm']).reset('123456da');
    const signUpDto = {
      "id": 0,
      "firstName": "Test",
      "lastName": 'Test',
      "email": 'test@maildrop.cc',
      "password": '123456da'
    };
    const params = Object({  queryParams: Object({  email: 'test@maildrop.cc'}) });
    component.signUp();
    expect(authService.signUp).toHaveBeenCalledWith(JSON.stringify(signUpDto));
    expect(component.signupForm.value.email).toEqual('test@maildrop.cc');
    expect(component.signupForm.value.firstName).toEqual('Test');
    expect(component.signupForm.value.lastName).toEqual('Test');
    expect(component.signupForm.value.password).toEqual('123456da');
    expect(router.navigate).toHaveBeenCalledWith(['auth/verify'], params);

  }));

  it('should fail to sign up', fakeAsync( () => {
    expect(component.signupForm.invalid).toBeTruthy();

    expect(component.signupForm.value.firstName).not.toEqual('Test');


    (<ControlContainer> <unknown> component.signupForm.controls['firstName']).reset('Test');
    (<ControlContainer> <unknown> component.signupForm.controls['lastName']).reset('Test');
    (<ControlContainer> <unknown> component.signupForm.controls['email']).reset('test@maildrop.cc');
    (<ControlContainer> <unknown> component.signupForm.controls['password']).reset('123456da');
    (<ControlContainer> <unknown> component.signupForm.controls['passConfirm']).reset('123456da');

    const signUpDto = {
      "id": 0,
      "firstName": "Test",
      "lastName": 'Test',
      "email": 'test@maildrop.cc',
      "password": '123456da'
    };
    component.signUp();
    expect(authService.signUp).toHaveBeenCalledWith(JSON.stringify(signUpDto));
    expect(component.signupForm.value.email).toEqual('test@maildrop.cc');
    expect(component.signupForm.value.firstName).toEqual('Test');
    expect(component.signupForm.value.lastName).toEqual('Test');
    expect(component.signupForm.value.password).toEqual('123456da');
    swal.fire({
      title: 'Error!',
      text: 'User with this email already exists!',
      icon: 'error',
      confirmButtonColor: '#DC143C',
      confirmButtonText: 'OK'
    });

    expect(swal.fire).toHaveBeenCalledWith({
      title: 'Error!',
      text: 'User with this email already exists!',
      icon: 'error',
      confirmButtonColor: '#DC143C',
      confirmButtonText: 'OK'
    });
  }));
});
