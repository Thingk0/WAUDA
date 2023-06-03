let buttons = document.querySelectorAll('.footer-btn');

function changeButtonStyle(index) {
    buttons.forEach((btn, idx) => {
        if (idx === index) {
            btn.classList.add('button-blue');
        } else {
            btn.classList.remove('button-blue');
        }
    });
    localStorage.setItem('selectedButtonIndex', index);
}

function setSelectedButtonColor() {
    let buttonIndex = Number(localStorage.getItem('selectedButtonIndex')) || 0;
    changeButtonStyle(buttonIndex);
}

document.addEventListener('DOMContentLoaded', setSelectedButtonColor);

buttons.forEach((button, index) => {
    button.addEventListener('click', () => {
        let url = button.dataset.url;
        changeButtonStyle(index);
        window.location.href = url;
    });
});
