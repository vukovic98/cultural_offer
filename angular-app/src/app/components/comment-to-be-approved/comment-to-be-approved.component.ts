import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CulturalOffer} from "../../model/offer-mode";
import {CommentToBeApprovedModel} from "../../model/comment-to-be-approved-model";

@Component({
  selector: 'app-comment-to-be-approved',
  templateUrl: './comment-to-be-approved.component.html',
  styleUrls: ['./comment-to-be-approved.component.css']
})
export class CommentToBeApprovedComponent implements OnInit {
  @Input() public commentToBeApproved: CommentToBeApprovedModel = {id: 0, content: "", commenterName: "",offer: "",image: {id: '1',picByte: ""},commenterEmail: ""};
  @Output() denyComment = new EventEmitter<number>();
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
