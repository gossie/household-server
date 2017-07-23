package household;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/status")
@RequiredArgsConstructor
public class StatusController {

    @GetMapping
    public String getStatus() {
        return "alive";
    }
}
