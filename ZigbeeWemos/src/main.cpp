#include <Arduino.h>
#include <ESP8266WiFi.h>
#include <PubSubClient.h>
#include <XBee.h>

#define LED_DATA D7

WiFiClient client;
PubSubClient mqtt;

const char* wifi_ssid = "FRITZ!Box Fon WLAN 7360";
const char* wifi_password = "StevensLegstraat";
const char* mqtt_server = "test.mosquitto.org";
const int mqtt_port = 1883;
const char* mqtt_topic = "smartmeter-wout";

XBee xbee = XBee();
XBeeResponse response = XBeeResponse();
ZBRxResponse rx = ZBRxResponse();
ModemStatusResponse msr = ModemStatusResponse();

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
  pinMode(LED_DATA, OUTPUT);

  Serial.begin(9600);
  xbee.begin(Serial);
  
  flashLed(LED_DATA, 3, 50);

  //connectWifi();
  //connectMQTT();
}

void loop() {
  //mqtt.loop();

  xbee.readPacket();
    
  if (xbee.getResponse().isAvailable()) {
    flashLed(LED_DATA, 1, 1000);
    
    // if (xbee.getResponse().getApiId() == ZB_RX_RESPONSE) {
    //   // got a zb rx packet
      
    //   // now fill our zb rx class
    //   xbee.getResponse().getZBRxResponse(rx);
          
    //   if (rx.getOption() == ZB_PACKET_ACKNOWLEDGED) {
    //       // the sender got an ACK
    //       flashLed(LED_BUILTIN, 10, 10);
    //   } else {
    //       // we got it (obviously) but sender didn't get an ACK
    //       flashLed(LED_BUILTIN, 2, 20);
    //   }
    //   // set dataLed PWM to value of the first byte in the data
    //   flashLed(LED_DATA, 1, 500);
    // } else if (xbee.getResponse().getApiId() == MODEM_STATUS_RESPONSE) {
    //   xbee.getResponse().getModemStatusResponse(msr);
    //   // the local XBee sends this response on certain events, like association/dissociation
      
    //   if (msr.getStatus() == ASSOCIATED) {
    //     // yay this is great.  flash led
    //     flashLed(LED_BUILTIN, 10, 10);
    //   } else if (msr.getStatus() == DISASSOCIATED) {
    //     // this is awful.. flash led to show our discontent
    //     flashLed(LED_BUILTIN, 10, 10);
    //   } else {
    //     // another status
    //     flashLed(LED_BUILTIN, 5, 10);
    //   }
    // } else {
    //   // not something we were expecting
    //   flashLed(LED_BUILTIN, 1, 25);    
    // }
  }
}
