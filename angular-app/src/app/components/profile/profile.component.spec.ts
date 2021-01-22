import {ComponentFixture, getTestBed, TestBed} from '@angular/core/testing';

import { ProfileComponent } from './profile.component';
import {EditProfileService} from "../../services/edit-profile.service";
import {HttpClient, HttpHandler} from "@angular/common/http";
import {HttpTestingController} from "@angular/common/http/testing";
import {ChangePasswordService} from "../../services/change-password.service";

describe('ProfileComponent', () => {
  let component: ProfileComponent;
  let fixture: ComponentFixture<ProfileComponent>;
  let httpClient: HttpClient;
  let service: EditProfileService;
  let httpMock: HttpTestingController;
  let injector;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfileComponent ],
      providers: [EditProfileService, HttpClient, HttpHandler]
    })
    .compileComponents();
    injector = getTestBed();
    service = TestBed.inject(EditProfileService);
    httpClient = TestBed.inject(HttpClient);
    httpMock = TestBed.inject(HttpTestingController);


  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
