package nl.iwsn.backend.model;

import com.google.gson.*;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.iwsn.backend.model.dht.DhtData;
import nl.iwsn.backend.model.smartmeter.SmartMeterData;

import java.lang.reflect.Type;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message implements JsonDeserializer<Message> {

    @Expose
    @SerializedName("sensor")
    private String sensor;

    @Expose
    @SerializedName("data")
    private IData data;

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

        return Message.builder()
                .sensor(sensor)
                .data(data)
                .build();
    }
}
