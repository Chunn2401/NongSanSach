const url = "/home"
const myTimeout = setTimeout(myGreeting, 2500);

function myGreeting() {
    window.open(url, "_self").focus();
}