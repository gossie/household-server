import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { CommonElementsModule } from './common-elements/common-elements.module';
import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { HouseholdPageComponent } from './household-page/household-page.component';
import { NoHouseholdComponent } from './household-page/no-household/no-household.component';
import { LoadingInterceptor } from './household-page/loading.interceptor';
import { LoadingComponent } from './household-page/loading/loading.component';
import { DeleteInterceptor } from './household-page/delete.interceptor';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CustomXsrfInterceptor } from './custom-xsrf.interceptor';

@NgModule({
    declarations: [
        AppComponent,
        HouseholdPageComponent,
        NoHouseholdComponent,
        LoadingComponent
    ],
    imports: [
        ReactiveFormsModule,
        HttpClientModule,
        BrowserModule,
        BrowserAnimationsModule,
        CommonElementsModule,
        AppRoutingModule
    ],
    providers: [
        { provide: HTTP_INTERCEPTORS, useClass: LoadingInterceptor, multi: true },
        { provide: HTTP_INTERCEPTORS, useClass: DeleteInterceptor, multi: true },
        { provide: HTTP_INTERCEPTORS, useClass: CustomXsrfInterceptor, multi: true }
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
