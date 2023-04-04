package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Service class for managing bidList operations
 */
@Service
public class BidListService {

    @Autowired
    private BidListRepository bidListRepository;

    /**
     * Gets a list of all bids
     * @return the list of bids
     */
    public List<BidList> getAllBids() {
        return bidListRepository.findAll();
    }

    /**
     * Gets a bid
     * @param id the ID of the bid
     * @return a single bid or null
     */
    public BidList getBidById(Integer id) {
        return bidListRepository.findById(id).orElse(null);
    }

    /**
     * Adds a new bid
     * @param bid bid to add
     * @return the new bid
     */
    public BidList addBidForm(BidList bid) {
        if (bid != null && bid.getAccount() != null && bid.getType() != null) {
            return bidListRepository.save(bid);
        } else {
            return bid;
        }
    }

    /**
     * Updates a bid
     * @param id the ID of the bid
     * @param updatedBid the updated bid
     * @return the updated bid
     * @throws IllegalAccessException if updatedBid is null
     */
    public BidList updateBid(Integer id, BidList updatedBid) throws IllegalAccessException {
        BidList bidTarget = getBidById(id);
        if (updatedBid != null && updatedBid.getAccount() != null && updatedBid.getType() != null) {
            Field[] fields = BidList.class.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                Object newValue = field.get(updatedBid);
                if (newValue != null) {
                    field.set(bidTarget, newValue);
                }
            }

            return bidListRepository.save(bidTarget);
        } else {
            return updatedBid;
        }
    }

    /**
     * Deletes a bid
     * @param id the ID of the bid
     * @return the list of all bids
     */
    public List<BidList> deleteBid(Integer id) {
        BidList targetedBid = getBidById(id);
        if (targetedBid != null) {
            bidListRepository.deleteById(id);
        }
        return getAllBids();
    }
}
