import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {CulturalOfferRoutingModule} from './cultural-offer-routing.module';
import {OfferDetailsComponent} from '../../components/offer-details/offer-details.component';
import {AddOfferComponent} from '../../components/add-offer/add-offer.component';
import {EditOfferComponent} from '../../components/edit-offer/edit-offer.component';
import {SubscribedItemsComponent} from '../../components/subscribed-items/subscribed-items.component';
import {SubscribedItemsListComponent} from '../../components/subscribed-items-list/subscribed-items-list.component';
import {CulturalOffersComponent} from '../../components/cultural-offers/cultural-offers.component';
import {CulturalOfferItemComponent} from '../../components/cultural-offer-item/cultural-offer-item.component';
import {FilterOffersComponent} from '../../components/filter-offers/filter-offers.component';
import {SharedModule} from '../shared/shared.module';
import {MatMenuModule} from '@angular/material/menu';
import {MatIconModule} from '@angular/material/icon';
import {MatSelectModule} from '@angular/material/select';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatButtonModule} from '@angular/material/button';
import {MatDialogModule} from '@angular/material/dialog';
import {NgSelectModule} from '@ng-select/ng-select';
import {MatListModule} from '@angular/material/list';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatCardModule} from '@angular/material/card';
import {RouterModule} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CommentListComponent} from '../../components/comment-list/comment-list.component';
import {CommentItemComponent} from '../../components/comment-item/comment-item.component';
import {AddPostComponent} from '../../components/add-post/add-post.component';
import {AddCommentComponent} from '../../components/add-comment/add-comment.component';
import {PostComponent} from '../../components/post/post.component';
import {PostListComponent} from '../../components/post-list/post-list.component';
//import {PostItemComponent} from '../../components/post-item/post-item.component';
import {HomeComponent} from '../../components/home/home.component';
import { EditCategoryDialogComponent } from '../../components/edit-category-dialog/edit-category-dialog.component';
//import { EditTypeDialogComponent } from '../../components/edit-type-dialog/edit-type-dialog.component';
import { MapComponent } from 'src/app/components/map/map.component';
import {TextFieldModule} from '@angular/cdk/text-field';
import { LocationDialogComponent } from '../../components/location-dialog/location-dialog.component';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';

@NgModule({
  declarations: [
    OfferDetailsComponent,
    CulturalOffersComponent,
    CulturalOfferItemComponent,
    FilterOffersComponent,
    AddOfferComponent,
    EditOfferComponent,
    SubscribedItemsComponent,
    SubscribedItemsListComponent,
    CommentListComponent,
    CommentItemComponent,
    AddPostComponent,
    AddCommentComponent,
    PostComponent,
    PostListComponent,
    //PostItemComponent,
    HomeComponent,
    EditCategoryDialogComponent,
    //EditTypeDialogComponent,
    MapComponent,
    LocationDialogComponent
  ],
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        CulturalOfferRoutingModule,
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
        RouterModule, TextFieldModule, MatProgressSpinnerModule
    ]
})
export class CulturalOfferModule { }
