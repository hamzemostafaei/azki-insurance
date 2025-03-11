package com.azki.insurance.data.access.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Persistable;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@ToString
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseJpaEntity{

    @Basic
    @Version
    @Column(name = "VERSION", nullable = false)
    private Long version;

    @Basic
    @Column(name = "CREATION_DATE", nullable = false)
    private Timestamp creationDate;

    @Basic
    @Column(name = "CREATOR_USER_ID", nullable = false)
    private Long creatorUserId;

    @Basic
    @Column(name = "LAST_UPDATE")
    private Timestamp lastUpdate;

    @Basic
    @Column(name = "UPDATER_USER_ID")
    private Long updaterUserId;

    @PrePersist
    protected void handlePrePersist() {
        this.creatorUserId = 1L;
        this.creationDate = new Timestamp(new Date().getTime());
    }

    @PreUpdate
    protected void handlePreUpdate() {
        this.updaterUserId = 1L;
    }

}
