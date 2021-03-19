package nl.iwsn.backend.model.serializers;

import com.google.gson.*;
import nl.iwsn.backend.model.smartmeter.Measurement;

import java.lang.reflect.Type;

public class MeasurementSerializer implements JsonDeserializer<Measurement> {

    @Override
    public Measurement deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        String payload = element.getAsString();

        // PARSING

        return new Measurement();
    }

}
