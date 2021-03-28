package nl.iwsn.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "globalMeasurement")
public class GlobalMeasurement {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyMMddHHmmss");

    @Id
    @JsonProperty("uid")
    private String uid;

    @JsonProperty("timestamp")
    private LocalDateTime timestamp;

    @JsonProperty("instantaneous_power_used")
    private double currentInstantaneousPowerUsed;
    


}
