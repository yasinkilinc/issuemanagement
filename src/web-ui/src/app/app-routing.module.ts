import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DashboardModule} from './pages/dashboard/dashboard.module';
import {ProjectModule} from './pages/project/project.module';
import {IssueModule} from './pages/issue/issue.module';
import {AppLayoutComponent} from './_layout/app-layout/app-layout.component';
import {NotfoundComponent} from "./shared/notfound/notfound.component";
import {AuthGuard} from "./security/auth.guard";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";

const routes: Routes = [
  {
    path:'',component: AppLayoutComponent, canActivate: [AuthGuard],
    children: [
      { path: '', pathMatch: 'full', redirectTo: 'dashboard'},
      { path: 'dashboard', loadChildren: ()=> DashboardModule },
      { path: 'project', loadChildren: () => ProjectModule},
      { path: 'issue', loadChildren: () => IssueModule}
    ]
  },
  { path:'login',component: LoginComponent},
  { path: 'register', component: RegisterComponent},
  { path:'**',component: NotfoundComponent,}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
