package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Service
public class TradeService {
    @Autowired
    private TradeRepository tradeRepository;

    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }

    public Trade getTradeById(Integer id) {
        return tradeRepository.findById(id).orElse(null);
    }

    public Trade saveTrade(Trade trade) {
        return tradeRepository.save(trade);
    }

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

    public List<Trade> deleteTrade(Integer id) {
        Trade targetedTrade = getTradeById(id);
        if (targetedTrade != null) {
            tradeRepository.deleteById(id);
        }
        return getAllTrades();
    }
}
