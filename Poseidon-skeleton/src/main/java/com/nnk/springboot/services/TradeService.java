package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Service class for managing trades operations
 */
@Service
public class TradeService {
    @Autowired
    private TradeRepository tradeRepository;

    /**
     * Gets a list of all trades
     * @return the list of trades
     */
    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }

    /**
     * Gets a trade
     * @param id the ID of the trade
     * @return a single trade or null
     */
    public Trade getTradeById(Integer id) {
        return tradeRepository.findById(id).orElse(null);
    }

    /**
     * Adds a new trade
     * @param trade trade to add
     * @return the added trade
     */
    public Trade saveTrade(Trade trade) {
        return tradeRepository.save(trade);
    }

    /**
     * Updates a trade
     * @param id the ID of the trade
     * @param updatedTrade the updated trade
     * @return the updated user
     * @throws IllegalAccessException if updatedTrade is null
     */
    public Trade updateTrade(Integer id, Trade updatedTrade) throws IllegalAccessException {
        Trade targetedTrade = getTradeById(id);

        if (updatedTrade != null) {
            Field[] fields = Trade.class.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                Object newValue = field.get(updatedTrade);
                if (newValue != null) {
                    field.set(targetedTrade, newValue);
                }
            }

            return tradeRepository.save(targetedTrade);
        } else {
            return updatedTrade;
        }
    }

    /**
     * Deletes a trade
     * @param id the ID of the trade
     * @return the list of all trades
     */
    public List<Trade> deleteTrade(Integer id) {
        Trade targetedTrade = getTradeById(id);
        if (targetedTrade != null) {
            tradeRepository.deleteById(id);
        }
        return getAllTrades();
    }
}
