import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ReleaseComponent } from './release.component';
import { ListReleaseComponent } from './list-release/list-release.component';



const routes: Routes = [{
    path: 'release',
    component: ReleaseComponent,
    children: [
        { path: 'list/:id', component: ListReleaseComponent },

    ],
}];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class ReleaseRoutingModule { }

export const routedComponents = [
    ListReleaseComponent,
    ReleaseComponent
];
