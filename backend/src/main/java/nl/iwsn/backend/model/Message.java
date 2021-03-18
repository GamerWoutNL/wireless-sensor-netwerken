package nl.iwsn.backend.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Expose
    @SerializedName("sensor")
    private String sensor;

    @Expose
    @SerializedName("data")
    private IData data;

}
