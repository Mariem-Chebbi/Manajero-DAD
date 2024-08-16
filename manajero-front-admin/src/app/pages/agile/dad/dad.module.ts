import { NgModule } from '@angular/core';
import { DadRoutingModule } from './dad-routing.module';
import { DadComponent } from './dad.component';
import { AddProjectComponent } from './project/add-project/add-project.component';
import { ShowFeaturesComponent } from './features/show-features/show-features.component';
import { ListReleaseComponent } from './release/list-release/list-release.component';
import { DadStepsComponent } from './dad-steps/dad-steps.component';
import { NbAccordionModule, NbAutocompleteModule, NbButtonModule, NbCardModule, NbCheckboxModule, NbDatepickerModule, NbDialogModule, NbIconModule, NbInputModule, NbListModule, NbSelectModule, NbStepperModule, NbToastrModule, NbUserModule, NbWindowModule } from '@nebular/theme';
import { FeaturesModule } from './features/features.module';
import { ListObjectifsComponent } from './objectifs/list-objectifs/list-objectifs.component';
import { ListUsersComponent } from './users/list-users/list-users.component';
import { CommonModule } from '@angular/common';
import { NbEvaIconsModule } from '@nebular/eva-icons';
import { FormObjectifsComponent } from './objectifs/form-objectifs/form-objectifs.component';
import { EditFormObjectifsComponent } from './objectifs/edit-form-objectifs/edit-form-objectifs.component';
import { AddFormUserComponent } from './users/add-form-user/add-form-user.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TagModule } from 'primeng/tag';
import { CreateTeamFormComponent } from './users/create-team-form/create-team-form.component';
import { DetailsUserComponent } from './users/details-user/details-user.component';
import { ReleaseModule } from './release/release.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ChartsModule } from '../../charts/charts.module';
import { Ng2CompleterModule } from 'ng2-completer';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { ChartModule } from 'angular2-chartjs';
import { NgxEchartsModule } from 'ngx-echarts';
import { ListIterationComponent } from './iteration/list-iteration/list-iteration.component';
import { AddIterationComponent } from './iteration/add-iteration/add-iteration.component';
import { ListFeaturesIterationComponent } from './iteration/list-features-iteration/list-features-iteration.component';
import { ListArchiveComponent } from './archives/list-archive/list-archive.component';
@NgModule({
    imports: [
        CommonModule,
        DadRoutingModule,
        NbStepperModule,
        NbCardModule,
        NbButtonModule,
        FeaturesModule,
        NbListModule,
        NbUserModule,
        NbIconModule,
        NbEvaIconsModule,
        NbWindowModule.forChild(),
        NbInputModule,
        FormsModule,
        ReactiveFormsModule,
        TagModule,
        NbToastrModule.forRoot(),
        NbSelectModule,
        ReleaseModule,
        ChartsModule,
        NgxChartsModule,
        ChartModule,
        NgxEchartsModule,
        NbDatepickerModule,
        NbCheckboxModule,
        NbAccordionModule
    ],
    declarations: [
        DadComponent,
        DadStepsComponent,
        ListObjectifsComponent,
        ListUsersComponent,
        FormObjectifsComponent,
        EditFormObjectifsComponent,
        AddFormUserComponent,
        CreateTeamFormComponent,
        DetailsUserComponent,
        DashboardComponent,
        ListIterationComponent,
        AddIterationComponent,
        ListFeaturesIterationComponent,
        ListArchiveComponent,

    ]
})
export class DadModule { }
