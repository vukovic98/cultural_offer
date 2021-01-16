import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {map} from "rxjs/operators";
import Swal from "sweetalert2";
import {Router} from "@angular/router";
import {Observable} from "rxjs";

@Injectable()
export class CommentsToBeApprovedService{
  private readonly commentsToBeApprovedEndPoint = "comments/pendingComments/";
  private readonly approveCommentEndPoint = "comments/approve/";
  private readonly denyCommentEndPoint = "comments/";

  constructor(private http: HttpClient) {}

  getCommentsByPage(page: number):any{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      "Authorization" :  "Bearer " + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.apiUrl + this.commentsToBeApprovedEndPoint + page, {headers: headers})
     .pipe(map((response) => JSON.stringify(response)));
  }

  approveComment(commentId: number): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      "Authorization" :  "Bearer " + localStorage.getItem("accessToken")
    });

    return this.http.put(environment.apiUrl + this.approveCommentEndPoint + commentId,"",{headers: headers});
  }

  denyComment(commentId: number): Observable<any> {
    const headers = new HttpHeaders({
      "Authorization" :  "Bearer " + localStorage.getItem("accessToken")
    });

    return this.http.delete(environment.apiUrl + this.denyCommentEndPoint + commentId,{headers: headers});

  }
}
