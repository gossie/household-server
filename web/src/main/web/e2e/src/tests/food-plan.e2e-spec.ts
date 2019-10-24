import { LoginPage } from '../pages/login.po';
import { ShoppingListPage } from '../pages/shopping-list.po';
import { FoodPlanPage } from '../pages/food-plan.po';
import { browser } from 'protractor';
import { RegistrationPage } from '../pages/registration.po';
import { NoHouseholdPage } from '../pages/no-household.po';
import { CookbookPage } from '../pages/cookbook.po';
import { AddRecipeDialog } from '../pages/cookbook-components/add-recipe-dialog.co';

fdescribe('food plan', () => {
    const registrationPage: RegistrationPage = new RegistrationPage();
    const loginPage: LoginPage = new LoginPage();
    const noHouseholdPage: NoHouseholdPage = new NoHouseholdPage();
    const cookbookPage: CookbookPage = new CookbookPage();
    const foodPlanPage: FoodPlanPage = new FoodPlanPage();
    const shoppingListPage: ShoppingListPage = new ShoppingListPage();

    beforeAll(async () => {
        await browser.waitForAngularEnabled(false);
    });

    afterAll(async () => {
        await browser.waitForAngularEnabled(true);
    });

    it('should register', async () => {
        await registrationPage.navigateTo();
        await registrationPage.register('foodPlan@user.de', 'foodPlan@user.de');
    });

    it('should login', async () => {
        await loginPage.login('foodPlan@user.de', 'foodPlan@user.de');
    });

    it('should create household', async () => {
        await noHouseholdPage.createHousehold();
    });

    it('should create a recipe', async () => {
        await cookbookPage.navigateTo();

        const addRecipeDialog: AddRecipeDialog = await cookbookPage.openAddRecipeDialog();
        await addRecipeDialog.setRecipeName('Chili con carne');
        await addRecipeDialog.addIngrediant(1, '', 'Zwiebel');
        await addRecipeDialog.addIngrediant(1, '', 'Chilischote');
        await addRecipeDialog.addIngrediant(500, 'g', 'Hack');
        await addRecipeDialog.addIngrediant(2, '', 'Möhren');
        await addRecipeDialog.addIngrediant(1, '', 'Paprikaschote');
        await addRecipeDialog.addIngrediant(5, 'EL', 'Tomatenmark');
        await addRecipeDialog.saveRecipe();
    });

    it('should open food plan', async () => {
        await foodPlanPage.navigateTo();
    });

    it('should search for a meal', async () => {
        await foodPlanPage.setMeal('monday', 'Ch');
    });

    it('should select a meal', async () => {
        await foodPlanPage.selectMealFromCookbook('Chili con carne');
    });

    it('should add some ingredients', async () => {
        await foodPlanPage.selectIngredient(0);
        await foodPlanPage.selectIngredient(2);
        await foodPlanPage.selectIngredient(5);
        await foodPlanPage.saveIngredients();
    });

    it('should go shopping', async () => {
        await shoppingListPage.navigateTo();
        expect(await shoppingListPage.getNumberOfItems('Global')).toBe(3);

        await shoppingListPage.selectItem('Global', 'Zwiebel');
        await shoppingListPage.selectItem('Global', 'Hack');
        await shoppingListPage.selectItem('Global', 'Tomatenmark');
        await shoppingListPage.clear('Global');
        await browser.sleep(3000);
        expect(await shoppingListPage.getNumberOfItems('Global')).toBe(0);
    });

    it('should clear food plan', async () => {
        await foodPlanPage.navigateTo();
        await foodPlanPage.clear();
        await browser.sleep(3000);
        expect(await foodPlanPage.isFoodPlanEmpty()).toBeTruthy();
    })

});
