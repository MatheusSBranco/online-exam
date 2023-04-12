var questionTypeSelect = document.getElementById("questionType");
var answerDiv = document.getElementById("answer");
var answerList = answerDiv.querySelector("ul");
var addAnswerButton = answerDiv.querySelector(".add-answer-button");
var removeAnswerButtons = answerDiv.querySelectorAll(".remove-answer-button");
var correctAnswerDiv = document.getElementById("correctAnswer");
var correctAnswerList = correctAnswerDiv.querySelector("ul");
var addCorrectAnswerButton = correctAnswerDiv.querySelector(".add-correctAnswer-button");
var removeCorrectAnswerButtons = correctAnswerDiv.querySelectorAll(".remove-correctAnswer-button");



function updateAnswerDisplay() {
  if (questionTypeSelect.value === "OPEN_ENDED") {
      answerDiv.innerHTML = `
              <label for="answer">Resposta:</label>
              <textarea class="form-control" name="answer" rows="4"></textarea>
          `;
      answerDiv.style.display = "none";
      answerDiv.removeAttribute("required");
      answerDiv.value = "";
  } else {
      answerDiv.setAttribute("required", "");
      answerDiv.style.display = "block";
      if (questionTypeSelect.value === "MULTIPLE_CHOICE_SINGLE_ANSWER" || questionTypeSelect.value === "TRUE_FALSE" || questionTypeSelect.value === "MULTIPLE_CHOICE_MULTIPLE_ANSWERS") {
        answerDiv.innerHTML = `
            <label for="answer">Respostas:</label>
            <ul>
                <li><input type="text" name="answer[]" th:field="*{answer}" required></li>
            </ul>
            <button type="button" class="add-answer-button">Adicionar Resposta</button>
        `;
        addAnswerButton = answerDiv.querySelector(".add-answer-button");
        answerList = answerDiv.querySelector("ul");
        addAnswerButton.addEventListener("click", function() {
            var newAnswer = document.createElement("li");
            newAnswer.innerHTML = `<input type="text" name="answer[]"  th:field="*{answer}" required> <button type="button" class="remove-answer-button">Remove</button>`;
            answerList.appendChild(newAnswer);
            removeAnswerButtons = answerDiv.querySelectorAll(".remove-answer-button");
            removeAnswerButtons.forEach(function(button) {
                button.addEventListener("click", function() {
                    answerList.removeChild(button.parentNode);
                });
            });
        });

        if (questionTypeSelect.value == "MULTIPLE_CHOICE_MULTIPLE_ANSWERS") {
          correctAnswerDiv.innerHTML = `
                <label for="correctAnswer">Respostas Corretas:</label>
                <ul>
                    <li><input type="text" name="correctAnswer[]" th:field="*{correctAnswer}" required></li>
                </ul>
                <button type="button" class="add-correctAnswer-button">Adicionar Resposta Correta</button>
          `;
          addCorrectAnswerButton = correctAnswerDiv.querySelector(".add-correctAnswer-button");
          correctAnswerList = correctAnswerDiv.querySelector("ul");
          addCorrectAnswerButton.addEventListener("click", function() {
            var newCorrectAnswer = document.createElement("li");
            newCorrectAnswer.innerHTML = `<input type="text" name="correctAnswer[]" th:field="*{correctAnswer}" required> <button type="button" class="remove-correctAnswer-button">Remove</button>`;
            correctAnswerList.appendChild(newCorrectAnswer);
            removeCorrectAnswerButtons = correctAnswerDiv.querySelectorAll(".remove-correctAnswer-button");
            removeCorrectAnswerButtons.forEach(function(button) {
                button.addEventListener("click", function() {
                    correctAnswerList.removeChild(button.parentNode);
                });
            });
          });
        } else {
          correctAnswerDiv.innerHTML = `
          <label for="answer">Resposta Correta:</label>
          <textarea class="form-control" name="correctAnswer" th:field="*{correctAnswer}" rows="4" required></textarea>
      `;
        }
      }
    else {
      answerDiv.innerHTML = `
          <label for="answer">Answer:</label>
          <textarea class="form-control" name="answer" th:field="*{answer}" rows="4" required></textarea>
      `;
    }
  }
}

window.addEventListener("load", function() {
  updateAnswerDisplay();
});

questionTypeSelect.addEventListener("change", function() {
  if (questionTypeSelect.value === "OPEN_ENDED") {
    answerDiv.value = "";
  }
  updateAnswerDisplay();
});
