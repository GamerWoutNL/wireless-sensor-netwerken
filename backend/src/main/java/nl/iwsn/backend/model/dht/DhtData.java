package nl.iwsn.backend.model.dht;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.*;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.iwsn.backend.model.IData;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "dhtData")
public class DhtData implements IData, JsonDeserializer<DhtData> {

    @Id
    @JsonProperty("uid")
    private String uid;

    @Expose
    @SerializedName("temperature")
    @JsonProperty("temperature")
    private int temperature;

    @Expose
    @SerializedName("humidity")
    @JsonProperty("humidity")
    private int humidity;

    @JsonProperty("timestamp")
    private LocalDateTime timestamp;

    @Override
    public DhtData deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject root = element.getAsJsonObject();

        return DhtData.builder()
                .temperature(context.deserialize(root.get("temperature"), Integer.class))
                .humidity(context.deserialize(root.get("humidity"), Integer.class))
                .timestamp(LocalDateTime.now())
                .build();
    }
}
