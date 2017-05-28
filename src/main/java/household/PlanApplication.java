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

import household.cleaningplan.Chore;
import household.cleaningplan.CleaningPlan;
import household.cleaningplan.CleaningPlanRepository;
import household.cleaningplan.Repeat;
import household.cleaningplan.TimeUnit;
import household.cookbook.Cookbook;
import household.cookbook.CookbookRepository;
import household.cookbook.Ingredient;
import household.cookbook.Recipe;
import household.foodplan.FoodPlan;
import household.foodplan.FoodPlanRepository;
import household.foodplan.Meal;
import household.household.HouseholdRepository;
import household.shoppinglist.ShoppingList;
import household.shoppinglist.ShoppingListItem;
import household.shoppinglist.ShoppingListRepository;

@SpringBootApplication
@EnableEntityLinks
public class PlanApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanApplication.class, args);
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
		ShoppingListItem item1 = new ShoppingListItem("Grillgut", false);
		ShoppingListItem item2 = new ShoppingListItem("Duschgel", false);
		ShoppingListItem item3 = new ShoppingListItem("Knoblauchzehen", false);
		ShoppingListItem item4 = new ShoppingListItem("Koriander (Gewürz)", true);
		ShoppingListItem item5 = new ShoppingListItem("Ingwer", false);
		ShoppingListItem item6 = new ShoppingListItem("Tomaten", false);
		ShoppingListItem item7 = new ShoppingListItem("Cream of coconut", false);
		ShoppingListItem item8 = new ShoppingListItem("Blattspinat", false);
		ShoppingListItem item9 = new ShoppingListItem("Zitronen", false);
		ShoppingListItem item10 = new ShoppingListItem("Kohlensäure", false);
		ShoppingList shoppingList = new ShoppingList(asList(item1, item2, item3, item4, item5, item6, item7, item8, item9, item10));
		shoppingListRepository.save(shoppingList);
	}
	
	@SuppressWarnings("deprecation")
	public void initializeCleaningPlan(CleaningPlanRepository cleaningPlanRepository) {
		Chore item1 = new Chore("Bett beziehen", new Date(117, 2, 31).getTime(), new Repeat(2, TimeUnit.WEEKS));
		Chore item2 = new Chore("Gelber Sack", new Date(117, 2, 30).getTime(), new Repeat(1, TimeUnit.WEEKS));
		Chore item3 = new Chore("Staubsaugen", new Date(117, 2, 31).getTime(), new Repeat(1, TimeUnit.WEEKS));
		Chore item4 = new Chore("Bad putzen", new Date(117, 2, 31).getTime(), new Repeat(1, TimeUnit.WEEKS));
		Chore item5 = new Chore("Wäsche waschen", new Date(117, 2, 31).getTime(), new Repeat(84, TimeUnit.HOURS));
		Chore item6 = new Chore("Boden wischen", new Date(117, 2, 10).getTime(), new Repeat(1, TimeUnit.WEEKS));
		Chore item7 = new Chore("Staub wischen", new Date(117, 2, 31).getTime(), new Repeat(84, TimeUnit.HOURS));
		Chore item8 = new Chore("Küche putzen", new Date(117, 2, 31).getTime(), new Repeat(1, TimeUnit.WEEKS));
		Chore item9 = new Chore("Müll", new Date(117, 3, 1).getTime(), new Repeat(2, TimeUnit.WEEKS));
		Chore item10 = new Chore("Klo putzen", new Date(117, 2, 31).getTime(), new Repeat(1, TimeUnit.WEEKS));
		Chore item11 = new Chore("Blumen gießen", new Date(117, 3, 1).getTime(), new Repeat(3, TimeUnit.DAYS));
		CleaningPlan cleaningPlan = new CleaningPlan(asList(item1, item2, item3, item4, item5, item6, item7, item8, item9, item10, item11));
		cleaningPlanRepository.save(cleaningPlan);
	}
	
	public void initializeFoodStuff(CookbookRepository cookbookRepository, FoodPlanRepository foodPlanRepository) {
		initializeCookbook(cookbookRepository);
		initializeFoodplan(foodPlanRepository, cookbookRepository);
	}
	
	private void initializeCookbook(CookbookRepository cookbookRepository) {
		List<Recipe> recipes = new ArrayList<>();
		
		List<Ingredient> chiliIngredients = new ArrayList<>();
		chiliIngredients.add(new Ingredient(125.0, "g", "Sojagranulat"));
		chiliIngredients.add(new Ingredient(200.0, "g", "Möhren"));
		chiliIngredients.add(new Ingredient(1.0, "", "Paprikaschote"));
		chiliIngredients.add(new Ingredient(1.0, "", "Zucchini"));
		chiliIngredients.add(new Ingredient(5.0, "TL", "Tomatenmark"));
		chiliIngredients.add(new Ingredient(1.0, "Dose", "Kidneybohnen"));
		chiliIngredients.add(new Ingredient(1.0, "Dose", "Mais"));
		chiliIngredients.add(new Ingredient(500.0, "ml", "Gemüsebrühe"));
		recipes.add(new Recipe("Chili sin carne", "", chiliIngredients));
		
		List<Ingredient> curryIngredients = new ArrayList<>();
		recipes.add(new Recipe("Curry", "", curryIngredients));
		
		List<Ingredient> spaghettiIngredients = new ArrayList<>();
		spaghettiIngredients.add(new Ingredient(500.0, "g", "Pilze"));
		spaghettiIngredients.add(new Ingredient(2.0, "", "Möhren"));
		spaghettiIngredients.add(new Ingredient(2.0, "", "Selleriestangen"));
		spaghettiIngredients.add(new Ingredient(2.0, "Dosen", "gehackte Tomten"));
		spaghettiIngredients.add(new Ingredient(1.0, "Esslöffel", "Tomatenmark"));
		spaghettiIngredients.add(new Ingredient(500, "g", "Pasta"));
		recipes.add(new Recipe("Spaghetti Bolognese", "", spaghettiIngredients));
		
		recipes.add(new Recipe("Lasagne", "", Collections.emptyList()));
		
		List<Ingredient> nudelsalatIngredients = new ArrayList<>();
		nudelsalatIngredients.add(new Ingredient(500.0, "g", "Spiralnudeln"));
		nudelsalatIngredients.add(new Ingredient(1.0, "", "Paprikaschote"));
		nudelsalatIngredients.add(new Ingredient(1.0, "", "Salatgurke"));
		nudelsalatIngredients.add(new Ingredient(250.0, "g", "Tomaten"));
		nudelsalatIngredients.add(new Ingredient(10.0, "", "Oliven"));
		recipes.add(new Recipe("Nudelsalat", "", nudelsalatIngredients));
		
		
		List<Ingredient> chickpeaCurry = new ArrayList<>();
		chickpeaCurry.add(new Ingredient(1.0, "", "Zwiebel"));
		chickpeaCurry.add(new Ingredient(2.0, "", "Knoblauchzehen"));
		chickpeaCurry.add(new Ingredient(2.0, "Teelöffel", "Koriander (Gewürz)"));
		chickpeaCurry.add(new Ingredient(1.0, "Teelöffel", "Kreuzkümmel"));
		chickpeaCurry.add(new Ingredient(1.0, "Teelöffel", "Kurkuma"));
		chickpeaCurry.add(new Ingredient(3.0, "cm", "Ingwer"));
		chickpeaCurry.add(new Ingredient(4.0, "", "Tomaten"));
		chickpeaCurry.add(new Ingredient(4.0, "Esslöffel", "Rote Linsen"));
		chickpeaCurry.add(new Ingredient(4.0, "Esslöffel", "Cream of coconut"));
		chickpeaCurry.add(new Ingredient(250.0, "g", "Brokkoli"));
		chickpeaCurry.add(new Ingredient(400.0, "g", "Kichererbsen"));
		chickpeaCurry.add(new Ingredient(100.0, "g", "Blattspinat"));
		chickpeaCurry.add(new Ingredient(0.5, "", "Zitronen"));
		recipes.add(new Recipe("Kirchererbsencurry", "", chickpeaCurry));
		
		cookbookRepository.save(new Cookbook(recipes));
	}
	
	public void initializeFoodplan(FoodPlanRepository foodPlanRepository, CookbookRepository cookbookRepository) {
		Map<String, Meal> meals = new HashMap<>();
		meals.put("monday", new Meal("Hegarty's"));
		meals.put("tuesday", new Meal(""));
		meals.put("wednesday", new Meal(""));
		meals.put("thursday", new Meal("Vielleicht bei Marc und Marlene"));
		meals.put("friday", new Meal(""));
		meals.put("saturday", new Meal("Kamayan"));
		meals.put("sunday", new Meal(""));
		
		foodPlanRepository.save(new FoodPlan(meals));
	}
}
