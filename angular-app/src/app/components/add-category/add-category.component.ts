import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormControl, FormArray, FormBuilder, Validators } from '@angular/forms'
import { CategoryService } from '../../services/category.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CategoryModel } from '../../model/category-model';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css']
})
export class AddCategoryComponent implements OnInit {

  private categories: Array<CategoryModel> = [];
  public pageNum: number = 1;
  public nextBtn: boolean = false;
  public categoryForm: FormGroup;

  constructor(private fb: FormBuilder,
    private categoryService: CategoryService,
    public dialog: MatDialog,
    private http: HttpClient) {

    this.categoryForm = this.fb.group({
      name: new FormControl('', Validators.required)
    });
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
    return this.fb.group({
      name: new FormControl('', Validators.required)
    })
  }

  editCategory(category: CategoryModel){
    console.log("edit = ");
    console.log(category);
    const dialogRef = this.dialog.open(EditDialog, {
      width: '250px',
      data: {name: category.name, id: category.id}
    });

    dialogRef.afterClosed().subscribe(data => {
      //console.log('The dialog was closed');
      //console.log(data);
      if(data != undefined){
        this.getCategories();
      }
    });
  }

  deleteCategory(id: number){
    //console.log("delete with id = ");
    console.log(id);
    this.categoryService.deleteCategory(id, ()=> {
      this.categories = this.categories.filter(item => item.id != id);
    });
  }

  getCategories(){
    this.categoryService.getCategoriesByPage(this.pageNum).subscribe((data: any)  => {
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
    console.log(this.categoryForm.value);
    this.categoryService.addCategory(this.categoryForm.value, ()=>{
      this.getCategories();
    });
  }

  get f() {
    return this.categoryForm.controls;
  }
}

@Component({
  selector: 'edit-dialog',
  templateUrl: 'edit-dialog.html',
})
export class EditDialog {

  myForm = new FormGroup({
    name: new FormControl(this.data.name, Validators.required)
  });

  constructor(
    public dialogRef: MatDialogRef<EditDialog>,
    @Inject(MAT_DIALOG_DATA) public data: CategoryModel,
    private categoryService: CategoryService) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  save(){
    this.data.name = this.myForm.value.name;
    console.log(this.data);
    this.categoryService.updateCategory(this.data, ()=>{
      this.dialogRef.close({data:this.data});
    });
  }

  close(){
    this.dialogRef.close();
  }

  get f() {
    return this.myForm.controls;
  }
}