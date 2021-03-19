package nl.iwsn.backend.model.smartmeter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.iwsn.backend.model.IData;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmartMeterData implements IData {

    @Id
    private int uid;

    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("p1")
    private Measurement measurement;

}
