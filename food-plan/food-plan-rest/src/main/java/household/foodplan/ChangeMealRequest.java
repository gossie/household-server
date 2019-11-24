package household.foodplan;

import lombok.Data;

@Data
public class ChangeMealRequest {

    private Long cookbookId;
    private Long recipeId;
    private MealDTO meal;

}