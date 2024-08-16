import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NbGlobalPhysicalPosition, NbToastrService, NbWindowRef } from '@nebular/theme';
import { IterationService } from '../../service/iteration.service';

@Component({
  selector: 'ngx-add-iteration',
  templateUrl: './add-iteration.component.html',
  styleUrls: ['./add-iteration.component.scss']
})
export class AddIterationComponent implements OnInit {
  addForm: FormGroup;
  projectId?: any;
  positions = NbGlobalPhysicalPosition;

  constructor(
    private fb: FormBuilder,
    protected windowRef: NbWindowRef,
    private toastrService: NbToastrService,
    private iterationService: IterationService
  ) { }

  ngOnInit(): void {
    this.projectId = this.windowRef.config.context['projectId'];
    this.addForm = this.fb.group({
      name: ['', Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
    });
  }

  onSubmit() {
    const iteration = this.addForm.value;
    this.iterationService.add(iteration, this.projectId).subscribe(
      (data) => {
        console.log("success");
        this.showToast(this.positions.BOTTOM_LEFT, 'success')
        this.windowRef.close(data);
      }
    )
  }

  showToast(position, status) {
    this.toastrService.show(status || 'Success', `Objectif`, { position, status });
  }

}
