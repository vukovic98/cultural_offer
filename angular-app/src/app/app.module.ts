import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import {FormsModule} from '@angular/forms';
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
import {MatDialogModule, MatDialogRef} from '@angular/material/dialog';

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
    EditOfferComponent
  ],
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
    MatDialogModule
  ],
  providers: [
    AuthService,
    CulturalOfferService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
