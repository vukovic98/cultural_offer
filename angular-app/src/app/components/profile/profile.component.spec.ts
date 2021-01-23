import {ComponentFixture, fakeAsync, flush, getTestBed, TestBed} from '@angular/core/testing';

import { ProfileComponent } from './profile.component';
import {EditProfileService} from "../../services/edit-profile.service";
import {HttpClient, HttpHandler} from "@angular/common/http";
import {HttpTestingController} from "@angular/common/http/testing";
import {ChangePasswordService} from "../../services/change-password.service";
import {of} from "rxjs";
import {NO_ERRORS_SCHEMA} from "@angular/core";
import {SignUpComponent} from "../sign-up/sign-up.component";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import Swal from "sweetalert2";
import {ControlContainer, FormBuilder, FormsModule, ReactiveFormsModule} from "@angular/forms";

describe('ProfileComponent', () => {
  let component: ProfileComponent;
  let fixture: ComponentFixture<ProfileComponent>;
  let editProfileService: EditProfileService;
  let router: any;
  let swal: any;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfileComponent ],
      providers: [EditProfileService, HttpClient, HttpHandler]
    })
    .compileComponents();
  });

  beforeEach(() => {
    let editProfileServiceMock = {
      editUser: jasmine.createSpy('editUser').
        and.returnValue(of({})),
      getUser: jasmine.createSpy('getUser').
        and.returnValue(of({
        "firstName": "Test",
        "lastName": "Test",
        "email": "test@maildrop.cc",
        "id": 1,
        "password":'123456da'
      }))
    };
    let routerMock = {
      navigate: jasmine.createSpy('navigate'),
      queryParams: jasmine.createSpy('queryParams')
    };
    let swalMock = {
      fire: jasmine.createSpy('fire').
      and.returnValue(of({
        position: 'center',
        title: 'Changes saved!',
        icon: 'success',
        showConfirmButton: false,
        timer: 1500
      }))
    };

    TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      declarations: [SignUpComponent],
      providers:[
        {provide: EditProfileService, useValue: editProfileServiceMock},
        {provide: Router, useValue: routerMock},
        { provide: Swal, useValue: swalMock},
        FormsModule,
        FormBuilder,
        ReactiveFormsModule
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(ProfileComponent);
    component = fixture.componentInstance;
    editProfileService= TestBed.inject(EditProfileService);
    router = TestBed.inject(Router);
    // @ts-ignore
    swal = TestBed.inject(Swal);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should get currently logged user', fakeAsync(() => {
    component.getUser();
    expect(component.firstName).toEqual("Test");
    expect(component.lastName).toEqual("Test");
    expect(component.email).toEqual("test@maildrop.cc");
    expect(component.editProfileForm.controls['email'].value).toEqual("test@maildrop.cc");
    expect(component.editProfileForm.controls['firstName'].value).toEqual("Test");
    expect(component.editProfileForm.controls['lastName'].value).toEqual("Test");
  }));

  it('should successfully edit user', fakeAsync(() => {
    let userMock = {
      "firstName": "Izmena",
      "lastName": "Izmena",
      "id": 1,
      "password":'123456da'
    };
    swal.fire({
      position: 'center',
      title: 'Changes saved!',
      icon: 'success',
      showConfirmButton: false,
      timer: 1500
    });


    (<ControlContainer> <unknown> component.editProfileForm.controls['firstName']).reset('Izmena');
    (<ControlContainer> <unknown> component.editProfileForm.controls['lastName']).reset('Izmena');
    component.id = 1;
    component.password = "123456da";
    component.editProfile();

    expect(editProfileService.editUser).toHaveBeenCalledWith(JSON.stringify(userMock),1);
    expect(swal.fire).toHaveBeenCalledWith({
      position: 'center',
      title: 'Changes saved!',
      icon: 'success',
      showConfirmButton: false,
      timer: 1500
    });
    flush();
  }));
});
