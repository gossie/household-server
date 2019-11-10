package household.foodplan;

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

    private Long databaseId;
    private String name;
    @JsonIgnore
    private Long cookbookId;
    @JsonIgnore
    private Long recipeId;

    public Optional<Long> getCookbookId() {
        return Optional.ofNullable(cookbookId);
    }

    public Optional<Long> getRecipeId() {
        return Optional.ofNullable(recipeId);
    }
}
