import { LoginPage } from '../pages/login.po';
import { ShoppingListPage } from '../pages/shopping-list.po';
import { browser } from 'protractor';
import { RegistrationPage } from '../pages/registration.po';

xdescribe('shopping list', () => {
    const registrationPage: RegistrationPage = new RegistrationPage();
    const loginPage: LoginPage = new LoginPage();
    const shoppingListPage: ShoppingListPage = new ShoppingListPage();

    it('should register', async () => {
        await registrationPage.navigateTo();
        await registrationPage.register('neuer1@user.de', 'neuer1@user.de');
    });

    it('should login', async () => {
        await loginPage.navigateTo();
        await loginPage.login('neuer1@user.de', 'neuer1@user.de');
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
        it('should create new group', async ()=> {
            await shoppingListPage.addGroup('Weihnachten');
            expect(await shoppingListPage.getNumberOfGroups()).toBe(2);
        });

        it('should add some items', async () => {
            await shoppingListPage.addItemToGroup('Weihnachten', 'Zimt');
            await shoppingListPage.addItemToGroup('Weihnachten', 'Kekse');
            await shoppingListPage.addItemToGroup('Weihnachten', 'Dominosteine');
            expect(await shoppingListPage.getNumberOfItems('Weihnachten')).toBe(3);
        });

        it('should go shopping', async () => {
            await shoppingListPage.selectItem('Weihnachten', 'Zimt');
            await shoppingListPage.selectItem('Weihnachten', 'Dominosteine');
            await shoppingListPage.clear('Weihnachten');
            await browser.sleep(3000);
            expect(await shoppingListPage.getNumberOfItems('Weihnachten')).toBe(1);
        });

        it('should by the rest', async () => {
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
