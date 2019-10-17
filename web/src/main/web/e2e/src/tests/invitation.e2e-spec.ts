import { LoginPage } from "../pages/login.po";
import { CoverPage } from "../pages/cover-page.po";
import { browser, by, element, ExpectedConditions } from "protractor";

describe('invitation', () => {
    const loginPage: LoginPage = new LoginPage();
    const coverPage: CoverPage = new CoverPage();

    it('should login', async () => {
        await loginPage.navigateTo();
        await loginPage.login('neuer1@user.de', 'neuer1@user.de');
    });

    it('should send invitation', async () => {
        await coverPage.invite('neuer3@user.de');
    });

    it('should logout', async () => {
        await coverPage.logout();
    });

    it('should login', async () => {
        await loginPage.navigateTo();
        await loginPage.login('neuer3@user.de', 'neuer3@user.de');
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
