package dev.magaofei.ci.server.repo;

import dev.magaofei.ci.server.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "repository")
public class Repository extends BaseEntity {

    private String name;
    private String path;
    private String namespaceFullPath;

}
