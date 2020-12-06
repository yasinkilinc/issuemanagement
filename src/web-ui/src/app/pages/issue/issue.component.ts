import {Component, OnInit} from '@angular/core';
import {IssueService} from "../../services/shared/issue.service";
import {Page} from "../../common/page";

@Component({
  selector: 'app-issue',
  templateUrl: './issue.component.html',
  styleUrls: ['./issue.component.scss']
})
export class IssueComponent implements OnInit {

  page = new Page();
  rows = [];

  constructor(private issueService: IssueService) { }

  ngOnInit(): void {
    this.setPage({offset: 0, limit: 10})
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

}
