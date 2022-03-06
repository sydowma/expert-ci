package dev.magaofei.ci.server.stage;

import dev.magaofei.ci.server.BaseEntity;

import java.time.LocalDateTime;

public class Stage extends BaseEntity {
    private Integer repoId;
    private Integer buildId;
    private Integer number;
    private String name;
    private String kind;
    private String type;
    private String status;
    private String error;
    private Boolean errignore;
    private Integer exitCode;
    private Integer limit;
    private String os;
    private String arch;
    private String variant;
    private String kernel;
    private String machine;
    private LocalDateTime started;
    private LocalDateTime created;
    private LocalDateTime updated;
    private LocalDateTime version;
    private Boolean stageOnSuccess;
    private Boolean stageOnFailure;
    private String dependsOn;
    private String label;

}
