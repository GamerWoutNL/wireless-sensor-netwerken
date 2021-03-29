package nl.iwsn.backend.task;

import com.google.gson.Gson;
import nl.iwsn.backend.model.GlobalMeasurement;
import nl.iwsn.backend.services.MeasurementService;
import nl.iwsn.backend.services.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MeasurementScheduler {

    private MeasurementService measurementService;
    private WebSocketService webSocketService;
    private Gson gson;

    @Autowired
    public MeasurementScheduler(MeasurementService measurementService, WebSocketService webSocketService) {
        this.measurementService = measurementService;
        this.webSocketService = webSocketService;
        this.gson = new Gson();
    }

    @Scheduled(cron = "0 * * * * *")
    public void generateGlobalMeasurement() {
        GlobalMeasurement measurement = this.measurementService.createMeasurement(24);
        String jsonString = gson.toJson(measurement);
        webSocketService.send(jsonString);
    }
}
