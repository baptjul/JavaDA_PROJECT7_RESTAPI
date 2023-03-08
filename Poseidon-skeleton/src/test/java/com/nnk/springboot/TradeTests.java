package com.nnk.springboot;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.RuleNameService;
import com.nnk.springboot.services.TradeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TradeTests {

	@Autowired
	private TradeService tradeService;
	@Autowired
	private TradeRepository tradeRepository;

	@BeforeEach
	void setup() {
		tradeRepository.deleteAll();
		tradeRepository.flush();
	}

//	@Test
//	public void tradeTest() {
//		Trade trade = new Trade("Trade Account", "Type");
//
//		// Save
//		trade = tradeRepository.save(trade);
//		Assert.assertNotNull(trade.getTradeId());
//		Assert.assertTrue(trade.getAccount().equals("Trade Account"));
//
//		// Update
//		trade.setAccount("Trade Account Update");
//		trade = tradeRepository.save(trade);
//		Assert.assertTrue(trade.getAccount().equals("Trade Account Update"));
//
//		// Find
//		List<Trade> listResult = tradeRepository.findAll();
//		Assert.assertTrue(listResult.size() > 0);
//
//		// Delete
//		Integer id = trade.getTradeId();
//		tradeRepository.delete(trade);
//		Optional<Trade> tradeList = tradeRepository.findById(id);
//		Assert.assertFalse(tradeList.isPresent());
//	}

	@Test
	public void saveTradeTest() {
		Trade trade = new Trade();
		trade.setAccount("Trade Account");
		trade.setType("Type");

		Trade newTrade = tradeService.saveTrade(trade);

		assertNotNull(newTrade.getTradeId());
		assertEquals("Trade Account", newTrade.getAccount());
	}

	@Test
	public void updateTradeTest() throws IllegalAccessException {
		Trade trade = new Trade();
		trade.setAccount("Trade Account");
		trade.setType("Type");

		Trade newTrade = tradeService.saveTrade(trade);

		Trade tradeUpdate = new Trade();
		tradeUpdate.setAccount("Trade Account Update");
		tradeUpdate.setType("Type");

		Trade updateTrade = tradeService.updateTrade(newTrade.getTradeId(), tradeUpdate);

		assertNotNull(updateTrade.getTradeId());
		assertEquals("Trade Account Update", updateTrade.getAccount());
	}

	@Test
	public void getAllTradesTest() {
		Trade firstTrade = new Trade();
		firstTrade.setAccount("Trade Account");
		firstTrade.setType("Type");
		tradeService.saveTrade(firstTrade);

		Trade secondTrade = new Trade();
		secondTrade.setAccount("Second Trade Account");
		secondTrade.setType("Type");
		tradeService.saveTrade(secondTrade);

		assertTrue(tradeService.getAllTrades().size() > 0);
	}

	@Test
	public void deleteTradeTest() {
		Trade firstTrade = new Trade();
		firstTrade.setAccount("Trade Account");
		firstTrade.setType("Type");
		Trade firstNewTrade = tradeService.saveTrade(firstTrade);

		Trade secondTrade = new Trade();
		secondTrade.setAccount("Second Trade Account");
		secondTrade.setType("Type");
		tradeService.saveTrade(secondTrade);

		tradeService.deleteTrade(firstNewTrade.getTradeId());

		assertNull(tradeService.getTradeById(firstNewTrade.getTradeId()));
	}
}
