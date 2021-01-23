import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AddCategoryComponent} from '../../components/add-category/add-category.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {SharedModule} from '../shared/shared.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatIconModule} from '@angular/material/icon';
import {MatSelectModule} from '@angular/material/select';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatButtonModule} from '@angular/material/button';
import {MatDialogModule} from '@angular/material/dialog';
import {NgSelectModule} from '@ng-select/ng-select';
import {MatMenuModule} from '@angular/material/menu';
import {MatListModule} from '@angular/material/list';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatCardModule} from '@angular/material/card';
import {CategoryRoutingModule} from './category-routing.module';
import {RouterModule} from '@angular/router';
//import {EditCategoryDialogComponent} from '../../components/edit-category-dialog/edit-category-dialog.component';

@NgModule({
  declarations: [
    AddCategoryComponent,
    //EditCategoryDialogComponent
  ],
  imports: [
    CommonModule,
    CategoryRoutingModule,
    FormsModule,
    ReactiveFormsModule,
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
export class CategoryModule { }
