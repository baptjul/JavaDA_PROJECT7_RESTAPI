package com.nnk.springboot.services;


import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    public Rating getRatingById(Integer id) {
        return ratingRepository.findById(id).orElse(null);
    }

    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public Rating updateRating(Integer id, Rating updatedRating) throws IllegalAccessException {
        Rating targetedRating = getRatingById(id);
        if (updatedRating != null) {
            Field[] fields = Rating.class.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                Object newValue = field.get(updatedRating);
                if (newValue != null) {
                    field.set(targetedRating, newValue);
                }
            }

            return ratingRepository.save(targetedRating);
        } else {
            return updatedRating;
        }
    }

    public List<Rating> deleteRating(Integer id) {
        Rating targetedRating = getRatingById(id);
        if (targetedRating != null) {
            ratingRepository.deleteById(id);
        }
        return getAllRatings();
    }
}
