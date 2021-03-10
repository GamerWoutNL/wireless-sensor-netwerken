#include <Arduino.h>

String received = "";

void setup() {
  Serial.begin(9600);
}

void loop() {
  if (Serial.available() > 0) {
    received = Serial.readString();
    
    Serial.println(received);
  }
}