package nl.iwsn.backend.model.smartmeter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.iwsn.backend.model.IData;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "smartMeterData")
public class SmartMeterData implements IData {

    @Id
    @JsonProperty("uid")
    private String uid;

    @Expose
    @SerializedName("id")
    @JsonProperty("id")
    private String id;

    @Expose
    @SerializedName("p1")
    @JsonProperty("measurement")
    private Measurement measurement;

}
