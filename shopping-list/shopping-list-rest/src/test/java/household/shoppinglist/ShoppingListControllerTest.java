package household.shoppinglist;

import static household.shoppinglist.ShoppingListTOAssert.assertThat;
import static org.mockito.Mockito.when;

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
        var base64Image = "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";
        var scaledBase64Image = "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";
        byte[] scaledRawImage = Base64.getDecoder().decode(scaledBase64Image);

        when(shoppingListService.editItem(17L, 23L, 27L, new ShoppingListItem(null, "Paprika", false, scaledRawImage)))
            .thenReturn(new ShoppingList(17L, List.of(new ShoppingListGroup(23L, "Global", List.of(new ShoppingListItem(27L, "Paprika", false, scaledRawImage))))));

        webTestClient.put()
            .uri("/api/shoppingLists/17/shoppingListGroups/23/shoppingListItems/27")
            .contentType(MediaType.valueOf("application/vnd.household.v2+json"))
            .accept(MediaType.valueOf("application/vnd.household.v2+json"))
            .body(BodyInserters.fromValue(new ShoppingListItemDTO(null, "Paprika", false, base64Image)))
            .exchange()
            .expectStatus()
            .is2xxSuccessful()
            .expectBody(ShoppingListDTO.class)
            .consumeWith(exchangeResult ->  assertThat(exchangeResult.getResponseBody())
                .hasSize(1)
                .shoppingListGroup(0, group -> group
                    .hasName("Global")
                    .hasSize(1)
                    .shoppingListItem(0, item -> item
                        .hasName("Paprika")
                        .isDeselected()
                        .imageFieldIsEmpty()
                        .hasLink("edit", "/api/shoppingLists/17/shoppingListGroups/23/shoppingListItems/27")
                        .hasLink("image", "/api/shoppingLists/17/shoppingListGroups/23/shoppingListItems/27")
                    )
                )
            );
    }

    @Test
    void testEditShoppingListItem_withoutImage() throws Exception {
        when(shoppingListService.editItem(17L, 23L, 27L, new ShoppingListItem(null, "Paprika", false, null)))
            .thenReturn(new ShoppingList(17L, List.of(new ShoppingListGroup(23L, "Global", List.of(new ShoppingListItem(27L, "Paprika", false, null))))));

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
                .shoppingListGroup(0, group -> group
                    .hasName("Global")
                    .hasSize(1)
                    .shoppingListItem(0, item -> item
                        .hasName("Paprika")
                        .isDeselected()
                        .imageFieldIsEmpty()
                        .hasLink("edit", "/api/shoppingLists/17/shoppingListGroups/23/shoppingListItems/27")
                        .hasNoLinkForRel("image")
                    )
                )
            );
    }

    @Test
    void testRetrieveImage() {
        when(shoppingListService.getShoppingList(17L))
            .thenReturn(new ShoppingList(17L, List.of(new ShoppingListGroup(23L, "Global", List.of(new ShoppingListItem(27L, "Paprika", false, "IMAGE".getBytes()))))));

        webTestClient.get()
            .uri("/api/shoppingLists/17/shoppingListGroups/23/shoppingListItems/27")
            .accept(MediaType.IMAGE_PNG)
            .exchange()
            .expectStatus()
            .is2xxSuccessful()
            .expectBody(byte[].class)
            .isEqualTo("IMAGE".getBytes());
    }

}
