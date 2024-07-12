import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TutorialService } from '../../service/tutorial.service';

@Component({
  selector: 'ngx-add-tutorial',
  templateUrl: './add-tutorial.component.html',
  styleUrls: ['./add-tutorial.component.scss']
})
export class AddTutorialComponent implements OnInit {
  text: string;
  addForm!: FormGroup;
  tutorial: any = {}

  constructor(private tutorialService: TutorialService) { }

  ngOnInit(): void {
    this.addForm = new FormGroup({

      title: new FormControl(this.tutorial.title, Validators.required),
      content: new FormControl(this.tutorial.content, Validators.required),
    })
  }

  get title() {
    return this.addForm.get('title')
  }
  get content() {
    return this.addForm.get('content')
  }

  submit() {
    this.tutorial = this.addForm.value
    this.tutorialService.add(this.tutorial).subscribe(
      (data) => {
        console.log("success");
      }
    )
    console.log(this.tutorial);
  }
}
