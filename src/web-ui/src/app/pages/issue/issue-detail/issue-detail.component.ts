import {ChangeDetectorRef, Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import {IssueService} from "../../../services/shared/issue.service";
import {ProjectService} from "../../../services/shared/project.service";
import {UserService} from "../../../services/shared/user.service";

@Component({
  selector: 'app-issue-detail',
  templateUrl: './issue-detail.component.html',
  styleUrls: ['./issue-detail.component.scss']
})
export class IssueDetailComponent implements OnInit {

  @ViewChild('tplDateCell') tplDateCell: TemplateRef<any>;

  issueDetailForm: FormGroup;

  id: number;
  private sub: any;

  datatable_rows = [];
  columns = [];

  projectOptions = [];
  issueStatusOptions = [];
  assigneeOptions = [];


  constructor(private cdr: ChangeDetectorRef,
              private route: ActivatedRoute,
              private projectService: ProjectService,
              private userService: UserService,
              private issueService: IssueService,
              private formBuilder: FormBuilder) {
  }

  ngAfterViewChecked() {
    //your code to update the model
    this.cdr.detectChanges();
  }

  ngOnInit(): void {
    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id'];
      this.loadIssueDetails();
    });

    this.loadProjects();
    this.loadAssignee();
    this.loadIssueStatuses();
  }

  ngAfterViewInit() {
    this.columns = [
      {prop: 'id', name: 'No', maxWidth: 30},
      {prop: 'description', name: 'Description', sortable: false},
      {prop: 'date', name: 'Issue Date', cellTemplate: this.tplDateCell, sortable: false},
      {prop: 'issueStatus', name: 'Issue Status', sortable: false},
      {prop: 'assignee.nameSurname', name: 'Assignee', sortable: false},
      {prop: 'issue.project.projectName', name: 'Project Name', sortable: false},
      // {prop: 'id', name: 'Actions', cellTemplate: this.tplProjectDeleteCell, flexGrow: 1, sortable: false}
    ];
  }

  private loadIssueStatuses() {
    this.issueService.getAllIssueStatuses().subscribe(response => {
      this.issueStatusOptions = response;
    })
  }

  private loadAssignee() {
    this.userService.getAll().subscribe(response => {
      this.assigneeOptions = response;
    });
  }

  private loadProjects() {
    this.projectService.getAll().subscribe(response => {
      this.projectOptions = response;
    });
  }

  private loadIssueDetails() {
    this.issueService.getByIdWithDetails(this.id).subscribe(response => {
      this.issueDetailForm = this.createIssueDetailFormGroup(response);
      this.datatable_rows = response['issueHistories'];
    });
  }

  saveIssue() {
    this.issueService.updateIssue(this.issueDetailForm.value).subscribe(response => {
      this.issueDetailForm = this.createIssueDetailFormGroup(response);
      this.datatable_rows = response['issueHistories'];
    });
  }

  createIssueDetailFormGroup(response) {
    return this.formBuilder.group({
      id: response['id'],
      description: response['description'],
      details: response['details'],
      date: this.fromJsonDate(response['date']),
      issueStatus: response['issueStatus'],
      assignee_id: response['assignee'] ? response['assignee']['id'] : '',
      project_id: response['project'] ? response['project']['id'] : '',
      project_manager: response['project'] && response['project']['manager'] ? response['project']['manager']['nameSurname'] : '',
    });
  }

  fromJsonDate(jDate): string {
    const bDate: Date = new Date(jDate);
    return bDate.toISOString().substring(0, 10);
  }

}
