package com.nnk.springboot;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RatingTests {

	@Autowired
	private RatingService ratingService;
	@Autowired
	private RatingRepository ratingRepository;

	@BeforeEach
	void setup() {
		ratingRepository.deleteAll();
		ratingRepository.flush();
	}

	@Test
	public void saveRatingTest() {
		Rating rating = new Rating();
		rating.setMoodysRating("Moodys Rating");
		rating.setSandPRating("Sand Rating");
		rating.setFitchRating("Fitch Rating");
		rating.setOrderNumber(10);

		Rating newRating = ratingService.saveRating(rating);

		assertNotNull(newRating.getId());
		assertEquals(10, newRating.getOrderNumber(), 1);
	}

	@Test
	public void updateRatingTest() throws IllegalAccessException {
		Rating rating = new Rating();
		rating.setMoodysRating("Moodys Rating");
		rating.setSandPRating("Sand Rating");
		rating.setFitchRating("Fitch Rating");
		rating.setOrderNumber(10);

		Rating newRating = ratingService.saveRating(rating);

		Rating ratingUpdate = new Rating();
		ratingUpdate.setMoodysRating("Moodys Rating");
		ratingUpdate.setSandPRating("Sand Rating");
		ratingUpdate.setFitchRating("Fitch Rating");
		ratingUpdate.setOrderNumber(20);

		Rating updatedRating = ratingService.updateRating(newRating.getId(), ratingUpdate);

		assertNotNull(updatedRating.getId());
		assertEquals(20, updatedRating.getOrderNumber(), 1);
	}

	@Test
	public void getAllRatingsTest() {
		Rating firstRating = new Rating();
		firstRating.setMoodysRating("Moodys Rating");
		firstRating.setSandPRating("Sand Rating");
		firstRating.setFitchRating("Fitch Rating");
		firstRating.setOrderNumber(10);
		ratingService.saveRating(firstRating);

		Rating secondRating = new Rating();
		secondRating.setMoodysRating("John Rating");
		secondRating.setSandPRating("Sand Rating");
		secondRating.setFitchRating("Fitch Rating");
		secondRating.setOrderNumber(23);
		ratingService.saveRating(secondRating);

		assertTrue(ratingService.getAllRatings().size() > 0);
	}
	//
	@Test
	public void deleteRatingtTest() {
		Rating firstRating = new Rating();
		firstRating.setMoodysRating("Moodys Rating");
		firstRating.setSandPRating("Sand Rating");
		firstRating.setFitchRating("Fitch Rating");
		firstRating.setOrderNumber(10);
		Rating firstNewRating = ratingService.saveRating(firstRating);

		Rating secondRating = new Rating();
		secondRating.setMoodysRating("John Rating");
		secondRating.setSandPRating("Sand Rating");
		secondRating.setFitchRating("Fitch Rating");
		secondRating.setOrderNumber(23);
		ratingService.saveRating(secondRating);

		ratingService.deleteRating(firstNewRating.getId());

		assertNull(ratingService.getRatingById(firstNewRating.getId()));
	}
}
