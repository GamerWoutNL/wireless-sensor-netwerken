#include <Arduino.h>
#include <ESP8266WiFi.h>
#include <PubSubClient.h>
#include "DHT.h"

#define DHTPIN 5
#define DHTTYPE DHT11

WiFiClient client;
PubSubClient mqtt;
DHT dht(DHTPIN, DHTTYPE);

const char* wifi_ssid = "FRITZ!Box Fon WLAN 7360";
const char* wifi_password = "StevensLegstraat";
const char* mqtt_server = "test.mosquitto.org";
const int mqtt_port = 1883;
const char* mqtt_topic = "smartmeter/raw";

unsigned long lastMsg = 0;

void connectWifi() {
  delay(10);

  WiFi.mode(WIFI_STA);
  WiFi.begin(wifi_ssid, wifi_password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
  }

  WiFi.setAutoReconnect(true);
  WiFi.persistent(true);
}

void callbackMQTT(char* topic, byte* pl, unsigned int length) {
  String payload = "";
  for (unsigned int i = 0; i < length; i++) {
    payload += (char)pl[i];
  }

  Serial.print(payload);
}

void connectMQTT() {
  mqtt.setClient(client);
  mqtt.setBufferSize(2048);
  mqtt.setCallback(callbackMQTT);
  mqtt.setServer(mqtt_server, mqtt_port);

  while (!mqtt.connected()) {
    mqtt.connect("");
    mqtt.subscribe(mqtt_topic);
    
    delay(500);
  }
}

void setup() {
  Serial.begin(9600);
  dht.begin();
  connectWifi();
  connectMQTT();
}

void loop() {
  mqtt.loop();

  unsigned long now = millis();
  if (now - lastMsg > 2000) {
    lastMsg = now;

    float humidity = dht.readHumidity();
    float temperature = dht.readTemperature();

    if (isnan(humidity) || isnan(temperature)) {
      return;
    }

    Serial.print(String((int)temperature) + ":" + String((int)humidity));
  }
  
  
}
