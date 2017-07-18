package household.shoppinglist;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor(access=AccessLevel.PACKAGE, force=true)
@AllArgsConstructor(access=AccessLevel.PACKAGE)
@Getter
@ToString
class ShoppingListEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
	private List<ShoppingListGroupEntity> shoppingListGroups = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
    @Deprecated private List<ShoppingListItemEntity> shoppingListItems = new ArrayList<>();

    
    @Deprecated
    public void addGroup(ShoppingListGroupEntity group) {
        shoppingListGroups.add(group);
    }


    @Deprecated
    public void clearItems() {
        shoppingListItems.clear();
    }
}
