import {ComponentFixture, fakeAsync, TestBed} from '@angular/core/testing';

import { CommentToBeApprovedComponent } from './comment-to-be-approved.component';
import {NO_ERRORS_SCHEMA} from "@angular/core";

describe('CommentToBeApprovedComponent', () => {
  let component: CommentToBeApprovedComponent;
  let fixture: ComponentFixture<CommentToBeApprovedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      declarations: [ CommentToBeApprovedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CommentToBeApprovedComponent);
    component = fixture.componentInstance;
    spyOn(component.denyComment,'emit');
    spyOn(component.approveComment,'emit');
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should emit rejecting comment to parent component', fakeAsync(() => {
    component.rejectComment(1);
    expect(component.denyComment.emit).toHaveBeenCalled();
  }));

  it('should emit approving comment to parent component', fakeAsync(() => {
    component.authoriseComment(1);
    expect(component.approveComment.emit).toHaveBeenCalled();
  }));
});
