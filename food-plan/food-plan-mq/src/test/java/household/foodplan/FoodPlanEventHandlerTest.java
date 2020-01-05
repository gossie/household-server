package household.foodplan;

import com.google.common.eventbus.EventBus;
import household.household.Household;
import household.household.HouseholdDeletedEvent;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class FoodPlanEventHandlerTest {

    private FoodPlanEventHandler shoppingListEventHandler;

    @Test
    public void testOnHouseholdDeleted() throws Exception {
        var shoppingListService = mock(FoodPlanService.class);
        var household = mock(Household.class);
        when(household.getShoppingListId()).thenReturn(2L);

        shoppingListEventHandler = new FoodPlanEventHandler(mock(EventBus.class), shoppingListService);
        shoppingListEventHandler.onHouseholdDeleted(new HouseholdDeletedEvent(household));

        verify(shoppingListService).deleteFoodPlan(2L);
    }
}
