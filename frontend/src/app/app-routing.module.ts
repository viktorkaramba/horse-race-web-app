import { NgModule } from "@angular/core";
import { Routes, RouterModule} from "@angular/router";
import {AuthGuard} from '../app/utility/app.guard';

const routes: Routes = [
  { path: '', redirectTo: 'profile', pathMatch: 'full' },
  { path: 'profile', loadChildren: () => import('./views/profile/profile.module').then(m => m.ProfileModule),
    canActivate: [AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
