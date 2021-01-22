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
        .and.returnValue(of({}))

    };

    let swalMock = {
      fire: jasmine.createSpy('fire').
      and.returnValue(of({}))
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
        ReactiveFormsModule
      ]

    }).compileComponents();
    TestBed.configureTestingModule({
      declarations: [ChangePasswordComponent],
      providers:[
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
   loader = TestbedHarnessEnvironment.loader(fixture);
  });

  it('should cancel ', () => {
    let cancelBtn: HTMLButtonElement = fixture.debugElement.query(By.css("#cancelBtn")).nativeElement;
    expect(cancelBtn.disabled).toBeFalse();
    cancelBtn.click();
    fixture.detectChanges();
    expect(router.navigate).toHaveBeenCalledWith(['/home-page']);




  });

  it('should successfully change password', fakeAsync(() => {
    expect(component.changePasswordForm.invalid).toBeTruthy();

    expect(component.changePasswordForm.value.oldPassword).not.toEqual('stara123');
    expect(component.changePasswordForm.value.newPassword).not.toEqual('nova1234');
    expect(component.changePasswordForm.value.confirmPassword).not.toEqual('nova1234');

    // let oldPassInput = fixture.debugElement.query(By.css('#oldPassword')).nativeElement;
    // oldPassInput.value = 'stara123';
    // let newPassInput = fixture.debugElement.query(By.css('#newPassword')).nativeElement;
    // newPassInput.value = 'nova123';
    // let confirmedPassInput = fixture.debugElement.query(By.css('#confirmPassword')).nativeElement;
    // confirmedPassInput.value = 'nova123';

    (<ControlContainer> <unknown> component.changePasswordForm.controls['oldPassword']).reset('stara123');
    (<ControlContainer> <unknown> component.changePasswordForm.controls['newPassword']).reset('nova1234');
    (<ControlContainer> <unknown> component.changePasswordForm.controls['confirmPassword']).reset('nova1234');

    const passDto = {
      oldPassword: 'stara123',
      newPassword: 'nova1234'
    }

    fixture.detectChanges();
    component.changePassword();
   // expect(router.navigate).toHaveBeenCalledWith(['/home-page']);
    //spyOn(Swal,"fire").and.callFake(Swal.fire);

//    expect(swal.fire).toHaveBeenCalledTimes(1);
    expect(changePassService.changePassword).toHaveBeenCalledWith(JSON.stringify(passDto));
    tick();
    expect(component.changePasswordForm.value.oldPassword).toEqual('stara123');
    expect(component.changePasswordForm.value.newPassword).toEqual('nova1234');
    expect(component.changePasswordForm.value.confirmPassword).toEqual('nova1234');

    flush();
}));
});
