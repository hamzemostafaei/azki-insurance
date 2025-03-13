package com.azki.insurance.presentation.data.access.entity;

import com.azki.insurance.presentation.domain.api.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.context.SecurityContextHolder;

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
        this.creatorUserId = getUSerId();
        this.creationDate = new Timestamp(new Date().getTime());
    }

    @PreUpdate
    protected void handlePreUpdate() {
        this.updaterUserId = getUSerId();
        this.lastUpdate = new Timestamp(new Date().getTime());
    }

    //TODO: UserDetails must be used
    private long getUSerId(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDTO user) {
            return user.getUserId();
        }else{
            //System user
            return 0L;
        }
    }

}
