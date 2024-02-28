const $btnSignIn = document.querySelector(".sign-in-btn");
const $btnSignUp = document.querySelector(".sign-up-btn");
const $signUp = document.querySelector(".crear-cuenta");
const $signIn = document.querySelector(".iniciar-sesion");

document.addEventListener('click', e => {
    if (e.target === $btnSignIn || e.target === $btnSignUp) {
        $signIn.classList.toggle('active');
        $signUp.classList.toggle('active')
    }
});
