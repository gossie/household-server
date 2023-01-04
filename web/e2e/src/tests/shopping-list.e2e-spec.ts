import { LoginPage } from '../pages/login.po';
import { ShoppingListPage } from '../pages/shopping-list.po';
import { browser } from 'protractor';
import { RegistrationPage } from '../pages/registration.po';
import { NoHouseholdPage } from '../pages/no-household.po';

describe('shopping list', () => {
    const registrationPage: RegistrationPage = new RegistrationPage();
    const loginPage: LoginPage = new LoginPage();
    const noHouseholdPage: NoHouseholdPage = new NoHouseholdPage();
    const shoppingListPage: ShoppingListPage = new ShoppingListPage();

    beforeAll(async () => {
        await browser.waitForAngularEnabled(false);
    });

    afterAll(async () => {
        await browser.waitForAngularEnabled(true);
    });

    it('should register', async () => {
        await registrationPage.navigateTo();
        await registrationPage.register('shopping@user.de', 'shopping@user.de');
    });

    it('should login', async () => {
        await loginPage.login('shopping@user.de', 'shopping@user.de');
    });

    it('should create household', async () => {
        await noHouseholdPage.createHousehold();
    });

    it('should open shopping list', async () => {
        await shoppingListPage.navigateTo();
    });

    describe('global group', () => {
        it('should add some items', async () => {
            await shoppingListPage.addItemToGroup('Global', 'Tomaten');
            await shoppingListPage.addItemToGroup('Global', 'Paprika');
            await shoppingListPage.addItemToGroup('Global', 'Zucchini');
            expect(await shoppingListPage.getNumberOfItems('Global')).toBe(3);
        });

        it('should go shopping', async () => {
            await shoppingListPage.selectItem('Global', 'Tomaten');
            await shoppingListPage.selectItem('Global', 'Paprika');
            await shoppingListPage.clear('Global');
            await browser.sleep(3000);
            expect(await shoppingListPage.getNumberOfItems('Global')).toBe(1);
        });

        it('should by the rest', async () => {
            await shoppingListPage.selectItem('Global', 'Zucchini');
            await shoppingListPage.clear('Global');
            await browser.sleep(3000);
            expect(await shoppingListPage.getNumberOfItems('Global')).toBe(0);
        });
    });

    describe('new group', () => {
        it('should create new group', async () => {
            await shoppingListPage.addGroup('Weihnachten');
            expect(await shoppingListPage.getNumberOfGroups()).toBe(2);
        });

        it('should add some items', async () => {
            await shoppingListPage.addItemToGroup('Weihnachten', 'Zimt');
            await shoppingListPage.addItemToGroup('Weihnachten', 'Keks');
            await shoppingListPage.addItemToGroup('Weihnachten', 'Dominosteine');
            expect(await shoppingListPage.getNumberOfItems('Weihnachten')).toBe(3);
        });

        it('should change the name of an item', async () => {
            await shoppingListPage.changeItem('Weihnachten', 'Keks', 'Kekse');
        });

        it('should go shopping', async () => {
            await shoppingListPage.selectItem('Weihnachten', 'Zimt');
            await shoppingListPage.selectItem('Weihnachten', 'Dominosteine');
            await shoppingListPage.clear('Weihnachten');
            await browser.sleep(3000);
            expect(await shoppingListPage.getNumberOfItems('Weihnachten')).toBe(1);
        });

        it('should buy the rest', async () => {
            await shoppingListPage.selectItem('Weihnachten', 'Kekse');
            await shoppingListPage.clear('Weihnachten');
            await browser.sleep(3000);
            expect(await shoppingListPage.getNumberOfItems('Weihnachten')).toBe(0);
        });

        it('should delete group', async () => {
            await shoppingListPage.deleteGroup('Weihnachten');
            await browser.sleep(3000);
            expect(await shoppingListPage.getNumberOfGroups()).toBe(1);
        });
    });

});
