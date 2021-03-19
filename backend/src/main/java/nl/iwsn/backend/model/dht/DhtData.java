package nl.iwsn.backend.model.dht;

import com.google.gson.*;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.iwsn.backend.model.IData;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DhtData implements IData, JsonDeserializer<DhtData> {

    @Expose
    @SerializedName("temperature")
    private int temperature;

    @Expose
    @SerializedName("humidity")
    private int humidity;

    @Expose
    @SerializedName("timestamp")
    private ZonedDateTime timestamp;

    @Override
    public DhtData deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject root = element.getAsJsonObject();

        return DhtData.builder()
                .temperature(context.deserialize(root.get("temperature"), Integer.class))
                .humidity(context.deserialize(root.get("humidity"), Integer.class))
                .timestamp(ZonedDateTime.now())
                .build();
    }

}
