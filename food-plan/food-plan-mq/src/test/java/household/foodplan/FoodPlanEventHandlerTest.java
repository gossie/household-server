package household.foodplan;

import com.google.common.eventbus.EventBus;
import household.household.HouseholdDeletedEvent;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class FoodPlanEventHandlerTest {

    private FoodPlanEventHandler shoppingListEventHandler;

    @Test
    public void testOnHouseholdDeleted() throws Exception {
        var foodPlanService = mock(FoodPlanService.class);

        shoppingListEventHandler = new FoodPlanEventHandler(mock(EventBus.class), foodPlanService);
        shoppingListEventHandler.onHouseholdDeleted(new HouseholdDeletedEvent(1L, 2L, 3L, 4L, 5L));

        verify(foodPlanService).deleteFoodPlan(4L);
    }
}
