import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { UserService } from './user.service';
import { ObjectUtils } from './object.utils';
import { Page } from './page.enum';

@Injectable({
    providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

    constructor(private userService: UserService,
                private router: Router) { }

    public canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        const canLoad = ObjectUtils.isObject(this.userService.getUserData());
        if (!canLoad) {
            this.router.navigate([Page.Login]);
        }
        return canLoad;
    }
}
