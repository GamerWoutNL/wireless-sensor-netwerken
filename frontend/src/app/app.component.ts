import { Component } from '@angular/core';
import { WebsocketService } from './websocket.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  title = 'Websockets';
  input;

  constructor(private websocketService: WebsocketService) { 
  }

  sendMessage(message: string) {
    this.websocketService.sendMessage(message);
  }
}
