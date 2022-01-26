import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PlaceComponent } from './component/place.component';

const routes: Routes = [
  { path:'', component: PlaceComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
