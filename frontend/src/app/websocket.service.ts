import { Injectable, EventEmitter } from '@angular/core';
 
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

  initializeWebSocketConnection() {
    const serverUrl = 'http://localhost:9865/iwsn';
    const ws = new SockJS(serverUrl);
    this.stompClient = Stomp.over(ws);
    const that = this;
    
    this.stompClient.connect({}, function(frame) {
      that.stompClient.subscribe('/topic/data', (message) => {
        if (message.body) {
          that.eventHandler.emit(message.body);
        }
      });
    });
  }

}
