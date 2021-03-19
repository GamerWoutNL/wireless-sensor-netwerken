package nl.iwsn.backend.api;

import nl.iwsn.backend.model.dht.DhtData;
import nl.iwsn.backend.services.DatabaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api")
public class TestController {

    private final DatabaseService databaseService;

    public TestController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @GetMapping("test")
    public void get() {
        databaseService.saveDhtData(DhtData.builder().temperature(23).humidity(56).timestamp(LocalDateTime.now()).build());
    }

}
