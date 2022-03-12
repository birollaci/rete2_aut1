package hu.bme.aut.retelab2.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class DateEntity {

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime letrehozasIdeje;
    @UpdateTimestamp
    private LocalDateTime modositasIdeje;
}