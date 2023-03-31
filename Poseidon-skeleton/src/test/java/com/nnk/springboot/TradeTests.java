package com.nnk.springboot;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;
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
