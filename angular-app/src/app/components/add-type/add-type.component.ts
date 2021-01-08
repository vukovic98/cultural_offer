import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TypeService } from '../../services/type.service';
import { CategoryService } from '../../services/category.service';
import { CategoryModel } from '../../model/category-model';
import { FormGroup, FormControl, FormArray, FormBuilder, Validators } from '@angular/forms'
import { TypeModel, AllTypesModel } from '../../model/type-model';

@Component({
  selector: 'app-add-type',
  templateUrl: './add-type.component.html',
  styleUrls: ['./add-type.component.css']
})
export class AddTypeComponent implements OnInit {

  private types: Array<AllTypesModel> = [];
  public pageNum: number = 1;
  public nextBtn: boolean = false;
  categories: Array<CategoryModel> = [];
  typeForm: FormGroup;

  constructor(private fb: FormBuilder,
    private typeService: TypeService,
    private categoryService: CategoryService,
    private http: HttpClient) {

    this.typeForm = this.fb.group({
      name: new FormControl('', Validators.required),
      category: new FormControl('', Validators.required),
    });
  }


  ngOnInit(): void {
    this.getTypes();
    this.getCategories();
  }

  getCategories() {
    this.categoryService.getCategories().subscribe(data => {
      this.categories = data;
      console.log("get categories");
      console.log(this.categories);
    }, error => {
      console.log(error);
    });
  }
  
  getTypes() {
    this.typeService.getByPage(this.pageNum).subscribe((data: any) => {
      //console.log("data = ");
      //console.log(data);
      this.types = data.content;
      //console.log("types = " + this.types);
      this.nextBtn = data.last;
      //console.log("nextBtn = " + this.nextBtn);
    }, (error: any) => {
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
    console.log(dto);
    this.typeService.save(dto, ()=> {
      this.getTypes();
    })
    /*this.categoryService.addCategory(this.categoryForm.value, ()=>{
      this.refreshCategories();
    });*/
  }

  get f() {
    return this.typeForm.controls;
  }

  /*refreshTypes(){
    this.typeService.getAllTypes().subscribe(data => {
      this.types = data;
      console.log(this.types);
    }, error => {
      console.log(error);
    });
  }*/

  deleteType(type: AllTypesModel) {
    console.log("delete with id = ");
    console.log(type);
    this.typeService.deleteType(type.id, () => {
      this.types = this.types.filter(item => item.id != type.id);

    });
  }

  getAllTypes(): Array<AllTypesModel> {
    return this.types || [];
  }
}
