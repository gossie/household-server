import { LoginPage } from '../pages/login.po';
import { CoverPage } from '../pages/cover-page.po';
import { browser, by, element, ExpectedConditions } from 'protractor';
import { RegistrationPage } from '../pages/registration.po';
import { NoHouseholdPage } from '../pages/no-household.po';

describe('invitation', () => {
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

    it('should register two users', async () => {
        await registrationPage.navigateTo();
        await registrationPage.register('neuer1@user.de', 'neuer1@user.de');
        await registrationPage.navigateTo();
        await registrationPage.register('neuer2@user.de', 'neuer2@user.de');
    });

    it('should login', async () => {
        await loginPage.login('neuer1@user.de', 'neuer1@user.de');
    });

    it('should create household', async () => {
        await noHouseholdPage.createHousehold();
    });

    it('should send invitation', async () => {
        await coverPage.invite('neuer2@user.de');
    });

    it('should logout', async () => {
        await coverPage.logout();
    });

    it('should login', async () => {
        await loginPage.navigateTo();
        await loginPage.login('neuer2@user.de', 'neuer2@user.de');
    });

    describe('rejections', () => {

        beforeAll(async () => {
            await browser.waitForAngularEnabled(false);
        });

        it('should reject invitation', async () => {
            await browser.wait(ExpectedConditions.visibilityOf(element(by.css('#reject-button'))), 7000);
            await coverPage.rejectInvitation();
            await browser.sleep(5000);
        });

        afterAll(async () => {
            await browser.waitForAngularEnabled(true);
        })

    });


});
