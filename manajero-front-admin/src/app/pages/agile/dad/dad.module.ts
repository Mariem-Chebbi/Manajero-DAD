import { NgModule } from '@angular/core';
import { DadRoutingModule } from './dad-routing.module';
import { DadComponent } from './dad.component';
import { AddProjectComponent } from './project/add-project/add-project.component';

@NgModule({
    imports: [
        DadRoutingModule,
    ],
    declarations: [
        DadComponent
    ],
})
export class DadModule { }
