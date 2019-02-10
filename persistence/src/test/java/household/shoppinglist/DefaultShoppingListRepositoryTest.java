package household.shoppinglist;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class DefaultShoppingListRepositoryTest {

    @Test
    public void testDeleteCookbook() {
        ShoppingListEntityRepository shoppingListEntityRepository = mock(ShoppingListEntityRepository.class);

        ShoppingListRepository shoppingListRepository = new DefaultShoppingListRepository(shoppingListEntityRepository, null);
        shoppingListRepository.deleteShoppingList(6L);

        verify(shoppingListEntityRepository).delete(6L);
    }

}
