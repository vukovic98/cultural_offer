import {ComponentFixture, fakeAsync, flush, getTestBed, TestBed, tick} from '@angular/core/testing';
import { ChangePasswordComponent } from './change-password.component';
import {ControlContainer, FormBuilder, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {HttpClient, HttpHandler} from "@angular/common/http";
import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";
import {ChangePasswordService} from "../../services/change-password.service";
import {RouterTestingModule} from "@angular/router/testing";
import {Router} from "@angular/router";
import {of} from "rxjs";
import { By } from '@angular/platform-browser';
import Swal from "sweetalert2";
import {TestbedHarnessEnvironment} from "@angular/cdk/testing/testbed";
import {HarnessLoader} from "@angular/cdk/testing";
import {passwordDto} from "../../model/password-model";
import {MatInputHarness} from "@angular/material/input/testing";
import {NavigationBarComponent} from "../navigation-bar/navigation-bar.component";
import {AuthService} from "../../services/auth.service";
import {NO_ERRORS_SCHEMA} from "@angular/core";

describe('ChangePasswordComponent', () => {
  let component: ChangePasswordComponent;
  let fixture: ComponentFixture<ChangePasswordComponent>;
  let changePassService: any;
  let router: any;
  let swal: any;
  let loader: HarnessLoader;

  beforeEach(() => {
    let changePassServiceMock = {
      changePassword: jasmine.createSpy('changePassword')
        .and.returnValue(of(
          {
            'firstName': 'Test',
            'lastName': 'Test',
            'email': 'test@maildrop.cc',
            'id': 0,
            'password': 'nova1234',
          }
        ))

    };

    let swalMock = {
      fire: jasmine.createSpy('fire').
      and.returnValue(of({
        position: 'center',
        title: 'Password successfully changed',
        icon: 'success',
        showConfirmButton: false,
        timer: 1500
      }))
    };

    let routerMock = {
      navigate: jasmine.createSpy('navigate')
    };

    TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      declarations: [ChangePasswordComponent],
      providers:[
        FormBuilder,
        FormsModule,
        ReactiveFormsModule,
        {provide: ChangePasswordService, useValue: changePassServiceMock },
        { provide: Router, useValue: routerMock },
        { provide: Swal, useValue: swalMock}
      ]

    }).compileComponents();

    fixture = TestBed.createComponent(ChangePasswordComponent);
    component = fixture.componentInstance;
    changePassService = TestBed.inject(ChangePasswordService);
    router = TestBed.inject(Router);
    // @ts-ignore
    swal = TestBed.inject(Swal);
    fixture.detectChanges();

  });

  it('should cancel ', () => {
    fixture.debugElement.query(By.css("#cancelBtn")).nativeElement.click;
    fixture.detectChanges();
    component.cancel();

    expect(router.navigate).toHaveBeenCalledWith(['/cultural-offer/home-page']);

  });

  it('should successfully change password', fakeAsync(() => {
    expect(component.changePasswordForm.invalid).toBeTruthy();

    expect(component.changePasswordForm.value.oldPassword).not.toEqual('stara123');
    expect(component.changePasswordForm.value.newPassword).not.toEqual('nova1234');
    expect(component.changePasswordForm.value.confirmPassword).not.toEqual('nova1234');

    (<ControlContainer> <unknown> component.changePasswordForm.controls['oldPassword']).reset('stara123');
    (<ControlContainer> <unknown> component.changePasswordForm.controls['newPassword']).reset('nova1234');
    (<ControlContainer> <unknown> component.changePasswordForm.controls['confirmPassword']).reset('nova1234');

    const passDto = {
      oldPassword: 'stara123',
      newPassword: 'nova1234'
    }

    component.changePassword();
    swal.fire({position: 'center',
      title: 'Password successfully changed',
      icon: 'success',
      showConfirmButton: false,
      timer: 1500});

    expect(swal.fire).toHaveBeenCalledWith({
      position: 'center',
      title: 'Password successfully changed',
      icon: 'success',
      showConfirmButton: false,
      timer: 1500
    });
    tick();
    //expect(router.navigate).toHaveBeenCalledWith(['/cultural-offer/home-page']);



    expect(changePassService.changePassword).toHaveBeenCalledWith(JSON.stringify(passDto));
    tick();
    expect(component.changePasswordForm.value.oldPassword).toEqual('stara123');
    expect(component.changePasswordForm.value.newPassword).toEqual('nova1234');
    expect(component.changePasswordForm.value.confirmPassword).toEqual('nova1234');

    flush();
}));
});
