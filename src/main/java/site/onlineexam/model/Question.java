package site.onlineexam.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "discipline_id", nullable = false)
    private Discipline discipline;
    
    @ManyToOne
    @JoinColumn(name = "theme_id", nullable = false)
    private Theme theme;

    @ElementCollection
    @ManyToMany
    @JoinTable(
        name = "question_tags",
        joinColumns = @JoinColumn(name = "question_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();
    
    private LocalDate creationDate;
    private String createdBy;
    private int difficultyLevel;
    private QuestionType questionType;
    private String statement;
    
    @ElementCollection
    private List<String> answer;

    @ElementCollection
    private List<String> correctAnswer;

    /* 
     // getters e setters 
    }
     */

    public void setCorrectAnswer(List<String> correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Discipline getDiscipline() {
        return discipline;
    }
    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
    public Theme getTheme() {
        return theme;
    }
    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Set<Tag> getTags() {
        return tags;
    }
    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public int getDifficultyLevel() {
        return difficultyLevel;
    }
    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
    public QuestionType getQuestionType() {
        return questionType;
    }
    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }
    public String getStatement() {
        return statement;
    }
    public void setStatement(String statement) {
        this.statement = statement;
    }
    public List<String> getAnswer() {
        return answer;
    }
    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }
    public List<String> getCorrectAnswer() {
        return correctAnswer;
    }
}
