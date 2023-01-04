package household.shoppinglist.persistence;

import household.shoppinglist.domain.ShoppingList;
import household.shoppinglist.domain.ShoppingListRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
class DefaultShoppingListRepository implements ShoppingListRepository {

	private final ShoppingListEntityRepository shoppingListEntityRepository;
	private final ShoppingListEntityMapper shoppingListMapper;

	@Override
	public ShoppingList determineShoppingList(String shoppingListId) {
		return shoppingListMapper.map(shoppingListEntityRepository.findById(shoppingListId).orElseThrow(IllegalStateException::new));
	}

	@Override
	public ShoppingList saveShoppingList(ShoppingList shoppingList) {
		return shoppingListMapper.map(shoppingListEntityRepository.save(shoppingListMapper.map(shoppingList)));
	}

	@Override
    public void deleteShoppingList(String shoppingListId) {
	    shoppingListEntityRepository.deleteById(shoppingListId);
    }

}
