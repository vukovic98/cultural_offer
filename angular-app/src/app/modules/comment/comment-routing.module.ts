import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from '../../components/login/login.component';
import {SignUpComponent} from '../../components/sign-up/sign-up.component';
import {UserVerificationComponent} from '../../components/user-verification/user-verification.component';
import {CommentsToBeApprovedListComponent} from '../../components/comments-to-be-approved-list/comments-to-be-approved-list.component';
import {RoleGuard} from '../../guards/role.guard';


const routes: Routes = [
  {
    path: "approving-comments",
    component:CommentsToBeApprovedListComponent,
    canActivate:[RoleGuard],
    data: {acceptRoles: 'ROLE_ADMIN'}
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CommentRoutingModule { }
