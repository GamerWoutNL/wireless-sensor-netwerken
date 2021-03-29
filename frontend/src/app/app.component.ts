import { Component } from '@angular/core';
import { Subscription } from 'rxjs';
import { WebsocketService } from './websocket.service';
import { GlobalMeasurement } from './model/global.measurement';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  
  private websocketSubscription: Subscription | undefined;
  
  title = 'frontend';
  globalMeasurement: GlobalMeasurement;

  constructor(private websocketService: WebsocketService) { 
    this.globalMeasurement = {
      timestamp: {
        date: {
          year: 0,
          month: 0,
          day: 0
        },
        time: {
          hour: 0,
          minute: 0,
          second: 0,
          nano: 0
        }
      },
      currentInstantaneousPowerUsed: 0,
      powerOverHours: [],
      totalCost: 0,
      temperature: 0,
      humidity: 0,
      smartMeterStatus: false,
      dhtStatus: false,
      temperatureTrend: 0,
      humidityTrend: 0,
    };
  }

  ngOnInit() {
    this.websocketSubscription = this.websocketService.eventHandler.subscribe((message) => this.onMessageReceive(message));
  }

  ngOnDestroy() {
    if (this.websocketSubscription) {
      this.websocketSubscription.unsubscribe();
    }
  }

  onMessageReceive(message: string): void {
    this.globalMeasurement = JSON.parse(message)
  }

}
