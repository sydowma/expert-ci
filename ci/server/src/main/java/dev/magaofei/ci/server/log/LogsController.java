package dev.magaofei.ci.server.log;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/logs")
@RestController
public class LogsController {

    @GetMapping
    public ResponseEntity<?> logs() {
        return ResponseEntity.ok().body("");
    }

    @GetMapping("/stream")
    public ResponseEntity<?> streamLog() {
        return ResponseEntity.ok().body("");
    }


}
