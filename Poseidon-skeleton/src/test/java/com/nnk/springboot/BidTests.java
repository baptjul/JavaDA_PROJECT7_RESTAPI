package com.nnk.springboot;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import static org.junit.Assert.*;
import com.nnk.springboot.services.BidListService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.BeforeEach;


import javax.persistence.Column;
import java.util.List;
import java.util.Optional;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BidTests {

	@Autowired
	private BidListService bidListService;
	@Autowired
	private BidListRepository bidListRepository;

	@BeforeEach
	void setup() {
		bidListRepository.deleteAll();
		bidListRepository.flush();
	}

//	@Test
//	public void bidListTest() {
//		BidList bid = new BidList("Account Test", "Type Test", 10d);
//
//		// Save
//		bid = bidListRepository.save(bid);
//		Assert.assertNotNull(bid.getBidListId());
//		Assert.assertEquals(bid.getBidQuantity(), 10d, 10d);
//
//		// Update
//		bid.setBidQuantity(20d);
//		bid = bidListRepository.save(bid);
//		Assert.assertEquals(bid.getBidQuantity(), 20d, 20d);
//
//		// Find
//		List<BidList> listResult = bidListRepository.findAll();
//		Assert.assertTrue(listResult.size() > 0);
//
//		// Delete
//		Integer id = bid.getBidListId();
//		bidListRepository.delete(bid);
//		Optional<BidList> bidList = bidListRepository.findById(id);
//		Assert.assertFalse(bidList.isPresent());
//	}

	@Test
	public void addBidFormTest() {
		BidList bid = new BidList();
		bid.setAccount("Account Test");
		bid.setType("Type Test");
		bid.setBidQuantity(10d);

		BidList newBid = bidListService.addBidForm(bid);

		assertNotNull(newBid.getBidListId());
		assertEquals("Account Test", newBid.getAccount());
	}

	@Test
	public void updateBidTest() throws IllegalAccessException {
		BidList bid = new BidList();
		bid.setAccount("Account Test");
		bid.setType("Type Test");
		bid.setBidQuantity(10d);

		BidList newBid = bidListService.addBidForm(bid);

		BidList bidUpdate = new BidList();
		bidUpdate.setAccount("Account Test");
		bidUpdate.setType("Type Test");
		bidUpdate.setBidQuantity(20d);

		BidList updatedBid = bidListService.updateBid(newBid.getBidListId(), bidUpdate);

		assertNotNull(updatedBid.getBidListId());
		assertEquals(20d, updatedBid.getBidQuantity(), 0.0001d);
	}


	@Test
	public void getAllBidsTest() {
		BidList firstBid = new BidList();
		firstBid.setAccount("Account Test");
		firstBid.setType("Type Test");
		firstBid.setBidQuantity(10d);
		bidListService.addBidForm(firstBid);

		BidList secondBid = new BidList();
		secondBid.setAccount("Second Account Test");
		secondBid.setType("Type Test 2");
		secondBid.setBidQuantity(20d);
		bidListService.addBidForm(secondBid);

		assertTrue(bidListService.getAllBids().size() > 0);
	}

	@Test
	public void deleteBidTest() {
		BidList firstBid = new BidList();
		firstBid.setAccount("Account Test");
		firstBid.setType("Type Test");
		firstBid.setBidQuantity(10d);
		BidList firstNewBid = bidListService.addBidForm(firstBid);

		BidList secondBid = new BidList();
		secondBid.setAccount("Second Account Test");
		secondBid.setType("Type Test 2");
		secondBid.setBidQuantity(20d);
		bidListService.addBidForm(secondBid);

		bidListService.deleteBid(firstNewBid.getBidListId());

		assertNull(bidListService.getBidById(firstNewBid.getBidListId()));
	}
}
