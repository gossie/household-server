import { AbstractControl } from '@angular/forms';

export class PasswordValidation {

    public static matchPassword(ac: AbstractControl) {

        let password = ac.get('password').value;
        let confirmPassword = ac.get('passwordRepeat').value;
        if(password != confirmPassword) {
            ac.get('passwordRepeat').setErrors( {MatchPassword: true} )
        } else {
           return null;
        }
    }
}
