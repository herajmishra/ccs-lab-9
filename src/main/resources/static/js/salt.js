function generateSalt(length = 16) {
    const charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    let salt = "";
    for (let i = 0; i < length; i++) {
        salt += charset.charAt(Math.floor(Math.random() * charset.length));
    }
    return salt;
}

function hashPassword() {
    const passwordField = document.getElementById("password");
    const saltField = document.getElementById("salt");
    let salt = saltField.value;
    if (salt === null || salt.length === 0) {
        salt = generateSalt();
    }

    saltField.value = salt;

    const hashedPassword = CryptoJS.SHA256(passwordField.value + salt).toString();

    console.log(salt);
    passwordField.value = hashedPassword;

    return true;
}

