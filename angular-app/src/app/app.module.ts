import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';

import {FormsModule, NgSelectOption} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {AuthService} from './services/auth.service';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatButtonModule} from '@angular/material/button';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { NavigationBarComponent } from './components/navigation-bar/navigation-bar.component';
import { AddOfferComponent } from './components/add-offer/add-offer.component';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule } from '@angular/material/icon';
import { MatSelectModule } from '@angular/material/select';
import { CulturalOffersComponent } from './components/cultural-offers/cultural-offers.component';
import { CulturalOfferItemComponent } from './components/cultural-offer-item/cultural-offer-item.component';
import { CulturalOfferService } from './services/culturalOffer.service';
import { UserVerificationComponent } from './components/user-verification/user-verification.component';
import { SubscribedItemsComponent } from './components/subscribed-items/subscribed-items.component';
import { SubscribedOfferCardComponent } from './components/subscribed-offer-card/subscribed-offer-card.component';
import { SubscribedItemsListComponent } from './components/subscribed-items-list/subscribed-items-list.component';
import { ProfileComponent } from './components/profile/profile.component';
import { EditOfferComponent } from './components/edit-offer/edit-offer.component';
import { OfferDetailsComponent } from './components/offer-details/offer-details.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatMenuModule } from '@angular/material/menu';
import {ChangePasswordComponent} from "./components/change-password/change-password.component";
import {EditProfileService} from "./services/edit-profile.service";
import {ChangePasswordService} from "./services/change-password.service";
import { FilterOffersComponent } from './components/filter-offers/filter-offers.component';
import {FilterOffersService} from "./services/filter-offers.service";
import {CommonModule} from "@angular/common";
import {NgSelectModule} from "@ng-select/ng-select";
import { CommentToBeApprovedComponent } from './components/comment-to-be-approved/comment-to-be-approved.component';
import { CommentsToBeApprovedListComponent } from './components/comments-to-be-approved-list/comments-to-be-approved-list.component';
import {CommentsToBeApprovedService} from "./services/comments-to-be-approved.service";
import {MatListModule} from "@angular/material/list";
import {MatExpansionModule} from "@angular/material/expansion";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    SignUpComponent,
    NavigationBarComponent,
    AddOfferComponent,
    CulturalOffersComponent,
    CulturalOfferItemComponent,
    UserVerificationComponent,
    SubscribedItemsComponent,
    SubscribedOfferCardComponent,
    SubscribedItemsListComponent,
    ProfileComponent,
	FilterOffersComponent,
OfferDetailsComponent,
    EditOfferComponent,
  ChangePasswordComponent,
  CommentToBeApprovedComponent,
  CommentsToBeApprovedListComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatIconModule,
    MatSelectModule,
    MatSlideToggleModule,
    MatButtonModule,
    MatDialogModule,
    CommonModule,
    NgSelectModule,
    MatMenuModule,
    MatListModule,
    MatExpansionModule
  ],
  providers: [
    AuthService,
    CulturalOfferService,
    EditProfileService,
    ChangePasswordService,
    FilterOffersService,
    CommentsToBeApprovedService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
