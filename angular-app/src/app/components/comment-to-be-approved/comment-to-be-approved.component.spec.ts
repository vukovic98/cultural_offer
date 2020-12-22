import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentToBeApprovedComponent } from './comment-to-be-approved.component';

describe('CommentToBeApprovedComponent', () => {
  let component: CommentToBeApprovedComponent;
  let fixture: ComponentFixture<CommentToBeApprovedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CommentToBeApprovedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CommentToBeApprovedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
