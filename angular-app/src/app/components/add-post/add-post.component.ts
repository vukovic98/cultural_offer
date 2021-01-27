import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {CulturalOffer} from '../../model/offer-mode';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AddPostModel} from '../../model/post-model';

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent implements OnInit {

  addPostForm = new FormGroup({
    "title": new FormControl('', [Validators.required]),
    "content": new FormControl('', Validators.required)
  });

  constructor(public dialogRef: MatDialogRef<AddPostComponent>,
              @Inject(MAT_DIALOG_DATA) public data: AddPostModel) { }

  ngOnInit(): void {
  }

  submitPost() {
    this.data.title = this.addPostForm.value.title;
    this.data.content = this.addPostForm.value.content;
    this.dialogRef.close({data: this.data});
  }

}
