package dev.magaofei.ci.server.builds;

import dev.magaofei.ci.server.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "builds")
@Entity
public class Builds extends BaseEntity {

    private Integer repoId;
    private Integer configId;
    private String trigger;
    private Integer number;
    private Integer parent;
    private String status;
    private String error;
    private String event;
    private String action;
    private String link;
    private LocalDateTime time;
    private String title;
    private String message;
    private String before;
    private String after;
    private String ref;
    private String sourceRepo;
    private String source;
    private String target;
    private String authorName;
    private String authorEmail;
    private String authorAvatar;
    private String sender;
    private String deploy;
    private String params;
    private LocalDateTime started;
    private LocalDateTime finished;
    private LocalDateTime created;
    private LocalDateTime updated;
    private LocalDateTime version;

}
