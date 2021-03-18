package nl.iwsn.backend.model.serializers;

import com.google.gson.*;
import nl.iwsn.backend.model.DhtData;
import nl.iwsn.backend.model.IData;
import nl.iwsn.backend.model.Message;
import nl.iwsn.backend.model.SmartMeterData;

import java.lang.reflect.Type;

public class MessageSerializer implements JsonDeserializer<Message> {

    @Override
    public Message deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject root = element.getAsJsonObject();

        String sensor = context.deserialize(root.get("sensor"), String.class);
        IData data = null;

        switch (sensor) {
            case "smartmeter":
                data = context.deserialize(root.get("data"), SmartMeterData.class);
                break;
            case "DHT11":
                data = context.deserialize(root.get("data"), DhtData.class);
                break;
        }

        return new Message(sensor, data);
    }

}
