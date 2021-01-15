import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {CommentRoutingModule} from './comment-routing.module';
import {SharedModule} from '../shared/shared.module';
import {CommentsToBeApprovedListComponent} from '../../components/comments-to-be-approved-list/comments-to-be-approved-list.component';
import {CommentToBeApprovedComponent} from '../../components/comment-to-be-approved/comment-to-be-approved.component';
import {MatIconModule} from '@angular/material/icon';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatMenuModule} from '@angular/material/menu';
import {MatSelectModule} from '@angular/material/select';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatButtonModule} from '@angular/material/button';
import {MatDialogModule} from '@angular/material/dialog';
import {NgSelectModule} from '@ng-select/ng-select';
import {MatListModule} from '@angular/material/list';
import {MatCardModule} from '@angular/material/card';
import {RouterModule} from '@angular/router';


@NgModule({
  declarations: [
    CommentsToBeApprovedListComponent,
    CommentToBeApprovedComponent
  ],
  imports: [
    CommonModule,
    CommentRoutingModule,
    SharedModule,
    MatMenuModule,
    MatIconModule,
    MatSelectModule, MatGridListModule,
    MatSlideToggleModule,
    MatButtonModule,
    MatDialogModule,
    NgSelectModule,
    MatMenuModule,
    MatListModule,
    MatExpansionModule,
    MatCardModule,
    RouterModule
  ]
})
export class CommentModule { }
