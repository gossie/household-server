package household.foodplan;

import lombok.Data;

@Data
public class ChangeMealRequest {

    private String cookbookId;
    private String recipeId;
    private MealDTO meal;

}