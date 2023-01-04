package household.shoppinglist.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
class ShoppingListDTOMapper {

	private final ShoppingListGroupDTOMapper shoppingListGroupMapper;

	ShoppingListDTO map(ShoppingList shoppingList) {
		List<ShoppingListGroupDTO> items = shoppingList.getShoppingListGroups().stream()
				.map(shoppingListGroupMapper::map)
				.collect(Collectors.toList());

		return new ShoppingListDTO(shoppingList.getId(), items);
	}
}
