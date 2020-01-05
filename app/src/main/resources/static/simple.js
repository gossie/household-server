function readCookie() {
    const name = 'cookieConsent=';
    const decodedCookie = decodeURIComponent(document.cookie);
    const ca = decodedCookie.split(';');
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) === ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length);
        }
    }
    return '';
}

function giveConsent() {
    const d = new Date();
    d.setTime(d.getTime() + (356 * 24 * 60 * 60 * 1000));
    const expires = `expires=${d.toUTCString()}`;
    document.cookie = document.cookie =  `cookieConsent=1;${expires};path=/`;

    document.getElementById('cookie-consent').style.display = readCookie().trim().length === 0 ? 'block' : 'none';
}

document.addEventListener("DOMContentLoaded", function(event) {
    document.getElementById('cookie-consent').style.display = readCookie().trim().length === 0 ? 'block' : 'none';
});
