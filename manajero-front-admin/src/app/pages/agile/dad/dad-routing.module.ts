import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { DadComponent } from './dad.component';
import { GuideeComponent } from './guide/guidee.component';

const routes: Routes = [{
    path: '',
    component: DadComponent,
    children: [{
        path: '', loadChildren: () => import('./guide/guide.module')
            .then(m => m.GuideModule),

    },
    {
        path: '', loadChildren: () => import('./project/project.module')
            .then(m => m.ProjectModule),
    },
    {
        path: '', loadChildren: () => import('./features/features.module')
            .then(m => m.FeaturesModule),
    },
    {
        path: '', loadChildren: () => import('./release/release.module')
            .then(m => m.ReleaseModule),
    },
    ],
}];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class DadRoutingModule { }

/* export const routedComponents = [
    DadComponent,
    GuideeComponent
];
 */