import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './components/home/home.component';

const routes: Routes = [

  {path: '', redirectTo: 'home-page', pathMatch: 'full'},
  {path: 'home-page', component: HomeComponent},

  {
    path: 'auth',
    loadChildren: () => import('./modules/auth/auth.module').then(m => m.AuthModule)
  },

  {
    path: 'user',
    loadChildren: () => import('./modules/user/user.module').then(u => u.UserModule)
  },

  {
    path: 'comment',
    loadChildren: () => import('./modules/comment/comment.module').then(c => c.CommentModule)
  },

  {
    path: 'cultural-offer',
    loadChildren: () => import('./modules/cultural-offer/cultural-offer.module').then(c => c.CulturalOfferModule)
  },

  {
    path: 'type',
    loadChildren: () => import('./modules/type/type.module').then(t => t.TypeModule)
  },

  {
    path: 'category',
    loadChildren: () => import('./modules/category/category.module').then(c => c.CategoryModule)
  }
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
