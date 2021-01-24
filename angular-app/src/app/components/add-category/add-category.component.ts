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

  /* addPostForm = new FormGroup({
    "title": new FormControl('', [Validators.required]),
    "content": new FormControl('', Validators.required)
  }); */
  public categoryFormByName: FormGroup = new FormGroup({
    byname: new FormControl('')
  });

  constructor(/*private fb: FormBuilder,*/
    private categoryService: CategoryService,
    public dialog: MatDialog) {

    /*this.categoryForm = this.fb.group({
      name: new FormControl('', Validators.required)
    });
    this.categoryFormByName = this.fb.group({
      byname: new FormControl('')
    });*/
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
    console.log("edit = ");
    console.log(category);
    const dialogRef = this.dialog.open(EditCategoryDialogComponent, {
      width: '350px',
      data: { name: category.name, id: category.id }
    });

    dialogRef.afterClosed().subscribe(data => {
      //console.log('The dialog was closed');
      //console.log(data);
      if (data != undefined) {
        this.getCategories();
      }
    });
  }

  deleteCategory(id: number) {
    this.categoryService.deleteCategory(id).subscribe((response: any) => {
      console.log("delete category = ");
      console.log(response);
      this.categories = this.categories.filter(item => item.id != id);
      Swal.fire({
        title: 'Success!',
        text: 'Category successfully deleted!',
        icon: 'success',
        confirmButtonText: 'OK'
      });
      return true;
    }, error => {
      console.log("delete category error = ");
      console.log(error);
      Swal.fire({
        title: 'Error!',
        text: 'Something went wrong! ' + error.error,
        icon: 'error',
        confirmButtonColor: '#DC143C',
        confirmButtonText: 'OK'
      });
      return false;
    })
  }

  getCategories() {
    //console.log("getcategories");
    this.categoryService.getCategoriesByPage(this.pageNum).subscribe((data: any) => {
      //console.log("getcategoriesbypage = ");
      //console.log(data);
      this.categories = data.content;
      this.nextBtn = data.last;
      console.log(this.categories);
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
    //console.log(this.categoryForm.value);
    this.categoryService.addCategory(this.categoryForm.value).subscribe(response => {
      console.log("addcategory response = ", response);
      this.getCategories();
      Swal.fire({
        title: 'Success!',
        text: 'Category successfully created!',
        icon: 'success',
        confirmButtonText: 'OK'
      });
      return true;
    }, error => {
      console.log("addcategory error = ", error);
      Swal.fire({
        title: 'Error!',
        text: 'Something went wrong! Category already exist',
        icon: 'error',
        confirmButtonColor: '#DC143C',
        confirmButtonText: 'OK'
      });
      return false;
    });
  }

  onSubmitByName() {
    //console.log(this.categoryFormByName.value);
    if (this.categoryFormByName.value.byname == "") {
      this.getCategories();
    } else {
      this.categoryService.getByName(this.categoryFormByName.value.byname).subscribe((data: CategoryModel) => {
        console.log("getbyname = ");
        console.log(data);
        this.categories = [data];
      }, (error: any) => {
        //console.log(error);
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