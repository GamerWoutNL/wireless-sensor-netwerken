package nl.iwsn.backend.api;

import nl.iwsn.backend.services.EmbeddedMessagingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class TestController {

    private final EmbeddedMessagingService messagingService;

    public TestController(EmbeddedMessagingService messagingService) {
        this.messagingService = messagingService;
    }

    @GetMapping("{message}")
    public String get(@PathVariable String message) {
        this.messagingService.publish("wout-test", message);

        return "test";
    }
}
