import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-simple-value-holder',
  templateUrl: './simple-value-holder.component.html',
  styleUrls: ['./simple-value-holder.component.scss']
})
export class SimpleValueHolderComponent implements OnInit {

  @Input() label: string = '';
  @Input() value: string = '';
  @Input() trend: number = 0.0;

  constructor() { }

  ngOnInit(): void {
  }

}
