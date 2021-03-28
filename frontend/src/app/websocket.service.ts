import { Injectable, EventEmitter } from '@angular/core';
import { config } from '../app/config'
 
declare var SockJS;
declare var Stomp;

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  private stompClient;
  public eventHandler: EventEmitter<string> = new EventEmitter<string>();

  constructor() { 
    this.initializeWebSocketConnection();
  }

  initializeWebSocketConnection(): void {
    const ws = new SockJS(config.backend.host + '/iwsn');
    this.stompClient = Stomp.over(ws);
    const that = this;
    
    this.stompClient.connect(
      {}, 
      (frame) => {
        console.log(frame);

        that.stompClient.subscribe('/topic/data', (message) => {
        if (message.body) {
          that.eventHandler.emit(message.body);
        }
      });
    });
  }

}
