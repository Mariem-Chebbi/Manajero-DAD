import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FeatureService } from '../../service/feature.service';

@Component({
  selector: 'ngx-details-feature',
  templateUrl: './details-feature.component.html',
  styleUrls: ['./details-feature.component.scss']
})
export class DetailsFeatureComponent {
  @Input() item: any;
  @Output() close = new EventEmitter<void>();
  editTitle: boolean = false;
  editDescription: boolean = false;
  feature: any = {};

  constructor(
    private featureService: FeatureService
  ) { }

  onEditTitle() {
    this.featureService.edit(this.item).subscribe(
      (data) => {
        this.editTitle = !this.editTitle
        console.log("success")
      }
    )
  }

  onEditDescription() {
    this.featureService.edit(this.item).subscribe(
      (data) => {
        this.editDescription = !this.editDescription
        console.log("success")
      }
    )
  }


  onStatusChange() {
    this.editFeature();
  }

  onPriorityChange() {
    this.editFeature();
  }

  editFeature() {
    this.featureService.edit(this.item).subscribe(
      (data) => {
        console.log("success")
      }
    )
  }

  confirmDelete() {
    if (window.confirm('Are you sure you want to delete this feature?')) {
      this.onDelete();
    }
  }

  onDelete(){
    this.featureService.delete(this.item.featureId).subscribe(
      (data)=>{
        window.location.reload();
      }
    )
  }

  closeDetails() {
    this.close.emit();
  }
}
