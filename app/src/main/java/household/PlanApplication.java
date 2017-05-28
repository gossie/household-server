package household;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.config.EnableEntityLinks;

import household.cleaningplan.ChoreEntity;
import household.cleaningplan.CleaningPlanEntity;
import household.cleaningplan.CleaningPlanRepository;
import household.cleaningplan.RepeatEntity;
import household.cleaningplan.TimeUnit;
import household.cookbook.CookbookEntity;
import household.cookbook.CookbookRepository;
import household.cookbook.IngredientEntity;
import household.cookbook.RecipeEntity;
import household.foodplan.FoodPlanEntity;
import household.foodplan.FoodPlanRepository;
import household.foodplan.MealEntity;
import household.household.HouseholdRepository;
import household.shoppinglist.ShoppingListEntity;
import household.shoppinglist.ShoppingListItemEntity;
import household.shoppinglist.ShoppingListRepository;
import household.shoppinglist.ShoppingListService;

@SpringBootApplication
@EnableEntityLinks
public class PlanApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanApplication.class, args);
	}
	
	@Bean
	public ShoppingListService ShoppingListService() {
		return new ShoppingListService(shoppingListRepository);
	}
	
	
	@Bean
	public CommandLineRunner householdInitializer(HouseholdRepository householdRepository, ShoppingListRepository shoppingListRepository, CleaningPlanRepository cleaningPlanRepository, FoodPlanRepository foodPlanRepository, CookbookRepository cookbookRepository) {
		return args -> {
//			initializeShoppingList(shoppingListRepository);
//			initializeCleaningPlan(cleaningPlanRepository);
//			initializeFoodStuff(cookbookRepository, foodPlanRepository);
//			
//			FoodPlan foodPlan = foodPlanRepository.findOne(1L);
//			CleaningPlan cleaningPlan = cleaningPlanRepository.findOne(1L);
//			ShoppingList shoppingList = shoppingListRepository.findOne(1L);
//			Cookbook cookbook = cookbookRepository.findOne(1L);
//			Household household = new Household(shoppingList, cleaningPlan, foodPlan, cookbook);
//			
//			householdRepository.save(household);
		};
	}

	private void initializeShoppingList(ShoppingListRepository shoppingListRepository) {
		ShoppingListItemEntity item1 = new ShoppingListItemEntity("Grillgut", false);
		ShoppingListItemEntity item2 = new ShoppingListItemEntity("Duschgel", false);
		ShoppingListItemEntity item3 = new ShoppingListItemEntity("Knoblauchzehen", false);
		ShoppingListItemEntity item4 = new ShoppingListItemEntity("Koriander (Gewürz)", true);
		ShoppingListItemEntity item5 = new ShoppingListItemEntity("Ingwer", false);
		ShoppingListItemEntity item6 = new ShoppingListItemEntity("Tomaten", false);
		ShoppingListItemEntity item7 = new ShoppingListItemEntity("Cream of coconut", false);
		ShoppingListItemEntity item8 = new ShoppingListItemEntity("Blattspinat", false);
		ShoppingListItemEntity item9 = new ShoppingListItemEntity("Zitronen", false);
		ShoppingListItemEntity item10 = new ShoppingListItemEntity("Kohlensäure", false);
		ShoppingListEntity shoppingList = new ShoppingListEntity(asList(item1, item2, item3, item4, item5, item6, item7, item8, item9, item10));
		shoppingListRepository.save(shoppingList);
	}
	
	@SuppressWarnings("deprecation")
	public void initializeCleaningPlan(CleaningPlanRepository cleaningPlanRepository) {
		ChoreEntity item1 = new ChoreEntity("Bett beziehen", new Date(117, 2, 31).getTime(), new RepeatEntity(2, TimeUnit.WEEKS));
		ChoreEntity item2 = new ChoreEntity("Gelber Sack", new Date(117, 2, 30).getTime(), new RepeatEntity(1, TimeUnit.WEEKS));
		ChoreEntity item3 = new ChoreEntity("Staubsaugen", new Date(117, 2, 31).getTime(), new RepeatEntity(1, TimeUnit.WEEKS));
		ChoreEntity item4 = new ChoreEntity("Bad putzen", new Date(117, 2, 31).getTime(), new RepeatEntity(1, TimeUnit.WEEKS));
		ChoreEntity item5 = new ChoreEntity("Wäsche waschen", new Date(117, 2, 31).getTime(), new RepeatEntity(84, TimeUnit.HOURS));
		ChoreEntity item6 = new ChoreEntity("Boden wischen", new Date(117, 2, 10).getTime(), new RepeatEntity(1, TimeUnit.WEEKS));
		ChoreEntity item7 = new ChoreEntity("Staub wischen", new Date(117, 2, 31).getTime(), new RepeatEntity(84, TimeUnit.HOURS));
		ChoreEntity item8 = new ChoreEntity("Küche putzen", new Date(117, 2, 31).getTime(), new RepeatEntity(1, TimeUnit.WEEKS));
		ChoreEntity item9 = new ChoreEntity("Müll", new Date(117, 3, 1).getTime(), new RepeatEntity(2, TimeUnit.WEEKS));
		ChoreEntity item10 = new ChoreEntity("Klo putzen", new Date(117, 2, 31).getTime(), new RepeatEntity(1, TimeUnit.WEEKS));
		ChoreEntity item11 = new ChoreEntity("Blumen gießen", new Date(117, 3, 1).getTime(), new RepeatEntity(3, TimeUnit.DAYS));
		CleaningPlanEntity cleaningPlan = new CleaningPlanEntity(asList(item1, item2, item3, item4, item5, item6, item7, item8, item9, item10, item11));
		cleaningPlanRepository.saveCleaningPlan(cleaningPlan);
	}
	
	public void initializeFoodStuff(CookbookRepository cookbookRepository, FoodPlanRepository foodPlanRepository) {
		initializeCookbook(cookbookRepository);
		initializeFoodplan(foodPlanRepository, cookbookRepository);
	}
	
	private void initializeCookbook(CookbookRepository cookbookRepository) {
		List<RecipeEntity> recipes = new ArrayList<>();
		
		List<IngredientEntity> chiliIngredients = new ArrayList<>();
		chiliIngredients.add(new IngredientEntity(125.0, "g", "Sojagranulat"));
		chiliIngredients.add(new IngredientEntity(200.0, "g", "Möhren"));
		chiliIngredients.add(new IngredientEntity(1.0, "", "Paprikaschote"));
		chiliIngredients.add(new IngredientEntity(1.0, "", "Zucchini"));
		chiliIngredients.add(new IngredientEntity(5.0, "TL", "Tomatenmark"));
		chiliIngredients.add(new IngredientEntity(1.0, "Dose", "Kidneybohnen"));
		chiliIngredients.add(new IngredientEntity(1.0, "Dose", "Mais"));
		chiliIngredients.add(new IngredientEntity(500.0, "ml", "Gemüsebrühe"));
		recipes.add(new RecipeEntity("Chili sin carne", "", chiliIngredients));
		
		List<IngredientEntity> curryIngredients = new ArrayList<>();
		recipes.add(new RecipeEntity("Curry", "", curryIngredients));
		
		List<IngredientEntity> spaghettiIngredients = new ArrayList<>();
		spaghettiIngredients.add(new IngredientEntity(500.0, "g", "Pilze"));
		spaghettiIngredients.add(new IngredientEntity(2.0, "", "Möhren"));
		spaghettiIngredients.add(new IngredientEntity(2.0, "", "Selleriestangen"));
		spaghettiIngredients.add(new IngredientEntity(2.0, "Dosen", "gehackte Tomten"));
		spaghettiIngredients.add(new IngredientEntity(1.0, "Esslöffel", "Tomatenmark"));
		spaghettiIngredients.add(new IngredientEntity(500, "g", "Pasta"));
		recipes.add(new RecipeEntity("Spaghetti Bolognese", "", spaghettiIngredients));
		
		recipes.add(new RecipeEntity("Lasagne", "", Collections.emptyList()));
		
		List<IngredientEntity> nudelsalatIngredients = new ArrayList<>();
		nudelsalatIngredients.add(new IngredientEntity(500.0, "g", "Spiralnudeln"));
		nudelsalatIngredients.add(new IngredientEntity(1.0, "", "Paprikaschote"));
		nudelsalatIngredients.add(new IngredientEntity(1.0, "", "Salatgurke"));
		nudelsalatIngredients.add(new IngredientEntity(250.0, "g", "Tomaten"));
		nudelsalatIngredients.add(new IngredientEntity(10.0, "", "Oliven"));
		recipes.add(new RecipeEntity("Nudelsalat", "", nudelsalatIngredients));
		
		
		List<IngredientEntity> chickpeaCurry = new ArrayList<>();
		chickpeaCurry.add(new IngredientEntity(1.0, "", "Zwiebel"));
		chickpeaCurry.add(new IngredientEntity(2.0, "", "Knoblauchzehen"));
		chickpeaCurry.add(new IngredientEntity(2.0, "Teelöffel", "Koriander (Gewürz)"));
		chickpeaCurry.add(new IngredientEntity(1.0, "Teelöffel", "Kreuzkümmel"));
		chickpeaCurry.add(new IngredientEntity(1.0, "Teelöffel", "Kurkuma"));
		chickpeaCurry.add(new IngredientEntity(3.0, "cm", "Ingwer"));
		chickpeaCurry.add(new IngredientEntity(4.0, "", "Tomaten"));
		chickpeaCurry.add(new IngredientEntity(4.0, "Esslöffel", "Rote Linsen"));
		chickpeaCurry.add(new IngredientEntity(4.0, "Esslöffel", "Cream of coconut"));
		chickpeaCurry.add(new IngredientEntity(250.0, "g", "Brokkoli"));
		chickpeaCurry.add(new IngredientEntity(400.0, "g", "Kichererbsen"));
		chickpeaCurry.add(new IngredientEntity(100.0, "g", "Blattspinat"));
		chickpeaCurry.add(new IngredientEntity(0.5, "", "Zitronen"));
		recipes.add(new RecipeEntity("Kirchererbsencurry", "", chickpeaCurry));
		
		cookbookRepository.save(new CookbookEntity(recipes));
	}
	
	public void initializeFoodplan(FoodPlanRepository foodPlanRepository, CookbookRepository cookbookRepository) {
		Map<String, MealEntity> meals = new HashMap<>();
		meals.put("monday", new MealEntity("Hegarty's"));
		meals.put("tuesday", new MealEntity(""));
		meals.put("wednesday", new MealEntity(""));
		meals.put("thursday", new MealEntity("Vielleicht bei Marc und Marlene"));
		meals.put("friday", new MealEntity(""));
		meals.put("saturday", new MealEntity("Kamayan"));
		meals.put("sunday", new MealEntity(""));
		
		foodPlanRepository.save(new FoodPlanEntity(meals));
	}
}
