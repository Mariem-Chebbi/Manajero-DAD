import { Component, OnInit } from '@angular/core';
import { ObjectiveService } from '../../service/objective.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NbGlobalPhysicalPosition, NbToastrService, NbWindowRef } from '@nebular/theme';

@Component({
  selector: 'ngx-form-objectifs',
  templateUrl: './form-objectifs.component.html',
  styleUrls: ['./form-objectifs.component.scss']
})
export class FormObjectifsComponent implements OnInit {
  projectId?: string;
  addForm: FormGroup;
  positions = NbGlobalPhysicalPosition;

  constructor(
    private objectiveService: ObjectiveService,
    private fb: FormBuilder,
    protected windowRef: NbWindowRef,
    private toastrService: NbToastrService
  ) { }

  ngOnInit(): void {
    this.projectId = this.windowRef.config.context['projectId'];
    this.addForm = this.fb.group({
      description: ['', Validators.required],
    });
  }

  addObjective() {
    console.log(this.projectId);
    if (this.addForm.valid) {
      const objective = this.addForm.value;
      this.objectiveService.add(objective, this.projectId).subscribe(
        (data) => {
          console.log("success");
          this.showToast(this.positions.BOTTOM_LEFT, 'success')
          this.windowRef.close(data); // Pass the new objective data back to the parent component
        }
      );
    }
  }

  showToast(position, status) {
    this.toastrService.show(status || 'Success', `Objectif`, { position, status });
  }
}
