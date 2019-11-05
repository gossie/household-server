package household.shoppinglist;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
class DefaultShoppingListRepository implements ShoppingListRepository {

	private final ShoppingListEntityRepository shoppingListEntityRepository;
	private final ShoppingListEntityMapper shoppingListMapper;

	@Override
	public ShoppingList determineShoppingList(Long shoppingListId) {
		return shoppingListMapper.map(shoppingListEntityRepository.findById(shoppingListId).orElseThrow(IllegalStateException::new));
	}

	@Override
	public ShoppingList saveShoppingList(ShoppingList shoppingList) {
		return shoppingListMapper.map(shoppingListEntityRepository.save(shoppingListMapper.map(shoppingList)));
	}

	@Override
    public void deleteShoppingList(Long shoppingListId) {
	    shoppingListEntityRepository.deleteById(shoppingListId);
    }

}
