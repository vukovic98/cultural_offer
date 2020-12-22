import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CulturalOffer} from "../../model/offer-mode";
import {CommentToBeApprovedModel} from "../../model/comment-to-be-approved-model";

@Component({
  selector: 'app-comment-to-be-approved',
  templateUrl: './comment-to-be-approved.component.html',
  styleUrls: ['./comment-to-be-approved.component.css']
})
export class CommentToBeApprovedComponent implements OnInit {
  @Input() public commentToBeApproved: CommentToBeApprovedModel = {id: 0, content:"", commenterName:"", image:"",offer:""};
  @Output() denyComment = new EventEmitter<number>(); //promeni any ovde i ispod da salje commentId
  @Output() approveComment = new EventEmitter<number>();
  panelOpenState = false;

  constructor() { }

  rejectComment(commentId: number){
    this.denyComment.emit(commentId);
  }

  authoriseComment(commentId: number){
    this.approveComment.emit(commentId);

  }
  ngOnInit(): void {
  }

}
