#include <Arduino.h>
#include <HardwareSerial.h>
#include <XBee.h>

#define LED_GREEN 18
#define LED_RED 19

HardwareSerial XbeeSerial(1);

XBee xbee = XBee();
ZBRxResponse response = ZBRxResponse();

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

  XbeeSerial.begin(9600, SERIAL_8N1, 16, 17);
  xbee.begin(XbeeSerial);

  pinMode(LED_GREEN, OUTPUT);
  pinMode(LED_RED, OUTPUT);

  flashLed(LED_GREEN, 3, 50);
  flashLed(LED_RED, 3, 50);
}

void loop() {
  xbee.readPacket();

  if (xbee.getResponse().isAvailable()) {
    if (xbee.getResponse().getApiId() == ZB_RX_RESPONSE) {
      xbee.getResponse().getZBRxResponse(response);

      int temperature = response.getData(0);
      int humidity = response.getData(1);

      Serial.print("Temperature: ");
      Serial.print(temperature);
      Serial.println(" C");

      Serial.print("Humidity: ");
      Serial.print(humidity);
      Serial.println(" %");
      
      Serial.println();

      flashLed(LED_GREEN, 1, 1000);
    } else {
      flashLed(LED_RED, 1, 1000);
    }
  } else if (xbee.getResponse().isError()) {
    flashLed(LED_RED, 2, 500);
  }
}