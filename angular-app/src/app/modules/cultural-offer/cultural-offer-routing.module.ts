import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {OfferDetailsComponent} from '../../components/offer-details/offer-details.component';
import {AddOfferComponent} from '../../components/add-offer/add-offer.component';
import {RoleGuard} from '../../guards/role.guard';
import {EditOfferComponent} from '../../components/edit-offer/edit-offer.component';
import {SubscribedItemsComponent} from '../../components/subscribed-items/subscribed-items.component';


const routes: Routes = [
  {path: 'offer-details/:id', component: OfferDetailsComponent},
  {
    path: 'add-offer',
    component: AddOfferComponent,
    canActivate: [RoleGuard],
    data: {acceptRoles: 'ROLE_ADMIN'}
  },
  {
    path: 'edit-offer',
    component: EditOfferComponent,
    canActivate: [RoleGuard],
    data: {acceptRoles: 'ROLE_ADMIN'}
  },
  {
    path: 'subscribed-items',
    component: SubscribedItemsComponent,
    canActivate: [RoleGuard],
    data: {acceptRoles: 'ROLE_USER'}
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CulturalOfferRoutingModule { }
