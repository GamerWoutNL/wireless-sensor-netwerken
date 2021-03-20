#include <Arduino.h>
#include <HardwareSerial.h>
#include <XBee.h>
#include <WiFi.h>
#include <PubSubClient.h>
#include <ArduinoJson.h>

#define LED_GREEN 18
#define LED_RED 19

WiFiClient client;
PubSubClient mqtt;
HardwareSerial XbeeSerial(1);
XBee xbee = XBee();
ZBRxResponse response = ZBRxResponse();

const char* wifi_ssid = "FRITZ!Box Fon WLAN 7360";
const char* wifi_password = "StevensLegstraat";
const char* mqtt_server = "test.mosquitto.org";
const int mqtt_port = 1883;
const char* mqtt_topic = "smartmeter-wout";

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

void connectMQTT() {
  mqtt.setClient(client);
  mqtt.setBufferSize(2048);
  mqtt.setServer(mqtt_server, mqtt_port);

  while (!mqtt.connected()) {
    mqtt.connect("");
    mqtt.subscribe(mqtt_topic);
    
    delay(500);
  }
}

void publishMQTT(String payload) {
  mqtt.publish(mqtt_topic, payload.c_str());
}

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

  connectWifi();
  connectMQTT();

  XbeeSerial.begin(9600, SERIAL_8N1, 16, 17);
  xbee.begin(XbeeSerial);

  pinMode(LED_GREEN, OUTPUT);
  pinMode(LED_RED, OUTPUT);
}

void loop() {
  mqtt.loop();

  xbee.readPacket();

  if (xbee.getResponse().isAvailable()) {
    if (xbee.getResponse().getApiId() == ZB_RX_RESPONSE) {
      xbee.getResponse().getZBRxResponse(response);

      int temperature = response.getData(0);
      int humidity = response.getData(1);

      DynamicJsonDocument doc(2048);
      
      JsonObject root = doc.to<JsonObject>();
      root["sensor"] = "DHT11";

      JsonObject data = root.createNestedObject("data");
      data["temperature"] = temperature;
      data["humidity"] = humidity;

      String payload = "";
      serializeJson(doc, payload);
      publishMQTT(payload.c_str());

      flashLed(LED_GREEN, 1, 1000);
    } else {
      flashLed(LED_RED, 1, 1000);
    }
  } else if (xbee.getResponse().isError()) {
    flashLed(LED_RED, 2, 500);
  }
}