import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {ProjectService} from "../../services/shared/project.service";
import {Page} from "../../common/page";
import {BsModalRef, BsModalService} from "ngx-bootstrap/modal";

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.scss']
})
export class ProjectComponent implements OnInit {

  page = new Page();

  cols = [];
  rows = [];
  modalRef: BsModalRef;
//  @ViewChild('tplProjectDeleteCell') tplProjectDeleteCell: TemplateRef<any>;

  constructor(private projectService: ProjectService ,private modalService: BsModalService) {

  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }


  ngOnInit(): void {
    this.cols = [
      {prop: 'id', name: 'No'},
      {prop: 'projectName', name: 'Project Name', sortable: false},
      {prop: 'projectCode', name: 'Project Code', sortable: false},
      {prop: 'manager.nameSurname', name: 'Manager', sortable: false},
//      {prop: 'id', name: 'Actions', cellTemplate: this.tplProjectDeleteCell, flexGrow: 1, sortable: false}
    ];

    this.setPage({offset: 0, limit:10})
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
}
