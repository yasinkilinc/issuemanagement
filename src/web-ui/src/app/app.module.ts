import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppLayoutComponent} from "./_layout/app-layout/app-layout.component";
import {FooterComponent, SidebarComponent} from "./_layout";
import {AppComponent} from "./app.component";
import {TranslateLoader, TranslateModule} from "@ngx-translate/core";
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from "@angular/common/http";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";
import {HeaderComponent} from "./_layout/header/header.component";
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {ApiService} from "./services/api.service";
import {NgxDatatableModule} from "@swimlane/ngx-datatable";
import {BsDatepickerModule, BsDropdownModule, CollapseModule, ModalModule, PaginationModule} from "ngx-bootstrap";
import {ProjectService} from "./services/shared/project.service";
import {IssueService} from "./services/shared/issue.service";
import {UserService} from "./services/shared/user.service";
import {IssueHistoryService} from "./services/shared/issue.history.service";
import {NotfoundComponent} from "./shared/notfound/notfound.component";
import {JwtInterceptor} from "./security/jwt.interceptor";
import {AuthenticationService} from "./security/authentication.service";
import {AuthGuard} from "./security/auth.guard";
import {ErrorInterceptor} from "./security/authentication.interceptor";
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ToastNoAnimation, ToastNoAnimationModule, ToastrModule} from "ngx-toastr";

export const createTranslateLoader = (http: HttpClient) => {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

@NgModule({
  declarations: [
    AppComponent,
    AppLayoutComponent,
    SidebarComponent,
    HeaderComponent,
    FooterComponent,
    NotfoundComponent,
    LoginComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    NgxDatatableModule,
    FontAwesomeModule,
    CollapseModule.forRoot(),
    BsDropdownModule.forRoot(),
    ModalModule.forRoot(),
    PaginationModule.forRoot(),
    BsDatepickerModule.forRoot(),
    ToastNoAnimationModule,
    ToastrModule.forRoot({
      toastComponent: ToastNoAnimation,
      maxOpened: 1,
      autoDismiss: true
    }),
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: createTranslateLoader,
        deps: [HttpClient]
      }
    })
  ],
  providers: [
    ApiService,
    ProjectService,
    IssueService,
    UserService,
    IssueHistoryService,
    AuthenticationService,
    AuthGuard,
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi:true},
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi:true},
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
