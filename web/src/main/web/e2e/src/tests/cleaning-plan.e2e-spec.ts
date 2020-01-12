import { browser } from "protractor";
import { RegistrationPage } from '../pages/registration.po';
import { LoginPage } from '../pages/login.po';
import { NoHouseholdPage } from '../pages/no-household.po';
import CleaningPlanPage from '../pages/cleaning-plan.po';

describe('cleaning plan', () => {

    const registrationPage: RegistrationPage = new RegistrationPage();
    const loginPage: LoginPage = new LoginPage();
    const noHouseholdPage: NoHouseholdPage = new NoHouseholdPage();
    const cleaningPlanPage: CleaningPlanPage = new CleaningPlanPage();

    beforeAll(async () => {
        await browser.waitForAngularEnabled(false);
    });

    afterAll(async () => {
        await browser.waitForAngularEnabled(true);
    });

    it('should register', async () => {
        await registrationPage.navigateTo();
        await registrationPage.register('cleaninPlan@user.de', 'cleaninPlan@user.de');
    });

    it('should login', async () => {
        await loginPage.login('cleaninPlan@user.de', 'cleaninPlan@user.de');
    });

    it('should create household', async () => {
        await noHouseholdPage.createHousehold();
    });

    it('should create a chore', async () => {
        await cleaningPlanPage.navigateTo();
        expect(await cleaningPlanPage.getNumberOfChores()).toBe(0);
        await cleaningPlanPage.addChore('Bett beziehen', 14);
        expect(await cleaningPlanPage.getNumberOfChores()).toBe(1);
        await cleaningPlanPage.deleteChore(0);
        expect(await cleaningPlanPage.getNumberOfChores()).toBe(0);
    });

});