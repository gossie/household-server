package household.foodplan.rest;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access=AccessLevel.PACKAGE)
@NoArgsConstructor(access=AccessLevel.PACKAGE)
@Getter
public class MealDTO extends AbstractDTO {

    private String databaseId;
    private String name;
    @JsonIgnore
    private String cookbookId;
    @JsonIgnore
    private String recipeId;

    public Optional<String> getCookbookId() {
        return Optional.ofNullable(cookbookId);
    }

    public Optional<String> getRecipeId() {
        return Optional.ofNullable(recipeId);
    }
}
