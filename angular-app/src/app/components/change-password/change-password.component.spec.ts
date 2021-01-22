import {ComponentFixture, getTestBed, TestBed} from '@angular/core/testing';
import { ChangePasswordComponent } from './change-password.component';
import {FormBuilder} from "@angular/forms";
import {HttpClient, HttpHandler} from "@angular/common/http";
import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";
import {ChangePasswordService} from "../../services/change-password.service";
import {RouterTestingModule} from "@angular/router/testing";

describe('ChangePasswordComponent', () => {
  let component: ChangePasswordComponent;
  let fixture: ComponentFixture<ChangePasswordComponent>;
  let injector;
  let httpMock: HttpTestingController;
  let httpClient: HttpClient;
  let service: ChangePasswordService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule],
      declarations: [ ChangePasswordComponent ],
      providers: [FormBuilder, ChangePasswordService, HttpClient, HttpHandler, HttpTestingController]
    })
    .compileComponents();
    injector = getTestBed();
    service = TestBed.inject(ChangePasswordService);
    httpClient = TestBed.inject(HttpClient);
    httpMock = TestBed.inject(HttpTestingController);
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangePasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
