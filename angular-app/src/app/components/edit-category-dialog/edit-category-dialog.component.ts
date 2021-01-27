import { Component, Inject } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { CategoryService } from '../../services/category.service';
import { CategoryModel } from '../../model/category-model';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import Swal from "sweetalert2";

@Component({
  selector: 'app-edit-category-dialog',
  templateUrl: './edit-category-dialog.component.html',
  styleUrls: ['./edit-category-dialog.component.css']
})
export class EditCategoryDialogComponent {
  myForm = new FormGroup({
    name: new FormControl(this.data.name, Validators.required)
  });

  constructor(
    public dialogRef: MatDialogRef<EditCategoryDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: CategoryModel,
    private categoryService: CategoryService) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

  save() {
    this.data.name = this.myForm.value.name;

    this.categoryService.updateCategory(this.data).subscribe(response => {
      this.dialogRef.close({ data: this.data });
      Swal.fire({
        title: 'Success!',
        text: 'Category successfully updated!',
        icon: 'success',
        confirmButtonText: 'OK'
      });
    }, error => {
      console.log("update error = ");
      console.log(error);
      Swal.fire({
        title: 'Error!',
        text: 'Something went wrong! Category name already exists',
        icon: 'error',
        confirmButtonColor: '#DC143C',
        confirmButtonText: 'OK'
      });
    });
  }

  close() {
    this.dialogRef.close();
  }

  get f() {
    return this.myForm.controls;
  }
}
