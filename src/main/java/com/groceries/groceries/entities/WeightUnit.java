package com.groceries.groceries.entities;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "weight_unit")
@SQLDelete(sql =
        "UPDATE weight_unit SET deleted_date=now() " +
                "WHERE weight_unit_id = ?")
@Where(clause = "deleted_date IS NULL")
public class WeightUnit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "weight_unit_id")
    private Long weightUnitId;

    @Column(name = "weight_unit_name")
    private String weightUnitName;

    public WeightUnit() {
    }

    public Long getWeightUnitId() {
        return weightUnitId;
    }

    public void setWeightUnitId(Long weightUnitId) {
        this.weightUnitId = weightUnitId;
    }

    public String getWeightUnitName() {
        return weightUnitName;
    }

    public void setWeightUnitName(String weightUnitName) {
        this.weightUnitName = weightUnitName;
    }
}