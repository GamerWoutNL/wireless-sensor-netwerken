import { Component } from '@angular/core';
import { Subscription } from 'rxjs';
import { WebsocketService } from './websocket.service';
import { GlobalMeasurement } from './model/global.measurement';
import { HttpServiceService } from './http-service.service'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  
  private websocketSubscription: Subscription | undefined;
  
  title = 'frontend';
  globalMeasurement: GlobalMeasurement;

  constructor(private websocketService: WebsocketService, private httpService: HttpServiceService) { 
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
    this.httpService.getData();
  }

  ngOnDestroy() {
    if (this.websocketSubscription) {
      this.websocketSubscription.unsubscribe();
    }
    this.websocketService.disconnect();
  }

  onMessageReceive(message: string): void {
    this.globalMeasurement = JSON.parse(message);
    console.log(this.globalMeasurement.timestamp);
  }

  pad(num, size): number {
    num = num.toString();
    while (num.length < size) num = "0" + num;
    return num;
  }

}
