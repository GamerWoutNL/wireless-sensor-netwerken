#include <Arduino.h>
// #include <XBee.h>

#define LED_GREEN 12
#define LED_RED 13

// XBee xbee = XBee();
// uint8_t payload[5] = { 0x48, 0x6f, 0x6c, 0x61, 0x21 };
// XBeeAddress64 address = XBeeAddress64(0x0013a200, 0x418f9800); //0013A200418F9800
// ZBTxRequest request = ZBTxRequest(address, payload, sizeof(payload));
// ZBTxStatusResponse status = ZBTxStatusResponse();

uint8_t packet[] = { 0x7E, 0x00, 0x12, 0x10, 0x01, 0x00, 0x13, 0xA2, 0x00, 0x41, 0x8F, 0x98, 0x00, 0xFF, 0xFE, 0x00, 0x00, 0x45, 0x77, 0x61, 0x21, 0x96 };

void flashLed(int pin, int times, int wait) {
  for (int i = 0; i < times; i++) {
    digitalWrite(pin, HIGH);
    delay(wait);
    digitalWrite(pin, LOW);
    
    if (i + 1 < times) {
      delay(wait);
    }
  }
}

void setup() {
  Serial.begin(9600);
  // xbee.begin(Serial);

  pinMode(LED_GREEN, OUTPUT);
  pinMode(LED_RED, OUTPUT);

  flashLed(LED_GREEN, 3, 50);
  flashLed(LED_RED, 3, 50);
}

void loop() {
  // xbee.send(request);

  // if (xbee.readPacket(500)) {       	
  //   if (xbee.getResponse().getApiId() == ZB_TX_STATUS_RESPONSE) {
  //     xbee.getResponse().getZBTxStatusResponse(status);
      
  //     if (status.getDeliveryStatus() == SUCCESS) {
  //       flashLed(LED_GREEN, 1, 1000);
  //     } else {
  //       flashLed(LED_RED, 1, 1000);
  //     }
  //   }
  // } else if (xbee.getResponse().isError()) {
  //   flashLed(LED_RED, 4, 500);
  // } else {
  //   flashLed(LED_RED, 2, 500);
  // }

  Serial.write(packet, sizeof(packet));
  flashLed(LED_GREEN, 1, 1000);

  delay(2000);
}
