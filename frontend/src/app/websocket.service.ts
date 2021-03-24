import { Injectable } from '@angular/core';

declare var SockJS;
declare var Stomp;

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  public stompClient;

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
          alert(message.body);
        }
      });
    });
  }

  sendMessage(message: string) {
    //this.stompClient.send('/app/topic/message' , {}, message);
  }

}
