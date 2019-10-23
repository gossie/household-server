import { RegistrationPage } from '../pages/registration.po';
import { LoginPage } from '../pages/login.po';
import { CoverPage } from '../pages/cover-page.po';
import { NoHouseholdPage } from '../pages/no-household.po';
import { browser } from 'protractor';

describe('change password', () => {
    const registrationPage: RegistrationPage = new RegistrationPage();
    const loginPage: LoginPage = new LoginPage();
    const noHouseholdPage: NoHouseholdPage = new NoHouseholdPage();
    const coverPage: CoverPage = new CoverPage();

    beforeAll(async () => {
        await browser.waitForAngularEnabled(false);
    });

    afterAll(async () => {
        await browser.waitForAngularEnabled(true);
    });

    it('should register', async () => {
        await registrationPage.navigateTo();
        await registrationPage.register('password@user.de', 'password@user.de');
    });

    it('should login', async () => {
        await loginPage.login('password@user.de', 'password@user.de');
    });

    it('should create household', async () => {
        await noHouseholdPage.createHousehold();
    });

    it('should change password', async () => {
        await coverPage.changePassword('password@user.de', 'changedPassword');
    });

    it('should logout', async () => {
        await coverPage.logout();
    });

    it('should login again', async () => {
        await loginPage.login('password@user.de', 'changedPassword');
        await loginPage.login('password@user.de', 'changedPassword');
    });

    it('should change password back', async () => {
        await coverPage.changePassword('changedPassword', 'neuer2@user.de');
    });

    it('should logout', async () => {
        await coverPage.logout();
    });

});
