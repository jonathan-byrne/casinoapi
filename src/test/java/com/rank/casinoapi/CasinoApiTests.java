package com.rank.casinoapi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rank.casinoapi.config.CasinoApiTestConfiguration;
import com.rank.casinoapi.dto.response.GetLastTransactionsResponse;
import com.rank.casinoapi.entity.Player;
import com.rank.casinoapi.repository.PlayerRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CasinoApiTestConfiguration.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class CasinoApiTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    PlayerRepository playerRepository;

    @Test
    public void givenPlayerId_whenGetValidPlayerBalance_thenStatus200() throws Exception {

        Player player = new Player("Mark", 100.0);
        playerRepository.save(player);

        mvc.perform(get("/casino/player/" + player.getId() + "/balance")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content()
        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void givenPlayerId_whenGetInvalidPlayerBalance_thenStatus400() throws Exception {
        mvc.perform(get("/casino/player/-1/balance") // -1 used as invalid playerId
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenPlayerIdTransactionTypeAndAmount_whenPost_thenUpdatePlayerBalance() throws Exception {

        Player player = new Player("Andrew", 560.0);
        playerRepository.save(player);

        postPlayerBalanceUpdate(player, "WIN", 34.7).andExpect(status().isOk());
    }

    @Test
    public void givenPlayerUsername_whenPost_getLast10Transactions() throws Exception {

        Player player = new Player("Chris", 90.09);
        playerRepository.save(player);

        postPlayerBalanceUpdate(player, "WIN", 34.7);
        postPlayerBalanceUpdate(player, "WAGER", 34.7);
        postPlayerBalanceUpdate(player, "WIN", 34.7);
        postPlayerBalanceUpdate(player, "WAGER", 90.0);
        postPlayerBalanceUpdate(player, "WIN", 34.7);

        postPlayerBalanceUpdate(player, "WIN", 34.7);
        postPlayerBalanceUpdate(player, "WIN", 34.7);
        postPlayerBalanceUpdate(player, "WAGER", 12.7);
        postPlayerBalanceUpdate(player, "WIN", 34.7);
        postPlayerBalanceUpdate(player, "WIN", 29.0);

        postPlayerBalanceUpdate(player, "WIN", 34.7);
        postPlayerBalanceUpdate(player, "WAGER", 100.0);
        postPlayerBalanceUpdate(player, "WIN", 34.7);
        postPlayerBalanceUpdate(player, "WIN", 34.7);

        String postResponse = mvc.perform(post("/casino/admin/player/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"username\" : \"" + player.getUsername() + "\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        Type listOfMyClassObject = new TypeToken<ArrayList<GetLastTransactionsResponse>>() {}.getType();

        Gson gson = new Gson();
        List<GetLastTransactionsResponse> outputList = gson.fromJson(postResponse, listOfMyClassObject);

        Assert.assertTrue(outputList.size() <= 10);

        if(outputList.size() > 0) {

            for(GetLastTransactionsResponse transactionsResponse : outputList) {
                Assert.assertTrue(transactionsResponse.getAmount() >= 0.0);
                Assert.assertTrue(transactionsResponse.getTransactionType().equalsIgnoreCase("WIN") ||
                        transactionsResponse.getTransactionType().equalsIgnoreCase("WAGER"));
            }
        }
    }

    public ResultActions postPlayerBalanceUpdate(Player player, String transactionType, Double amount) throws Exception {
        return mvc.perform(post("/casino/player/" + player.getId() + "/balance/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"playerId\" : \"" + player.getId() + "\"," +
                                " \"transactionType\" : \"" + transactionType + "\"," +
                                " \"amount\" : " + amount + " }")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
