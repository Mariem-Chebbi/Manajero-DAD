import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NbBadgeModule, NbButtonModule, NbCardModule, NbDatepickerModule, NbIconModule, NbInputModule, NbStepperModule } from '@nebular/theme';
import { CommonModule } from '@angular/common';
import { NbEvaIconsModule } from '@nebular/eva-icons';
import { PickListModule } from 'primeng/picklist';
import { OrderListModule } from 'primeng/orderlist';
import { ListReleaseComponent } from './list-release/list-release.component';
import { ReleaseRoutingModule, routedComponents } from './release-routing.module';
import { AddReleaseComponent } from './add-release/add-release.component';
import { AssignReleaseComponent } from './assign-release/assign-release.component';
import { Ng2SmartTableModule } from 'ng2-smart-table';



@NgModule({
    imports: [
        CommonModule,
        ReleaseRoutingModule,
        FormsModule,
        ReactiveFormsModule,
        NbStepperModule,
        NbCardModule,
        NbInputModule,
        NbButtonModule,
        NbDatepickerModule,
        NbBadgeModule,
        NbIconModule,
        NbEvaIconsModule,
        PickListModule,
        OrderListModule,
        NbBadgeModule,
        Ng2SmartTableModule




    ],
    declarations: [
        ...routedComponents,
        ListReleaseComponent,
        AddReleaseComponent,
        AssignReleaseComponent
    ],
})
export class ReleaseModule { }
