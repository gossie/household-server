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
        var foodPlanService = mock(FoodPlanService.class);
        var household = mock(Household.class);
		when(household.getId()).thenReturn(1L);
		when(household.getShoppingListId()).thenReturn(2L);
		when(household.getCleaningPlanId()).thenReturn(3L);
		when(household.getFoodPlanId()).thenReturn(4L);
		when(household.getCookbookId()).thenReturn(5L);

        shoppingListEventHandler = new FoodPlanEventHandler(mock(EventBus.class), foodPlanService);
        shoppingListEventHandler.onHouseholdDeleted(new HouseholdDeletedEvent(1L, 2L, 3L, 4L, 5L));

        verify(foodPlanService).deleteFoodPlan(4L);
    }
}
