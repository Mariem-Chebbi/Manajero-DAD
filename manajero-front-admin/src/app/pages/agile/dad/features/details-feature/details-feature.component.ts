import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FeatureService } from '../../service/feature.service';
import { ReleaseService } from '../../service/release.service';
import { UserService } from '../../service/user.service';

@Component({
  selector: 'ngx-details-feature',
  templateUrl: './details-feature.component.html',
  styleUrls: ['./details-feature.component.scss']
})
export class DetailsFeatureComponent implements OnInit {
  @Input() item: any;
  @Output() close = new EventEmitter<void>();
  editTitle: boolean = false;
  editDescription: boolean = false;
  feature: any = {};
  releaseList: any[] = [];
  @Input() projectId: string;
  release: any;
  userList?: any[];

  constructor(
    private featureService: FeatureService,
    private releaseService: ReleaseService,
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.getAllReleases();
    this.getUsers();
  }

  onEditTitle() {
    this.featureService.edit(this.item).subscribe(
      (data) => {
        this.editTitle = !this.editTitle
        //console.log("success")
      }
    )
  }

  onEditDescription() {
    this.featureService.edit(this.item).subscribe(
      (data) => {
        this.editDescription = !this.editDescription
        //console.log("success")
      }
    )
  }


  onStatusChange() {
    this.editFeature();
  }

  onPriorityChange() {
    this.editFeature();
  }

  onReleaseChange(event) {
    this.releaseService.getById(event).subscribe(
      (data) => {
        this.release = data
        this.item.release = this.release
        this.featureService.edit(this.item).subscribe(
          (data) => {
            //console.log(data)
          }
        )
      }
    )
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

  onDelete() {
    this.featureService.archive(this.item.featureId).subscribe(
      (data) => {
        window.location.reload();
      }
    )
  }

  closeDetails() {
    this.close.emit();
  }

  getAllReleases() {
    this.releaseService.getAll(this.projectId).subscribe(
      (data) => {
        this.releaseList = data
      }
    )
  }

  get releaseId(): string | undefined {
    return this.item?.release?.releaseId;
  }

  set releaseId(value: string | undefined) {
    if (this.item && this.item.release) {
      this.item.release.releaseId = value;
    }
  }

  get userId(): any {
    return this.item?.user?._id;
  }

  set userId(value: any | undefined) {
    if (this.item && this.item.user) {
      this.item.user = value;
    }
  }

  getUsers() {
    this.userService.getusers(this.projectId).subscribe(
      (data) => {
        console.log(data)
        this.userList = data
      }
    )
  }

  onUserchange(event) {
    this.userService.getById(event).subscribe(
      (data) => {
        this.item.user = data
        this.featureService.edit(this.item).subscribe(
          (data) => {
            console.log(data)
          }
        )
      }
    )
  }
}
