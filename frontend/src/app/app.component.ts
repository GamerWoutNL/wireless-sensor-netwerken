import { Component } from '@angular/core';
import { Subscription } from 'rxjs';
import { WebsocketService } from './websocket.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  private websocketSubscription: Subscription | undefined;
  title = 'frontend';
  message = '';
  value = '10';

  constructor(private websocketService: WebsocketService) { }

  ngOnInit() {
    this.websocketSubscription = this.websocketService.eventHandler.subscribe((message) => this.onMessageReceive(message));
  }

  ngOnDestroy() {
    if (this.websocketSubscription) {
      this.websocketSubscription.unsubscribe();
    }
  }

  onMessageReceive(message: string): void {
    this.message = message;
  }

}
