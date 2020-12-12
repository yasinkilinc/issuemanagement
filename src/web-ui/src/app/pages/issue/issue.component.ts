import {ChangeDetectorRef, Component, OnInit, TemplateRef} from '@angular/core';
import {IssueService} from "../../services/shared/issue.service";
import {Page} from "../../common/page";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProjectService} from "../../services/shared/project.service";
import {BsModalRef, BsModalService} from "ngx-bootstrap/modal";

@Component({
  selector: 'app-issue',
  templateUrl: './issue.component.html',
  styleUrls: ['./issue.component.scss']
})
export class IssueComponent implements OnInit {

  modalRef: BsModalRef;
  page = new Page();
  rows = [];
  projectOptions = [];
  issueForm: FormGroup;

  constructor(private issueService: IssueService,
              private projectService: ProjectService,
              private modalService: BsModalService,
              private formBuilder: FormBuilder,
              private cdr: ChangeDetectorRef) {
  }

  ngAfterViewChecked() {
    //your code to update the model
    this.cdr.detectChanges();
  }

  ngOnInit(): void {
    this.issueForm = this.formBuilder.group({
      projectId: [null, [Validators.required]],
      description: [null, [Validators.required]]
    })

    this.loadProjects();

    this.setPage({offset: 0, limit: 10})
  }

  private loadProjects() {
    this.projectService.getAll().subscribe(response => {
      this.projectOptions = response;
    });
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  setPage(pageInfo) {
    this.page.page = pageInfo.offset;
    this.page.size = pageInfo.limit;
    this.issueService.getAll(this.page).subscribe(pageData => {
        this.page.totalElements = pageData.totalElements;
        this.page.totalPages = pageData.totalPages;
        this.rows = pageData.content;
      }
    )
  }

  get f() {
    return this.issueForm.controls
  }

  closeAndResetModal() {
    this.issueForm.reset();
    this.modalRef.hide();
  }

  saveIssue() {
    this.issueService.createIssue(this.issueForm.value).subscribe(resp => {
      this.setPage({offset: 0, limit: 10});
      this.closeAndResetModal();
    });
  }

}
