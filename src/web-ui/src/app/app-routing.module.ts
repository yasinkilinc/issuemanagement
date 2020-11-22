import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AppComponent} from './app.component';
import {DashboardModule} from './pages/dashboard/dashboard.module';
import {ProjectModule} from './pages/project/project.module';
import {IssueModule} from './pages/issue/issue.module';

const routes: Routes = [
  {
    path:'',
    children: [
      { path: '', pathMatch: 'full', redirectTo: 'dashboard'},
      { path: 'dashboard', loadChildren: ()=> DashboardModule },
      { path: 'project', loadChildren: () => ProjectModule},
      { path: 'issue', loadChildren: () => IssueModule}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
