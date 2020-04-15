package com.groceries.groceries.entities;

import ma.glasnost.orika.MapperFacade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
public class BaseEntity {

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false)
    private Date createdTimeStamp;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date", nullable = false)
    private Date updatedTimeStamp;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_date")
    private Date deletedTimeStamp;

    public <S> S toDTO(Class<S> clazz, MapperFacade mapper) throws RuntimeException {
        try {
            return mapper.map(this, clazz);
        } catch (Exception e) {
            throw new RuntimeException(
                    String.format(
                            "Error converting to class %s, message %s",
                            clazz.getTypeName(),
                            e.getLocalizedMessage()));
        }
    }

    public Date getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(Date createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }
}
