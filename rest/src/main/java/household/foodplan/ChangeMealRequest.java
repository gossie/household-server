package household.foodplan;

import lombok.Data;

@Data
public class ChangeMealRequest {

    private Long recipeId;
    private MealDTO meal;

}