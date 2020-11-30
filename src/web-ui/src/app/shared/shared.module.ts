import { NgModule } from '@angular/core';
import {TranslateModule} from "@ngx-translate/core";
import {ModalModule} from "ngx-bootstrap/modal";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";


@NgModule({
 exports:[
   TranslateModule,
   ModalModule,
   FormsModule,
   ReactiveFormsModule,
 ]
})
export class SharedModule { }
