import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Observable, of, throwError, NEVER } from 'rxjs';
import { TokenService } from './token.service';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import { Page } from './page.enum';

@Injectable({
    providedIn: 'root',
})
export class JwtInterceptor implements HttpInterceptor {

    private jwt: string;

    constructor(tokenService: TokenService, private router: Router) {
        tokenService.observeToken()
            .subscribe(token => this.jwt = token);
    }

    public intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (this.isRegistration(req) || this.isLogin(req)) {
            return next.handle(req);
        }

        req = req.clone({headers: req.headers.set('Authorization', this.jwt)});
        return next.handle(req)
            .pipe(
                catchError((error: HttpErrorResponse) => {
                    if (error.status === 401 || error.status === 403) {
                        this.router.navigateByUrl(`/${Page.Login}`);
                    } else {
                        return throwError(error);
                    }
                    return NEVER;
                })
            )
    }

    private isRegistration(req: HttpRequest<any>): boolean {
        return req.url.endsWith('api/registrations') && req.method === 'POST'
    }

    private isLogin(req: HttpRequest<any>): boolean {
        return req.url.endsWith('api/auth/login') && req.method === 'POST'
    }
}