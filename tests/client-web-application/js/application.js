
function showLoginForm()
{
    templateBuilder.build('login-form', {}, 'login');
}

function hideLoginForm()
{
    templateBuilder.clear('login');
}

function login()
{
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    userService.login(username, password);
    hideLoginForm()
}
document.addEventListener('DOMContentLoaded', () => {

});
