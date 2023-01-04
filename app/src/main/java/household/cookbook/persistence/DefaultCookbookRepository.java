package household.cookbook.persistence;

import household.cookbook.domain.Cookbook;
import household.cookbook.domain.CookbookRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
class DefaultCookbookRepository implements CookbookRepository {

	private final CookbookEntityRepository cookbookEntityRepository;
	private final CookbookMapper cookbookMapper;

	@Override
	public Cookbook determineCookbook(String cookbookId) {
		return cookbookMapper.map(cookbookEntityRepository.findById(cookbookId).orElseThrow(IllegalStateException::new));
	}

	@Override
	public Cookbook saveCookbook(Cookbook cookbook) {
		return cookbookMapper.map(cookbookEntityRepository.save(cookbookMapper.map(cookbook)));
	}

	@Override
	public Cookbook createCookbook() {
		return cookbookMapper.map(cookbookEntityRepository.save(new CookbookEntity()));
	}

    @Override
    public void deleteCookbook(String cookbookId) {
        cookbookEntityRepository.deleteById(cookbookId);
    }

}
