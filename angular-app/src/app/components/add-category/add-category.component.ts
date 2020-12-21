import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormArray, FormBuilder, Validators } from '@angular/forms'
import { CategoryService } from '../../services/category.service';
import { TypeService } from '../../services/type.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CategoryModel } from '../../model/category-model';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css']
})
export class AddCategoryComponent implements OnInit {

  categoryForm: FormGroup;

  constructor(private fb:FormBuilder,
              private categoryService: CategoryService,
              private typeService: TypeService,
              private http: HttpClient) {

    this.categoryForm = this.fb.group({
      name: new FormControl('', Validators.required),
      types: this.fb.array([]),
    }); 
  }

  types() : FormArray {
    return this.categoryForm.get("types") as FormArray
  }

  newType(): FormGroup {
    return this.fb.group({
      name: new FormControl('', Validators.required)
    })
  }

  addType() {
    this.types().push(this.newType());
  }

  removeType(i:number) {
    this.types().removeAt(i);
  }

  onSubmit() {
    console.log(this.categoryForm.value);
  }
  get f() {
    return this.categoryForm.controls;
  }

  ngOnInit(): void {
  
  }
  
  submit() {
    let obj = {
      'name': this.categoryForm.value.name,
      'types': this.categoryForm.value.types
    }
    let category!: CategoryModel;
    category.name = obj.name;
    category.types = obj.types;

    console.log(category);
    this.categoryService.addCategory(category);
  }
}
