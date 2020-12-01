import {Component, OnInit, TemplateRef, ViewChild, AfterViewInit} from '@angular/core';
import {ProjectService} from "../../services/shared/project.service";
import {Page} from "../../common/page";
import {BsModalRef, BsModalService} from "ngx-bootstrap/modal";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ConfirmationComponent} from "../../shared/confirmation/confirmation.component";
import { faTrash } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.scss']
})
export class ProjectComponent implements OnInit {

  modalRef: BsModalRef;
  projectForm: FormGroup;

  page = new Page();
  cols = [];
  rows = [];
  delete = faTrash;

  @ViewChild('tplProjectDeleteCell') tplProjectDeleteCell: TemplateRef<any>;

  constructor(private projectService: ProjectService,
              private modalService: BsModalService,
              private formBuilder: FormBuilder) {

  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  saveProject() {
    if (!this.projectForm.valid)
      return;

    this.projectService.createProject(this.projectForm.value).subscribe(
      response => {
        this.setPage({offset: 0, limit: 10});
        this.closeAndResetModal();
      }
    )
  }

  ngAfterViewInit() {
    this.cols = [
      {prop: 'id', name: 'No'},
      {prop: 'projectName', name: 'Project Name', sortable: false},
      {prop: 'projectCode', name: 'Project Code', sortable: false},
      //{prop: 'manager.nameSurname', name: 'Manager', sortable: false},
      {prop: 'id', name: 'Actions', cellTemplate: this.tplProjectDeleteCell, flexGrow: 1, sortable: false}
    ];
  }

  ngOnInit(): void {
    this.setPage({offset: 0, limit: 10})

    this.projectForm = this.formBuilder.group({
      'projectCode': [null, [Validators.required, Validators.minLength(2), Validators.maxLength(10)]],
      'projectName': [null, [Validators.required, Validators.minLength(4)]]
//      'managerId': [null, [Validators.required]]
    });

  }

  get f() {
    return this.projectForm.controls
  }

  closeAndResetModal() {
    this.projectForm.reset();
    this.modalRef.hide();
  }

  setPage(pageInfo) {
    this.page.page = pageInfo.offset;
    this.page.size = pageInfo.limit;
    this.projectService.getall(this.page).subscribe(pageData => {
        this.page.totalElements = pageData.totalElements;
        this.page.totalPages = pageData.totalPages;
        this.rows = pageData.content;
      }
    )
  }

  showProjectDeleteConfirmation(value){
    const modal = this.modalService.show( ConfirmationComponent );
    (<ConfirmationComponent>modal.content).showConfirmation(
      'Delete Confirmation',
      'Are you sure for delete project?'
    );

    (<ConfirmationComponent>modal.content).onClose.subscribe(result => {
      if(result ===true){
        this.projectService.delete(value).subscribe( response => {
          if(response === true){
            this.setPage({offset: 0, limit: 10})
          }
        });
      }else if(result ===false){
      }
    });
  }

}
