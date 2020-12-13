import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-subscribed-items-list',
  templateUrl: './subscribed-items-list.component.html',
  styleUrls: ['./subscribed-items-list.component.css']
})
export class SubscribedItemsListComponent implements OnInit {

  @Input() public offers: any;

  constructor() { }

  ngOnInit(): void {
  }

}
