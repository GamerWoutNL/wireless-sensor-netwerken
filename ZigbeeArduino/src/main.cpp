#include <Arduino.h>

#define LED_GREEN 12
#define LED_RED 13

uint8_t payload[2] = {0, 0};

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

void send(uint8_t payload[], int length) {

  if (length > 240) {
    flashLed(LED_RED, 1, 1000);
    return;
  }

  uint32_t checksum = 0x42B;

  Serial.write(0x7E); //start
  Serial.write(0x00); //length lsb
  Serial.write(14 + length); //length msb
  Serial.write(0x10); //rx message
  Serial.write(0x01); //message id
  Serial.write(0x00); //long address
  Serial.write(0x13);
  Serial.write(0xA2);
  Serial.write(0x00);
  Serial.write(0x41);
  Serial.write(0x8F);
  Serial.write(0x98);
  Serial.write(0x00);
  Serial.write(0xFF); //short address
  Serial.write(0xFE);
  Serial.write(0x00); //broadcast
  Serial.write(0x00); //options 42B

  for (int i = 0; i < length; i++) { //payload
    Serial.write(payload[i]);
    checksum += payload[i];
  }

  checksum = checksum & 0xFF;
  checksum = 0xFF - checksum;

  Serial.write(checksum); //checksum

  flashLed(LED_GREEN, 1, 1000);

  delay(1000);
}

void setup() {
  Serial.begin(9600);

  pinMode(LED_GREEN, OUTPUT);
  pinMode(LED_RED, OUTPUT);

  flashLed(LED_GREEN, 3, 50);
  flashLed(LED_RED, 3, 50);
}

void loop() {
  payload[0] = 45;
  payload[1] = 46;
  send(payload, sizeof(payload) / sizeof(payload[0]));

  payload[0] = 42;
  payload[1] = 49;
  send(payload, sizeof(payload) / sizeof(payload[0]));
}
