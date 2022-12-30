import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonElementsModule } from './common-elements/common-elements.module';
import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { LoadingInterceptor } from './household-page/loading.interceptor';
import { DeleteInterceptor } from './household-page/delete.interceptor';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CustomXsrfInterceptor } from './custom-xsrf.interceptor';
import { CookieConsentComponent } from './cookie-consent/cookie-consent.component';
import { RegistrationPageComponent } from './registration-page/registration-page.component';
import { AppRoutingModule } from './app-routing.module';
import { LoginPageComponent } from './login-page/login-page.component';
import { BrowserModule } from '@angular/platform-browser';
import { JwtInterceptor } from './jwt.interceptor';

@NgModule({
    declarations: [
        AppComponent,
        CookieConsentComponent,
        RegistrationPageComponent,
        LoginPageComponent
    ],
    imports: [
        BrowserModule,
        ReactiveFormsModule,
        HttpClientModule,
        BrowserAnimationsModule,
        CommonElementsModule,
        AppRoutingModule
    ],
    providers: [
        { provide: HTTP_INTERCEPTORS, useClass: LoadingInterceptor, multi: true },
        { provide: HTTP_INTERCEPTORS, useClass: DeleteInterceptor, multi: true },
        { provide: HTTP_INTERCEPTORS, useClass: CustomXsrfInterceptor, multi: true },
        { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
