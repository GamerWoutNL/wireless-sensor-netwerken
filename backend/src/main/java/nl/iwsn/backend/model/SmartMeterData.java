package nl.iwsn.backend.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmartMeterData implements IData {

    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("p1")
    private String p1;
}
