package dev.magaofei.ci.server.step;

import dev.magaofei.ci.server.BaseEntity;

import java.time.LocalDateTime;

public class Steps extends BaseEntity {

    private Integer taskId;
    private String name;
    private Integer number;
    private String status;
    private String error;
    private Integer exitCode;
    private LocalDateTime started;
    private LocalDateTime stopped;
}
