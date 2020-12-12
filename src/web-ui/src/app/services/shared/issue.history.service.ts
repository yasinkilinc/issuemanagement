import {Injectable} from "@angular/core";
import {ApiService} from "../api.service";
import {Observable} from "rxjs/Observable";
import {map} from "rxjs/operators";

@Injectable()
export class IssueHistoryService {

  private USER_PATH = "/issue/history";

  constructor(private apiService: ApiService) {
  }

  getAll(): Observable<any> {
    return this.apiService.get(this.USER_PATH).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          console.log(res);
          return {};
        }
      }
    ));
  }

  getById(id): Observable<any> {
    return this.apiService.get(this.USER_PATH, id).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          console.log(res);
          return {};
        }
      }
    ));
  }

  createUserHistory(user): Observable<any> {
    return this.apiService.post(this.USER_PATH, user).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          console.log(res);
          return {};
        }
      }
    ));
  }
}
