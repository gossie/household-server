package household.shoppinglist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ShoppingListItemDTO extends AbstractDTO {

    private Long databaseId;
	private String name;
	private boolean selected;
	private String image;

    public String getImage() {
        return image == null ? "" : image;
    }

    public boolean hasImage() {
        return StringUtils.hasText(image);
    }

    public ShoppingListItemDTO removeImage() {
        image = null;
        return this;
    }
}
