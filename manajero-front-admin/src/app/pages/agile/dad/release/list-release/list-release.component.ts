import { AfterViewInit, Component, OnInit } from '@angular/core';
import { FeatureService } from '../../service/feature.service';
import { ActivatedRoute } from '@angular/router';
import { PrimeNGConfig } from 'primeng/api';

@Component({
  selector: 'ngx-list-release',
  templateUrl: './list-release.component.html',
  styleUrls: ['./list-release.component.scss']
})
export class ListReleaseComponent {

  settings = {
    add: {
      addButtonContent: '<i class="nb-plus"></i>',
      createButtonContent: '<i class="nb-checkmark"></i>',
      cancelButtonContent: '<i class="nb-close"></i>',
    },
    edit: {
      editButtonContent: '<i class="nb-edit"></i>',
      saveButtonContent: '<i class="nb-checkmark"></i>',
      cancelButtonContent: '<i class="nb-close"></i>',
    },
    delete: {
      deleteButtonContent: '<i class="nb-trash"></i>',
      confirmDelete: true,
    },
    columns: {
      name: {
        title: 'Name',
        type: 'string',
      },
      status: {
        title: 'Status',
        type: 'string',
      },
      progres: {
        title: 'Progress',
        type: 'string',
      },
      startDate: {
        title: 'Start Date',
        type: 'date',
      },
      releaseDate: {
        title: 'Release Date',
        type: 'date',
      },
      description: {
        title: 'description',
        type: 'string',
      },
    },
  };
}
