import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavigationBarComponent } from './navigation-bar.component';
import {AuthService} from '../../services/auth.service';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatMenuModule} from '@angular/material/menu';

describe('NavigationBarComponent', () => {
  let component: NavigationBarComponent;
  let fixture: ComponentFixture<NavigationBarComponent>;
  let authService: AuthService;

  let authServiceMock = {
    isUser: jasmine.createSpy('isUser')
      .and.returnValue(true),
    isAdmin: jasmine.createSpy('isAdmin')
      .and.returnValue(true),
    logout: jasmine.createSpy('logout')
  };

  beforeEach( () => {
    TestBed.configureTestingModule({
      imports: [
        MatMenuModule,
        BrowserAnimationsModule
      ],
      declarations: [ NavigationBarComponent ],
      providers: [
        { provide: AuthService, useValue: authServiceMock }
      ]
    }).compileComponents();

  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NavigationBarComponent);
    component = fixture.componentInstance;
    authService = TestBed.inject(AuthService);

    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should determine if user is registered user', () => {
    let is = component.isUser();

    expect(authService.isUser).toHaveBeenCalled();
    expect(is).toBeTrue();
  });

  it('should determine if user is admin', () => {
    let is = component.isUserAdmin();

    expect(authService.isAdmin).toHaveBeenCalled();
    expect(is).toBeTrue();
  });

  it('should logout user', () => {
    component.logout();

    expect(authService.logout).toHaveBeenCalled();
  });

});
