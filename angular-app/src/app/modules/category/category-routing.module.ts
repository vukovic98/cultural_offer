import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AddCategoryComponent} from '../../components/add-category/add-category.component';
import {RoleGuard} from '../../guards/role.guard';


const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: AddCategoryComponent,
    canActivate: [RoleGuard],
    data: {acceptRoles: 'ROLE_ADMIN'}
  },
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CategoryRoutingModule { }
