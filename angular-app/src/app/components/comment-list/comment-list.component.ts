import {Component, Input, OnInit} from '@angular/core';
import {CommentModel} from '../../model/comment-model';
import {AuthService} from '../../services/auth.service';
import {CulturalOfferService} from '../../services/culturalOffer.service';

@Component({
  selector: 'app-comment-list',
  templateUrl: './comment-list.component.html',
  styleUrls: ['./comment-list.component.css']
})
export class CommentListComponent implements OnInit {

  @Input() offer_id: string = "";
  public comments: Array<CommentModel> | undefined;
  public pageNum: number = 1;
  public nextBtn: boolean = false;
  public totalPages: number = 1;
  public totalElements: number = 0;

  constructor(
    private authService: AuthService,
    private offerService: CulturalOfferService
  ) { }

  ngOnInit(): void {
    this.offerService.getCommentsForOffer(Number(this.offer_id), this.pageNum)
      .subscribe((data: any) => {
        console.log(data.content);
        this.comments = data.content;
        this.totalPages = data.totalPages;
        this.nextBtn = data.last;
        console.log(this.nextBtn);
        this.totalElements = data.totalElements;
      })
  }

  isAdmin() {
    return this.authService.isAdmin();
  }

  retrieveComments() {
    this.offerService.getCommentsForOffer(Number(this.offer_id), this.pageNum)
      .subscribe((data: any) => {
        this.comments = data.content;
        this.nextBtn = data.last;
      })
  }

  nextPage() {
    this.pageNum += 1;
    this.retrieveComments();
  }

  previousPage() {
    this.pageNum -= 1;
    this.retrieveComments();
  }

  firstPage() {
    this.pageNum = 1;
    this.retrieveComments();
  }

  lastPage() {
    this.pageNum = this.totalPages;
    this.retrieveComments();
  }
}
