package household.shoppinglist.persistence;

import static household.shoppinglist.domain.ShoppingListAssert.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import household.shoppinglist.domain.ShoppingList;
import household.shoppinglist.domain.ShoppingListGroup;
import household.shoppinglist.domain.ShoppingListItem;

import java.util.List;

@DataJpaTest
@Import(ShoppingListPersistenceContext.class)
public class DefaultShoppingListRepositoryTest {

    @Autowired
    private DefaultShoppingListRepository shoppingListRepository;

    @Test
    public void testPersitence() {
        var shoppingList = new ShoppingList(
            null,
            List.of(new ShoppingListGroup(
                null, "Gruppe 1", List.of(new ShoppingListItem(null, "Paprika", false, null), new ShoppingListItem(null, "Joghurt", true, "image".getBytes()))
            ))
        );

        var shoppingListId = shoppingListRepository.saveShoppingList(shoppingList).getId();

        assertThat(shoppingListRepository.determineShoppingList(shoppingListId))
            .hasId(shoppingListId)
            .hasSize(1)
            .shoppingListGroup(0, group -> group
                .hasName("Gruppe 1")
                .hasSize(2)
                .shoppingListItem(0, item -> item
                    .hasName("Paprika")
                    .hasNoImage()
                    .isDeselected())
                .shoppingListItem(1, item -> item
                    .hasName("Joghurt")
                    .hasImage("image".getBytes())
                    .isSelected()
                ));

        shoppingListRepository.deleteShoppingList(shoppingListId);

        Assertions.assertThatExceptionOfType(IllegalStateException.class)
            .isThrownBy(() -> shoppingListRepository.determineShoppingList(shoppingListId))
            .withNoCause();
    }

}
