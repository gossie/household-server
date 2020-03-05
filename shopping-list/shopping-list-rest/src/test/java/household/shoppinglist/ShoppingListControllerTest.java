package household.shoppinglist;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.Collections;

import static household.shoppinglist.ShoppingListTOAssert.assertThat;
import static org.mockito.Mockito.when;

//@SpringBootTest(classes = { ShoppingListController.class, ShoppingListGroupDTOMapper.class, ShoppingListItemDTOMapper.class, ShoppingListDTOMapper.class })
//@AutoConfigureWebTestClient
@WebFluxTest(controllers = ShoppingListController.class)
@Import({ ShoppingListGroupDTOMapper.class, ShoppingListItemDTOMapper.class, ShoppingListDTOMapper.class })
class ShoppingListControllerTest {

    @MockBean
    private ShoppingListService shoppingListService;

    @Autowired
    private WebTestClient webTestClient/* = WebTestClient.bindToServer().build()*/;

    @Test
    void testEditShoppingListItem() throws Exception {
        when(shoppingListService.editItem(17L, 23L, new ShoppingListItem(null, "Paprika", false)))
            .thenReturn(new ShoppingList(17L));

        webTestClient.put()
            .uri("/api/shoppingLists/17/shoppingListGroups/23/shoppingListItems/27")
            .contentType(MediaType.valueOf("application/vnd.household.v2+json"))
            .accept(MediaType.valueOf("application/vnd.household.v2+json"))
            .body(BodyInserters.fromValue(new ShoppingListItemDTO(null, "Paprika", false)))
            .exchange()
            .expectStatus()
            .is2xxSuccessful()
            .expectBody(ShoppingListDTO.class)
            .consumeWith(exchangeResult -> {
                assertThat(exchangeResult.getResponseBody())
                    .hasSize(1)
                    .shoppingListGroup(0, group -> group.hasName("Global").hasSize(0));
            });
    }
}
