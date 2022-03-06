package dev.magaofei.ci.server.node;

import dev.magaofei.ci.server.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "node")
@Entity
public class Node extends BaseEntity {
    private Integer userId;
    private String provider;
    private String state;
    private String name;
    private String image;
    private String region;
    private String size;
    private String os;
    private String arch;
    private String kernel;
    private String variant;
    private String address;
    private Integer capacity;
    private String filter;
    private String labels;
    private String error;

}
