import { LoginPage } from "./login.po";

describe('???', () => {
    let page: LoginPage;

    beforeEach(() => {
        page = new LoginPage();
    });

    it('should login', async () => {
        await page.navigateTo();
        await page.login('neuer1@user.de', 'neuer1@user.de');
    });
});
