let questionTypeSelect = document.getElementById("questionType");
let answerDiv = document.getElementById("answer");
let correctAnswerDiv = document.getElementById("correctAnswer");

function createAnswerInput(name, isRemovable) {
    let li = document.createElement("li");
    li.innerHTML = `<input type="text" name="${name}[]" required>`;
    if (isRemovable) {
        let removeButton = document.createElement("button");
        removeButton.type = "button";
        removeButton.textContent = "Remove";
        removeButton.addEventListener("click", function () {
            li.remove();
        });
        li.appendChild(removeButton);
    }
    return li;
}

function setupAnswerSection(answerList, button, name) {
    button.addEventListener("click", function () {
        let newAnswer = createAnswerInput(name, true);
        answerList.appendChild(newAnswer);
    });
}

function updateAnswerDisplay() {
    while (answerDiv.firstChild) answerDiv.removeChild(answerDiv.firstChild);
    while (correctAnswerDiv.firstChild) correctAnswerDiv.removeChild(correctAnswerDiv.firstChild);

    if (questionTypeSelect.value === "OPEN_ENDED") {
        answerDiv.style.display = "none";
        correctAnswerDiv.innerHTML = `
            <label for="correctAnswer">Resposta:</label>
            <textarea class="form-control" name="correctAnswer" rows="4" required></textarea>
        `;
    } else {
        answerDiv.style.display = "block";
        answerDiv.innerHTML = `
            <label for="answer">Respostas:</label>
            <ul></ul>
            <button type="button" class="add-answer-button">Adicionar Resposta</button>
        `;
        let answerList = answerDiv.querySelector("ul");
        let addAnswerButton = answerDiv.querySelector(".add-answer-button");
        setupAnswerSection(answerList, addAnswerButton, "answer");

        if (questionTypeSelect.value === "MULTIPLE_CHOICE_MULTIPLE_ANSWERS") {
            correctAnswerDiv.innerHTML = `
                <label for="correctAnswer">Respostas Corretas:</label>
                <ul></ul>
                <button type="button" class="add-correctAnswer-button">Adicionar Resposta Correta</button>
            `;
            let correctAnswerList = correctAnswerDiv.querySelector("ul");
            let addCorrectAnswerButton = correctAnswerDiv.querySelector(".add-correctAnswer-button");
            setupAnswerSection(correctAnswerList, addCorrectAnswerButton, "correctAnswer");
        } else {
            correctAnswerDiv.innerHTML = `
                <label for="correctAnswer">Resposta Correta:</label>
                <textarea class="form-control" name="correctAnswer" rows="4" required></textarea>
            `;
        }
    }
}

window.addEventListener("load", updateAnswerDisplay);
questionTypeSelect.addEventListener("change", updateAnswerDisplay);