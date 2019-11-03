import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpXsrfTokenExtractor, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class CustomXsrfInterceptor implements HttpInterceptor {

    constructor(private tokenService: HttpXsrfTokenExtractor) {}

    public intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        console.debug('intercept request', req.url);
        if (req.method === 'GET' || req.method === 'HEAD') {
            return next.handle(req);
        }
        const token = this.tokenService.getToken();
        console.debug('found token', token);
        if (token !== null && !req.headers.has('XSRF-TOKEN')) {
            req = req.clone({headers: req.headers.set('XSRF-TOKEN', token)});
        }
        return next.handle(req);
    }
}