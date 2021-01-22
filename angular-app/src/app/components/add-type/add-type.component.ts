import { Component, OnInit, Inject } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TypeService } from '../../services/type.service';
import { CategoryService } from '../../services/category.service';
import { CategoryModel } from '../../model/category-model';
import { FormGroup, FormControl, FormArray, FormBuilder, Validators } from '@angular/forms'
import { TypeModel, AllTypesModel } from '../../model/type-model';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import Swal from "sweetalert2";
import { map } from 'rxjs/operators';
import { EditTypeDialogComponent } from '../edit-type-dialog/edit-type-dialog.component';

@Component({
  selector: 'app-add-type',
  templateUrl: './add-type.component.html',
  styleUrls: ['./add-type.component.css']
})
export class AddTypeComponent implements OnInit {

  public types: Array<AllTypesModel> = [];
  public pageNum: number = 1;
  public nextBtn: boolean = false;
  public categories: Array<CategoryModel> = [];
  public typeForm: FormGroup = new FormGroup({
    name: new FormControl('', Validators.required),
    category: new FormControl('', Validators.required)
  });
  public typeFormByName: FormGroup = new FormGroup({
    byname: new FormControl('')
  });
  

  constructor(private typeService: TypeService,
    private categoryService: CategoryService,
    public dialog: MatDialog) {}

  ngOnInit(): void {
    this.getTypes();
    this.getCategories();
  }

  getCategories() {
    this.categoryService.getCategories().subscribe(data => {
      console.log("getcategories = ", data);
      this.categories = data;
    }, error => {
      console.log(error);
    });
  }

  editType(type: AllTypesModel) {
    const dialogRef = this.dialog.open(EditTypeDialogComponent, {
      width: '350px',
      data: {
        name: type.name,
        id: type.id,
        categoryId: type.categoryId,
        categoryName: type.categoryName
      }
    });

    dialogRef.afterClosed().subscribe(data => {
      if (data != undefined) {
        this.getTypes();
      }
    });
  }

  getTypes() {
    this.typeService.getByPage(this.pageNum).subscribe((data: any) => {
      //console.log("getByPage = ", data);
      this.types = data.content;
      this.nextBtn = data.last;
    }, (error: any) => {
      //console.log("error = ", error);
      console.log(error);
    });
  }

  nextPage() {
    this.pageNum += 1;
    this.getTypes();
  }

  previousPage() {
    this.pageNum -= 1;
    this.getTypes();
  }

  onSubmit() {
    let dto = new AllTypesModel()
    dto.name = this.typeForm.value.name;
    dto.categoryId = this.typeForm.value.category;
    //console.log("dto = ", dto);
    this.typeService.save(dto)
      .subscribe(response => {
        //console.log("saveresponse = ", response);
        this.getTypes();
        Swal.fire({
          title: 'Success!',
          text: 'Type successfully created!',
          icon: 'success',
          confirmButtonText: 'OK'
        });
      }, error => {
        //console.log("error = ", error);
        Swal.fire({
          title: 'Error!',
          text: 'Something went wrong! Type already exists',
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'OK'
        });
      })
  }

  onSubmitByName() {
    if (this.typeFormByName.value.byname == "") {
      this.getTypes();
    } else {
      //console.log("byname = ", this.typeFormByName.value.byname);
      this.typeService.getByName(this.typeFormByName.value.byname).subscribe((data: AllTypesModel) => {
        //console.log("getbynameresponse = ", data);
        this.types = [data];
      }, (error: any) => {
        //console.log("error = ", error);
        Swal.fire({
          title: 'Error!',
          text: 'Type not found',
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'OK'
        });
      });
    }
  }

  get f() {
    return this.typeForm.controls;
  }

  get fbyname() {
    return this.typeFormByName.controls;
  }

  deleteType(type: AllTypesModel) {
    //console.log("deletetype = ", type);
    this.typeService.deleteType(type.id)
      .subscribe(response => {
        //console.log("response = ", response);
        this.types = this.types.filter(item => item.id != type.id);
        Swal.fire({
          title: 'Success!',
          text: 'Type successfully deleted!',
          icon: 'success',
          confirmButtonText: 'OK'
        });
      }, error => {
        //console.log("error = ", error);
        Swal.fire({
          title: 'Error!',
          text: 'Something went wrong! ' + error.error,
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'OK'
        });
      })
  }

  getAllTypes(): Array<AllTypesModel> {
    return this.types || [];
  }
}
