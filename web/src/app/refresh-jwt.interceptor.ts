import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenService } from './token.service';
import { environment } from 'src/environments/environment';
import { RefreshResponse } from './refresh-response';

@Injectable({
    providedIn: 'root',
})
export class RefreshJwtInterceptor implements HttpInterceptor {

    constructor(private tokenService: TokenService, private httpClient: HttpClient) {}

    public intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (!this.isRegistration(req) && !this.isLogin(req) && !this.isRefresh(req)) {
            this.httpClient.post<RefreshResponse>(`${environment.apiUrl}/api/auth/refresh`, null, {
                headers: {
                    'Accepts': 'application/vnd.household.v1+json'
                }
            })
            .subscribe(response => this.tokenService.publishToken(response.token));
        }
        return next.handle(req);
    }

    private isRegistration(req: HttpRequest<any>): boolean {
        return req.url.endsWith('api/registrations') && req.method === 'POST'
    }

    private isLogin(req: HttpRequest<any>): boolean {
        return req.url.endsWith('api/auth/login') && req.method === 'POST'
    }

    private isRefresh(req: HttpRequest<any>): boolean {
        return req.url.endsWith('api/auth/refresh') && req.method === 'POST'
    }
}