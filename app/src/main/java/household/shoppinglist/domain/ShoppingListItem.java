package household.shoppinglist.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
public class ShoppingListItem extends AbstractModel {

	private String name;
	private boolean selected;
	private byte[] image;

	public ShoppingListItem(String id, String name, boolean selected, byte[] image) {
		super(id);
		this.name = name;
		this.selected = selected;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
	    this.name = name;
    }

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}
