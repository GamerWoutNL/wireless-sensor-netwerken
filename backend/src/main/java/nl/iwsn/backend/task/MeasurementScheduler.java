package nl.iwsn.backend.task;

import nl.iwsn.backend.services.MeasurementService;
import nl.iwsn.backend.services.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MeasurementScheduler {

    private MeasurementService measurementService;
    private WebSocketService webSocketService;

    @Autowired
    public MeasurementScheduler(MeasurementService measurementService, WebSocketService webSocketService) {
        this.measurementService = measurementService;
        this.webSocketService = webSocketService;
    }

    @Scheduled(cron = "0 * * * * *")
    public void generateGlobalMeasurement() {
        this.webSocketService.send(this.measurementService.getSerializedMeasurement(24));
    }
}
