import { Component, OnInit } from '@angular/core';
import {CommentsToBeApprovedService} from "../../services/comments-to-be-approved.service";
import {CommentToBeApprovedModel} from "../../model/comment-to-be-approved-model";

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
    console.log(this.commentsToBeApproved);
    return this.commentsToBeApproved || [];
  }

  approveComment(commentId: number){
      this.service.approveComment(commentId);
      this.commentsToBeApproved = this.commentsToBeApproved.filter(item => item.id != commentId);

  }

  denyComment(commentId: number){
    this.service.denyComment(commentId);
    this.commentsToBeApproved = this.commentsToBeApproved.filter(item => item.id != commentId);

  }
  nextPage() {
    this.pageNum += 1;
    this.service.getCommentsByPage(this.pageNum).subscribe((data: string) => {
      this.commentsToBeApproved = JSON.parse(data).content;
      this.nextBtn = JSON.parse(data).last;
    });
  }

  previousPage() {
    this.pageNum -= 1;
    this.service.getCommentsByPage(this.pageNum).subscribe((data: string) => {
      this.commentsToBeApproved = JSON.parse(data).content;
      this.nextBtn = JSON.parse(data).last;
    });
  }

  ngOnInit(): void {
    /*this.commentsToBeApproved.push({
      commenter: "Ivana Vlaisavljevic",
      offer: "Exit",
      imageUrl:"src/assets/img/paper.png",
      comment:"Prelepo ali i ruzno",
      id:5
    });*/

    this.service.getCommentsByPage(this.pageNum).subscribe((data: string) => {
      this.commentsToBeApproved = JSON.parse(data).content;
      this.nextBtn = JSON.parse(data).last;
    });


  }

}
