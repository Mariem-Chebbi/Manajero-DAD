import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { DadComponent } from './dad.component';
import { DadStepsComponent } from './dad-steps/dad-steps.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ListArchiveComponent } from './archives/list-archive/list-archive.component';

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
}, {
    path: 'steps/:id',
    component: DadStepsComponent,
},
{
    path: 'dashboard/:id',
    component: DashboardComponent,
},
{
    path: 'archives/:id',
    component: ListArchiveComponent,
}
];

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