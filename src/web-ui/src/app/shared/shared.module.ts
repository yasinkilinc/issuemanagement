import { NgModule } from '@angular/core';
import {TranslateModule} from "@ngx-translate/core";
import {BsModalRef, ModalModule} from "ngx-bootstrap/modal";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { ConfirmationComponent } from './confirmation/confirmation.component';
import {CommonModule} from "@angular/common";


@NgModule({
  imports:[
    CommonModule,
    FormsModule,
    ModalModule.forRoot()
  ],
  providers:[
    BsModalRef
  ],
 exports:[
   TranslateModule,
   ModalModule,
   FormsModule,
   ReactiveFormsModule,
   ConfirmationComponent
 ],
  entryComponents:[
    ConfirmationComponent
  ],
 declarations: [ConfirmationComponent]
})
export class SharedModule { }
