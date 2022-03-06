package dev.magaofei.ci.server.log;

import dev.magaofei.ci.server.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "log")
public class Logs extends BaseEntity {
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Logs{" +
                "data='" + data + '\'' +
                '}';
    }
}
