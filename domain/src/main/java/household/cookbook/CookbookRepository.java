package household.cookbook;

public interface CookbookRepository {

	Cookbook determineCookbook(Long cookbookId);

	Cookbook saveCookbook(Cookbook cookbook);

}
