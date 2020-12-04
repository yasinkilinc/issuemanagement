import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {DashboardModule} from './pages/dashboard/dashboard.module';
import {ProjectModule} from './pages/project/project.module';
import {IssueModule} from './pages/issue/issue.module';
import {AppLayoutComponent} from './_layout/app-layout/app-layout.component';
import {NotfoundComponent} from "./shared/notfound/notfound.component";

const routes: Routes = [
  {
    path:'',component: AppLayoutComponent,
    children: [
      { path: '', pathMatch: 'full', redirectTo: 'dashboard'},
      { path: 'dashboard', loadChildren: ()=> DashboardModule },
      { path: 'project', loadChildren: () => ProjectModule},
      { path: 'issue', loadChildren: () => IssueModule}
    ]
  },
  {
    path:'**',component: NotfoundComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
