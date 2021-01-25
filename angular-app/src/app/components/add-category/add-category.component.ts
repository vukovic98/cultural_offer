import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormControl, FormArray, FormBuilder, Validators } from '@angular/forms'
import { CategoryService } from '../../services/category.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CategoryModel } from '../../model/category-model';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import Swal from "sweetalert2";
import { EditCategoryDialogComponent } from '../edit-category-dialog/edit-category-dialog.component';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css']
})
export class AddCategoryComponent implements OnInit {

  public categories: Array<CategoryModel> = [];
  public pageNum: number = 1;
  public nextBtn: boolean = false;

  public categoryForm: FormGroup = new FormGroup({
    name: new FormControl('', Validators.required)
  });

  public categoryFormByName: FormGroup = new FormGroup({
    byname: new FormControl('')
  });

  constructor(
    private categoryService: CategoryService,
    public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.getCategories();
  }

  getAllCategories() {
    return this.categories || [];
  }

  types(): FormArray {
    return this.categoryForm.get("types") as FormArray
  }

  newType(): FormGroup {
    return new FormGroup({
      name: new FormControl('', Validators.required)
    })
  }

  editCategory(category: CategoryModel) {
    const dialogRef = this.dialog.open(EditCategoryDialogComponent, {
      width: '350px',
      data: { name: category.name, id: category.id }
    });

    dialogRef.afterClosed().subscribe(data => {

      if (data != undefined) {
        this.getCategories();
      }
    });
  }

  deleteCategory(id: number) {
    console.log("callservicedelete");
    this.categoryService.deleteCategory(id).subscribe((response) => {
      this.categories = this.categories.filter(item => item.id != id);
      console.log("success = ", response);
      Swal.fire({
        title: 'Success!',
        text: 'Category successfully deleted!',
        icon: 'success',
        confirmButtonText: 'OK'
      });
    }, error => {
      console.log("error = ", error);
      Swal.fire({
        title: 'Error!',
        text: 'Something went wrong! Can not remove category.',
        icon: 'error',
        confirmButtonColor: '#DC143C',
        confirmButtonText: 'OK'
      });
    })
  }

  getCategories() {
    this.categoryService.getCategoriesByPage(this.pageNum).subscribe((data) => {
      this.categories = data.content;
      this.nextBtn = data.last;
    }, error => {
      console.log(error);
    });
  }

  nextPage() {
    this.pageNum += 1;
    this.getCategories();
  }

  previousPage() {
    this.pageNum -= 1;
    this.getCategories();
  }

  onSubmit() {
    this.categoryService.addCategory(this.categoryForm.value).subscribe(response => {
      this.getCategories();
      Swal.fire({
        title: 'Success!',
        text: 'Category successfully created!',
        icon: 'success',
        confirmButtonText: 'OK'
      });
    }, error => {
      Swal.fire({
        title: 'Error!',
        text: 'Something went wrong! Category already exist',
        icon: 'error',
        confirmButtonColor: '#DC143C',
        confirmButtonText: 'OK'
      });
    });
  }

  onSubmitByName() {
    if (this.categoryFormByName.value.byname == "") {
      this.getCategories();
    } else {
      this.categoryService.getByName(this.categoryFormByName.value.byname).subscribe((data: CategoryModel) => {
        this.categories = [data];
      }, (error: any) => {
        Swal.fire({
          title: 'Error!',
          text: 'Category not found',
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'OK'
        });
      });
    }
  }

  get f() {
    return this.categoryForm.controls;
  }

  get fbyname() {
    return this.categoryFormByName.controls;
  }
}
