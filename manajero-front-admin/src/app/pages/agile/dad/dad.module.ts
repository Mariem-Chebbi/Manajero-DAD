import { NgModule } from '@angular/core';
import { DadRoutingModule } from './dad-routing.module';
import { DadComponent } from './dad.component';
import { AddProjectComponent } from './project/add-project/add-project.component';
import { ShowFeaturesComponent } from './features/show-features/show-features.component';
import { ListReleaseComponent } from './release/list-release/list-release.component';

@NgModule({
    imports: [
        DadRoutingModule,
    ],
    declarations: [
        DadComponent,
    ],
})
export class DadModule { }
