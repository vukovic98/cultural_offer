import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentsToBeApprovedListComponent } from './comments-to-be-approved-list.component';

describe('CommentsToBeApprovedListComponent', () => {
  let component: CommentsToBeApprovedListComponent;
  let fixture: ComponentFixture<CommentsToBeApprovedListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CommentsToBeApprovedListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CommentsToBeApprovedListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
