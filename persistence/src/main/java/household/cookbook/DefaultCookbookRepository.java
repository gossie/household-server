package household.cookbook;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
class DefaultCookbookRepository implements CookbookRepository {

	private final CookbookEntityRepository cookbookEntityRepository;
	private final CookbookMapper cookbookMapper;
	
	@Override
	public Cookbook determineCookbook(Long cookbookId) {
		return cookbookMapper.map(cookbookEntityRepository.findOne(cookbookId));
	}

	@Override
	public Cookbook saveCookbook(Cookbook cookbook) {
		return cookbookMapper.map(cookbookEntityRepository.save(cookbookMapper.map(cookbook)));
	}

	@Override
	public Cookbook createCookbook() {
		return cookbookMapper.map(cookbookEntityRepository.save(new CookbookEntity()));
	}

}
