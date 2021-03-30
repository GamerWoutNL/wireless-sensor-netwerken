import { Component, OnInit, Input } from '@angular/core';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { Color, Label } from 'ng2-charts';

@Component({
  selector: 'app-graph',
  templateUrl: './graph.component.html',
  styleUrls: ['./graph.component.scss']
})
export class GraphComponent implements OnInit {

  @Input() values: number[] = [];
  @Input() label: string = '';

  labels: string[] = [];

  lineChartData: ChartDataSets[] = [
    { data: this.values, label: this.label },
  ];

  lineChartLabels: Label[] = this.labels;

  lineChartOptions = {
    responsive: true,
    elements: {
      point: {
          radius: 0
      }
    },
    scales: {
      xAxes: [{
          ticks: {
              display: false
          }
      }],
      yAxes: [{
        scaleLabel: {
          display: true,
          labelString: 'Gebruik (kW)'
        }
      }]
    }
  };

  lineChartColors: Color[] = [
    {
      borderColor: 'rgba(121, 82, 179, 1)',
      backgroundColor: 'rgba(121, 82, 179, 0.28)',
    },
  ];

  lineChartLegend = true;
  lineChartPlugins = [];
  lineChartType = 'line' as ChartType;

  ngOnInit(): void {
    for (let i = 0; i < this.values.length; i++) {
      this.labels[i] = i.toString();    
    }

    console.log(this.labels);
  }

  ngOnChanges(): void {
    for (let i = 0; i < this.values.length; i++) {
      this.labels[i] = i.toString();    
    }
    this.lineChartData = [
      { data: this.values, label: this.label },
    ];
  }
}
