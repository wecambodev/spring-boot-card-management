package com.mbanq.banksystem.service;
import com.mbanq.banksystem.model.Card;
import com.mbanq.banksystem.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public List<Card> findAllActiveByConsumerId(Long consumerId){
        return cardRepository.findAllActiveByConsumerId(consumerId);
    }

    public Card findById(Long id) {
        return cardRepository.findById(id);
    }

    public Card findActiveById(Long id){
        return cardRepository.findActiveById(id);
    }

    public boolean changeDailyLimit(Card card) {
        return cardRepository.changeDailyLimit(card);
    }
    public boolean activate(Long id) {
        return cardRepository.activate(id);
    }
    public boolean deactivate(Long id) {
        return cardRepository.deactivate(id);
    }

}
