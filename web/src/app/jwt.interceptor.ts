import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable, Subscription } from 'rxjs';
import { TokenService } from './token.service';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

    private jwt: string;

    constructor(tokenService: TokenService) {
        tokenService.observeToken()
            .subscribe(token => this.jwt = token);
    }

    public intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        req = req.clone({headers: req.headers.set('Authorization', this.jwt)});
        return next.handle(req);
    }
}