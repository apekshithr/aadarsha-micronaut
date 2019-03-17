package com.micronaut.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "ping_info")
public class PingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String pingUser;
    private LocalDateTime pingTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPingUser() {
        return pingUser;
    }

    public void setPingUser(String pingUser) {
        this.pingUser = pingUser;
    }

    public LocalDateTime getPingTime() {
        return pingTime;
    }

    public void setPingTime(LocalDateTime pingTime) {
        this.pingTime = pingTime;
    }

    @Override
    public String toString() {
        return "PingInfo{" +
                "id=" + id +
                ", pingUser='" + pingUser + '\'' +
                ", pingTime='" + pingTime + '\'' +
                '}';
    }

}
