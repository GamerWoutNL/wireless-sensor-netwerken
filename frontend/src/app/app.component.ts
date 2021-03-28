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
  message = "";
  images = [944, 1011, 984].map((n) => `https://picsum.photos/id/${n}/900/500`);

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
