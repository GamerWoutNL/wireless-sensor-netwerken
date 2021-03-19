package nl.iwsn.backend.model.dht;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.iwsn.backend.model.IData;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DhtData implements IData {

    @Expose
    @SerializedName("temperature")
    private int temperature;

    @Expose
    @SerializedName("humidity")
    private int humidity;

}
