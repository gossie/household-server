import { by, element } from "protractor";

export class CoverPage {

    public async invite(email: string): Promise<void> {
        await element(by.css('#invitation-input')).sendKeys(email);
        return element(by.css('#invitation-button')).click();
    }

    public async rejectInvitation(): Promise<void> {
        return element(by.css('#reject-button')).click()
    }

    public async logout(): Promise<void> {
        return element(by.css('#logout-button')).click();
    }

    public async changePassword(currentPassword: string, newPassword: string): Promise<void> {
        await element(by.css('#current-password')).sendKeys(currentPassword);
        await element(by.css('#new-password')).sendKeys(newPassword);
        await element(by.css('#new-password-repeat')).sendKeys(newPassword);
        return element(by.css('#change-password-button')).click();
    }
}
