package dev.magaofei.ci.server.runner;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/runner")
@RestController
public class RunnerController {

    private final RunnerService runnerService;

    public RunnerController(RunnerService runnerService) {
        this.runnerService = runnerService;
    }

    /**
     * 上报 node 节点
     * @return 返回 token
     */
    @PostMapping("/node")
    public ResponseEntity<?> uploadNode(
            @RequestBody NodeInfo nodeInfo
    ) {
        return ResponseEntity.ok().body(this.runnerService.uploadNode(nodeInfo));
    }


    /**
     * 给 runner 返回任务
     * @return
     */
    @GetMapping("/builds")
    public ResponseEntity<?> handleBuilds() {
        return ResponseEntity.ok().body("");
    }


    /**
     * 修改任务状态
     * @return
     */
    @PostMapping
    public ResponseEntity<?> changeTaskStatus() {
        return ResponseEntity.ok().body("");
    }

    @PostMapping("/log")
    public ResponseEntity<?> saveLog() {
        return ResponseEntity.ok().body("");
    }


    @PatchMapping("/log/append")
    public ResponseEntity<?> appendLog() {
        return ResponseEntity.ok().body("");
    }


}
