package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Entity class representing a curve point in the database
 */
@Entity
@Table(name = "Curvepoint")
public class CurvePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Integer id;
    @NotNull(message = "Must not be null")
    @Column(name = "CurveId")
    @Min(value = 0)
    private Integer curveId;
    @Column(name = "asOfDate")
    private Date asOfDate;
    @Column(name = "term")
    @Min(value = 0)
    private Double term;
    @Column(name = "value")
    @Min(value = 0)
    private Double value;
    @Column(name = "creationDate")
    private Date creationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCurveId() {
        return curveId;
    }

    public void setCurveId(Integer curveId) {
        this.curveId = curveId;
    }

    public Date getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(Date asOfDate) {
        this.asOfDate = asOfDate;
    }

    public Double getTerm() {
        return term;
    }

    public void setTerm(Double term) {
        this.term = term;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
