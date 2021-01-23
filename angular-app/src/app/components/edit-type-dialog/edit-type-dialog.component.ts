import { Component, OnInit, Inject } from '@angular/core';
import { TypeService } from '../../services/type.service';
import { FormGroup, FormControl, FormArray, FormBuilder, Validators } from '@angular/forms'
import { AllTypesModel } from '../../model/type-model';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import Swal from "sweetalert2";
import {map} from 'rxjs/operators';

@Component({
  selector: 'app-edit-type-dialog',
  templateUrl: './edit-type-dialog.component.html',
  styleUrls: ['./edit-type-dialog.component.css']
})
export class EditTypeDialogComponent implements OnInit {

  ngOnInit(): void {
  }

  myForm = new FormGroup({
    name: new FormControl(this.data.name, Validators.required)
  });

  constructor(
    public dialogRef: MatDialogRef<EditTypeDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: AllTypesModel,
    private typeService: TypeService) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  save(){
    this.data.name = this.myForm.value.name;
    //console.log("updateObj = ", this.data);
    this.typeService.updateType(this.data)
      .subscribe(response => {
        //console.log("response = ", response);
        this.dialogRef.close({data:this.data});
        Swal.fire({
          title: 'Success!',
          text: 'Type successfully updated!',
          icon: 'success',
          confirmButtonText: 'OK'
        });
      }, error => {
        //console.log("error = ", error);
        Swal.fire({
          title: 'Error!',
          text: 'Something went wrong! Type name already exists',
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'OK'
        });
      })
  }

  close(){
    this.dialogRef.close();
  }

  get f() {
    return this.myForm.controls;
  }
}
