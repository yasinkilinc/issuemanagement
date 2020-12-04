import { NgModule } from '@angular/core';
import {TranslateModule} from "@ngx-translate/core";
import {BsModalRef, ModalModule} from "ngx-bootstrap/modal";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { ConfirmationComponent } from './confirmation/confirmation.component';
import {CommonModule} from "@angular/common";
import { NotfoundComponent } from './notfound/notfound.component';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ModalModule.forRoot()
  ],
  providers: [BsModalRef],
  declarations: [
    ConfirmationComponent,
    NotfoundComponent
  ],
  entryComponents: [
    ConfirmationComponent
  ],
  exports: [
    TranslateModule,
    ModalModule,
    ReactiveFormsModule,
    ConfirmationComponent,
    FormsModule
  ]
})
export class SharedModule {
}
