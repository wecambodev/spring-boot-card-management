package com.mbanq.banksystem.repository;

import com.mbanq.banksystem.model.Card;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository {

    List<Card> findAllActiveByConsumerId(Long consumerId);
    Card findActiveById(Long id);
    Card findById(Long id);
    boolean dailyLimit(Card card);
    boolean activate(Long id);
    boolean deactivate(Long id);

}
