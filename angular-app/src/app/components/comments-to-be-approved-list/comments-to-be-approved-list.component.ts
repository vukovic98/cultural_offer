import { Component, OnInit } from '@angular/core';
import {CommentsToBeApprovedService} from "../../services/comments-to-be-approved.service";
import {CommentToBeApprovedModel} from "../../model/comment-to-be-approved-model";
import Swal from "sweetalert2";

@Component({
  selector: 'app-comments-to-be-approved-list',
  templateUrl: './comments-to-be-approved-list.component.html',
  styleUrls: ['./comments-to-be-approved-list.component.css']
})
export class CommentsToBeApprovedListComponent implements OnInit {

  commentsToBeApproved: Array<CommentToBeApprovedModel> = [];
  public pageNum: number = 1;
  public nextBtn: boolean = false;


  constructor(private service: CommentsToBeApprovedService) { }

  getAllComments(){
    return this.commentsToBeApproved || [];
  }

  approveComment(commentId: number){

      this.service.approveComment(commentId)
        .subscribe(response => {
          Swal.fire({
            text: 'Comment approved!',
            icon: "success",
            showConfirmButton: false,
            timer: 1200
          });
        });

      this.commentsToBeApproved = this.commentsToBeApproved.filter(item => item.id !== commentId);
  }

  denyComment(commentId: number){
    this.service.denyComment(commentId)
      .subscribe(response => {
        Swal.fire({
          text: 'Comment successfully denied!',
          icon: "success",
          showConfirmButton: false,
          timer: 1200
        });
      });
    this.commentsToBeApproved = this.commentsToBeApproved.filter(item => item.id !== commentId);

  }

  nextPage() {
    this.pageNum += 1;
    this.service.getCommentsByPage(this.pageNum).subscribe((data) => {
      this.commentsToBeApproved = data.content;
      this.nextBtn = data.last;
    });
  }

  previousPage() {
    this.pageNum -= 1;
    this.service.getCommentsByPage(this.pageNum).subscribe((data) => {
      this.commentsToBeApproved = data.content;
      this.nextBtn = data.last;
    });
  }
  getCommentsByPage() {
    this.service.getCommentsByPage(this.pageNum).subscribe((data) => {
      this.commentsToBeApproved = data.content;
      this.nextBtn = data.last;
    });
  }
  ngOnInit(): void {
    this.getCommentsByPage();
  }

}
