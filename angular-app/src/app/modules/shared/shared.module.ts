import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {NavigationBarComponent} from '../../components/navigation-bar/navigation-bar.component';
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



@NgModule({
  declarations: [
    NavigationBarComponent,
  ],
  imports: [
    CommonModule,
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
  ],
  exports: [
    NavigationBarComponent
  ]
})
export class SharedModule { }
