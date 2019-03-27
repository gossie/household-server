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
}
