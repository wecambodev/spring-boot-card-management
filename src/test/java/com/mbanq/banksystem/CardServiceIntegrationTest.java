package com.mbanq.banksystem;
import com.mbanq.banksystem.model.Card;
import com.mbanq.banksystem.service.CardService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CardServiceIntegrationTest {

    @Autowired
    CardService cardService;


    private static final  Long consumerID = 3L;
    private static final  Long cardID     = 5L;


    @Before
    public void setUp() {
        assertThat(cardService).isNotNull();
    }


    @Test
    public void whenFindByIdShouldBeReturnTheSameId() {
        Long  id = 1L;
        Card found = cardService.findById(id);
        assertThat(id)
                .isEqualTo(found.getId());
    }


    @Test
    public void whenFindAllCardsByConsumerIdShouldBeReturnAllCardsOfThatConsumer() {

        List<Card> cards  = cardService.findAllActiveByConsumerId(consumerID);

        assertThat(consumerID).isEqualTo(cards.get(0).getConsumer().getId());
    }


    @Test
    public void whenDeactivateCardItWillDeactivate() {


        assertThat(cardService.deactivate(cardID)).isEqualTo(true);

    }


    @Test
    public void whenActivateCardItWillDeactivate() {


        assertThat(cardService.activate(cardID)).isEqualTo(true);

    }


    @Test

    public void whenChangeCardDailyLimitItShouldBeReturnTrue(){


        Card card = cardService.findById(cardID);
        card.setDailyLimit(1000.00);
        assertThat(cardService.changeDailyLimit(card)).isEqualTo(true);

    }








}
