package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Service class for managing ratings operations
 */
@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    /**
     * Gets a list of all ratings
     * @return the list of ratings
     */
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    /**
     * Gets a rating
     * @param id the ID of the rating
     * @return a single rating or null
     */
    public Rating getRatingById(Integer id) {
        return ratingRepository.findById(id).orElse(null);
    }

    /**
     * Adds a new rating
     * @param rating rating to add
     * @return the added rating
     */
    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    /**
     * Updates a rating
     * @param id the ID of the rating
     * @param updatedRating the updated rating
     * @return the updated rating
     * @throws IllegalAccessException if updatedRating is null
     */
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

    /**
     * Deletes a rating
     * @param id the ID of the rating
     * @return the list of all ratings
     */
    public List<Rating> deleteRating(Integer id) {
        Rating targetedRating = getRatingById(id);
        if (targetedRating != null) {
            ratingRepository.deleteById(id);
        }
        return getAllRatings();
    }
}
