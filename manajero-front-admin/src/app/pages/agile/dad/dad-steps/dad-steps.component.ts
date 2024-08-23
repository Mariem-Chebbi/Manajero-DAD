import { Component, OnInit } from '@angular/core';
import { FeatureService } from '../service/feature.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'ngx-dad-steps',
  templateUrl: './dad-steps.component.html',
  styleUrls: ['./dad-steps.component.scss']
})
export class DadStepsComponent implements OnInit {
  featureList: any[]
  projectId: string;
  content: string = 'features';

  users: { name: string, title: string }[] = [
    { name: 'Carla Espinosa', title: 'Nurse' },
    { name: 'Bob Kelso', title: 'Doctor of Medicine' },
    { name: 'Janitor', title: 'Janitor' },
    { name: 'Perry Cox', title: 'Doctor of Medicine' },
    { name: 'Ben Sullivan', title: 'Carpenter and photographer' },
  ];


  constructor(
    private featureService: FeatureService,
    private router: Router,
    private route: ActivatedRoute,
  ) {
    this.route.paramMap.subscribe(params => {
      this.projectId = params.get('id'); // No conversion needed for string IDs
    });
  }

  ngOnInit() {
  }

  navigateToIteration() {
    this.content = 'iterations';
  }

  navigateToFeature() {
    this.content = 'features'
  }

  goToArchives() {
    this.router.navigate(['/pages/agile/dad/archives/' + this.projectId]);
  }

  goToTheDashboard(){
    this.router.navigate(['/pages/agile/dad/dashboard/' + this.projectId]);

  }



}
