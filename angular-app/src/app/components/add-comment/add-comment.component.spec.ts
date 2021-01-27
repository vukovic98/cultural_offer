import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Router, RouterModule } from '@angular/router';
import { AddCommentComponent } from './add-comment.component';
import { of } from 'rxjs';
import { CulturalOfferService } from 'src/app/services/culturalOffer.service';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthService } from 'src/app/services/auth.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
describe('AddCommentComponent', () => {
  let component: AddCommentComponent;
  let fixture: ComponentFixture<AddCommentComponent>;

  beforeEach( () => {

    const culturalOfferServiceStub = () => ({
      addComment: jasmine.createSpy('addComment')
      .and.returnValue(of({

        body: {
          "id": 20,
          "commenterEmail": "test@maildrop.com",
          "commenterName": "Marko",
          "content": "Content test comment",
          "offer": "1"
        }

      }))
    });

    TestBed.configureTestingModule({
      declarations: [AddCommentComponent],
      providers: [
        { provide: CulturalOfferService, useFactory: culturalOfferServiceStub }, AuthService
      ],
      imports: [RouterTestingModule, HttpClientTestingModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddCommentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('submit', () => {
    it('makes expected calls', () => {
      const culturalOfferServiceStub: CulturalOfferService = fixture.debugElement.injector.get(
        CulturalOfferService
      );
      component.submit();
      expect(culturalOfferServiceStub.addComment).toHaveBeenCalled();
    });
  });


});
