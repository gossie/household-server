import { LoginPage } from "../pages/login.po";
import { CoverPage } from "../pages/cover-page.po";

describe('change password', () => {
    const loginPage: LoginPage = new LoginPage();
    const coverPage: CoverPage = new CoverPage();

    it('should login', async () => {
        await loginPage.navigateTo();
        await loginPage.login('neuer2@user.de', 'neuer2@user.de');
    });

    it('should change password', async () => {
        await coverPage.changePassword('changedPassword');
    });

    it('should logout', async () => {
        await coverPage.logout();
    });

    it('should login again', async () => {
        await loginPage.navigateTo();
        await loginPage.login('neuer2@user.de', 'changedPassword');
    });

    it('should change password back', async () => {
        await coverPage.changePassword('neuer2@user.de');
    });

    it('should logout', async () => {
        await coverPage.logout();
    });

});
