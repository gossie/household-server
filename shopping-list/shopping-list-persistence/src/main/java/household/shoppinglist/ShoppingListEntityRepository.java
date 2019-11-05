package household.shoppinglist;

import org.springframework.data.jpa.repository.JpaRepository;

interface ShoppingListEntityRepository extends JpaRepository<ShoppingListEntity, Long> {

}
