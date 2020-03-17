package household.shoppinglist;

import static household.shoppinglist.ShoppingListTOAssert.assertThat;
import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.Base64;
import java.util.List;

@WebFluxTest(controllers = ShoppingListController.class)
@Import({ ShoppingListGroupDTOMapper.class, ShoppingListItemDTOMapper.class, ShoppingListDTOMapper.class })
class ShoppingListControllerTest {

    @MockBean
    private ShoppingListService shoppingListService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testEditShoppingListItem() throws Exception {
        var image = "IMAGE".getBytes();

        when(shoppingListService.editItem(17L, 23L, 27L, new ShoppingListItem(null, "Paprika", false, image)))
            .thenReturn(new ShoppingList(17L, List.of(new ShoppingListGroup(23L, "Global", List.of(new ShoppingListItem(27L, "Paprika", false, image))))));

        webTestClient.put()
            .uri("/api/shoppingLists/17/shoppingListGroups/23/shoppingListItems/27")
            .contentType(MediaType.valueOf("application/vnd.household.v2+json"))
            .accept(MediaType.valueOf("application/vnd.household.v2+json"))
            .body(BodyInserters.fromValue(new ShoppingListItemDTO(null, "Paprika", false, "SU1BR0U=")))
            .exchange()
            .expectStatus()
            .is2xxSuccessful()
            .expectBody(ShoppingListDTO.class)
            .consumeWith(exchangeResult ->  assertThat(exchangeResult.getResponseBody())
                .hasSize(1)
                .shoppingListGroup(0, group -> group.hasName("Global")
                    .hasSize(1)
                    .shoppingListItem(0, item -> item.hasName("Paprika")
                        .isDeselected()
                        .hasImage("SU1BR0U=")
                        .hasLink("edit", "/api/shoppingLists/17/shoppingListGroups/23/shoppingListItems/27")
                    )
                )
            );
    }

    @Test
    void testEditShoppingListItem_withoutImage() throws Exception {
        when(shoppingListService.editItem(17L, 23L, 27L, new ShoppingListItem(null, "Paprika", false, new byte[] {})))
            .thenReturn(new ShoppingList(17L, List.of(new ShoppingListGroup(23L, "Global", List.of(new ShoppingListItem(27L, "Paprika", false, new byte[] {}))))));

        webTestClient.put()
            .uri("/api/shoppingLists/17/shoppingListGroups/23/shoppingListItems/27")
            .contentType(MediaType.valueOf("application/vnd.household.v2+json"))
            .accept(MediaType.valueOf("application/vnd.household.v2+json"))
            .body(BodyInserters.fromValue(new ShoppingListItemDTO(null, "Paprika", false, null)))
            .exchange()
            .expectStatus()
            .is2xxSuccessful()
            .expectBody(ShoppingListDTO.class)
            .consumeWith(exchangeResult ->  assertThat(exchangeResult.getResponseBody())
                .hasSize(1)
                .shoppingListGroup(0, group -> group.hasName("Global")
                    .hasSize(1)
                    .shoppingListItem(0, item -> item.hasName("Paprika")
                        .isDeselected()
                        .hasImage("")
                        .hasLink("edit", "/api/shoppingLists/17/shoppingListGroups/23/shoppingListItems/27")
                    )
                )
            );
    }
}
