package household.shoppinglist;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/migrations")
@RequiredArgsConstructor
@Deprecated
public class MigrationController {

    private final ShoppingListEntityRepository shoppingListEntityRepository;
    
    @PostMapping(path="/migrate")
    @ResponseStatus(value=HttpStatus.OK)
    public void migrate() {
        List<ShoppingListEntity> all = shoppingListEntityRepository.findAll().stream().map(shoppingList -> {
            List<ShoppingListGroupEntity> shoppingListGroups = shoppingList.getShoppingListGroups();
            if(shoppingListGroups.isEmpty()) {
                ShoppingListGroupEntity groupToUse = new ShoppingListGroupEntity(null, "Global", shoppingList.getShoppingListItems());
                shoppingList.addGroup(groupToUse);
            } else {
                ShoppingListGroupEntity groupToUse = shoppingListGroups.get(0);
                groupToUse.setShoppingListItems(shoppingList.getShoppingListItems());
            }
            shoppingList.clearItems();
            return shoppingList;
        }).collect(Collectors.toList());
        
        shoppingListEntityRepository.save(all);
    }
}
