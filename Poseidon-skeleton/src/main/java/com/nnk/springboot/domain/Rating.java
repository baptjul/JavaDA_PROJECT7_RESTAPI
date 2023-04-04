package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Entity class representing a rating in the database
 */
@Entity
@Table(name = "Rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @NotBlank(message = "Must not be empty")
    @Column(name = "moodysRating", nullable = false)
    private String moodysRating;
    @NotBlank(message = "Must not be empty")
    @Column(name = "sandPRating", nullable = false)
    private String sandPRating;
    @NotBlank(message = "Must not be empty")
    @Column(name = "fitchRating", nullable = false)
    private String fitchRating;
    @NotNull(message = "Must not be null")
    @Column(name = "orderNumber", nullable = false)
    @Min(value = 0)
    private Integer orderNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMoodysRating() {
        return moodysRating;
    }

    public void setMoodysRating(String moodysRating) {
        this.moodysRating = moodysRating;
    }

    public String getSandPRating() {
        return sandPRating;
    }

    public void setSandPRating(String sandPRating) {
        this.sandPRating = sandPRating;
    }

    public String getFitchRating() {
        return fitchRating;
    }

    public void setFitchRating(String fitchRating) {
        this.fitchRating = fitchRating;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}
