package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Service
public class BidListService {

    @Autowired
    private BidListRepository bidListRepository;

    public List<BidList> getAllBids() {
        return bidListRepository.findAll();
    }

    public BidList getBidById(Integer id) {
        return bidListRepository.findById(id).orElse(null);
    }

    public List<BidList> validate(BidList bid) {
        bidListRepository.save(bid);
        return bidListRepository.findAll();
    }

    public BidList addBidForm(BidList bid) {
        if  (bid != null && bid.getAccount() != null && bid.getType() != null) {
            return bidListRepository.save(bid);
        } else {
            return bid;
        }
    }

    public BidList updateBid(Integer id, BidList bid) throws IllegalAccessException {
        BidList bidTarget = getBidById(id);
        if  (bid != null && bid.getAccount() != null && bid.getType() != null) {
            Field[] fields = BidList.class.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                Object newValue = field.get(bid);
                if (newValue != null) {
                    field.set(bidTarget, newValue);
                }
            }

            return bidListRepository.save(bidTarget);
        } else {
            return bid;
        }
    }

    public List<BidList> deleteBid(Integer id) {
        BidList targetedBid = getBidById(id);
        if (targetedBid != null) {
            bidListRepository.deleteById(id);
        }
        return getAllBids();
    }
}
