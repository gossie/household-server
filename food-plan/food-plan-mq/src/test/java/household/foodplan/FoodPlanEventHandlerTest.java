package household.foodplan;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class FoodPlanEventHandlerTest {

    private FoodPlanEventHandler shoppingListEventHandler;

    @Test
    public void testOnHouseholdDeleted() throws Exception {
        var shoppingListService = mock(FoodPlanService.class);

        shoppingListEventHandler = new FoodPlanEventHandler(shoppingListService);
        shoppingListEventHandler.onHouseholdDeleted(new HouseholdDeletedEvent(2L));

        verify(shoppingListService).deleteFoodPlan(2L);
    }
}
