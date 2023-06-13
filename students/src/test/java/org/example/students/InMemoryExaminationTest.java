package org.example.students;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.students.exception.ScoreNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Set;

class InMemoryExaminationTest {

    private Examination exams;

    @BeforeEach
    void setUp() {
        exams = new InMemoryExamination();
    }

    @Test
    void addAndGetScore() throws ScoreNotFoundException {
        Score score = new Score("Vadim", "Vadimov", "mathematics", 5);
        exams.addScore(score);

        Score expectedScore = exams.getScore(score.name(), score.lastname(), score.subject());
        Assertions.assertEquals(expectedScore, score);
    }

    @Test
    void getScore() {
        Assertions.assertThrows(ScoreNotFoundException.class, () -> exams.getScore("Vadim", "Vadimov", "mathematics"));
    }

    @Test
    void getAverageForSubject() {
        Score score1 = new Score("Vadim", "Vadim@mail.ru", "mathematics", 5);
        Score score2 = new Score("Dmitry", "Dmitiy@@mail.ru", "mathematics", 4);
        Score score3 = new Score("Vladimir", "Vladimir@@mail.ru", "mathematics", 4);
        Score score4 = new Score("Irina", "Irina@@mail.ru", "history", 4);

        exams.addScore(score1);
        exams.addScore(score2);
        exams.addScore(score3);
        exams.addScore(score4);

        double avgForSubject = exams.getAverageForSubject("mathematics");
        Assertions.assertEquals(avgForSubject, 13.0 / 3);
    }

    @Test
    void multipleSubmissionsStudentNames() {
        Score score1 = new Score("Vadim", "Vadim@mail.ru", "mathematics", 2);
        Score score2 = new Score("Vadim", "Vadim@mail.ru", "history", 4);
        Score score3 = new Score("Vladimir", "Vladimir@@mail.ru", "history", 2);
        Score score4 = new Score("Irina", "Irina@@mail.ru", "history", 4);
        Score score5 = new Score("Irina", "Irina@@mail.ru", "mathematics", 4);
        Score score6 = new Score("Vladimir", "Vladimir@@mail.ru", "biology", 3);
        Score score7 = new Score("Vadim", "Vadim@mail.ru", "biology", 4);

        exams.addScore(score1);
        exams.addScore(score2);
        exams.addScore(score3);

        exams.addScore(score5);
        exams.addScore(score6);
        exams.addScore(score7);

        Set<String> students = exams.multipleSubmissionsStudentNames();
        Assertions.assertEquals(students.size(), 2);
    }

    @Test
    void lastFiveStudentsWithExcellentMarkOnAnySubject() {
        Score score1 = new Score("Anna", "Anna@mail.ru", "mathematics", 5);
        Score score2 = new Score("Maria", "Maria@mail.ru", "linux", 3);
        Score score3 = new Score("Vladimir", "Vladimir@mail.ru", "c++", 2);
        Score score4 = new Score("Nikita", "Nikita@mail.ru", "history", 5);
        Score score5 = new Score("Irina", "Irina@@mail.ru", "assembler", 4);
        Score score6 = new Score("Vladimir", "Vladimir@mail.ru", "biology", 5);
        Score score7 = new Score("Alex", "Alex@mail.ru", "sql", 1);
        Score score8 = new Score("Natasha", "Natasha@mail.ru", "python", 5);
        Score score9 = new Score("Uriy", "Uriy@mail.ru", "java", 5);

        exams.addScore(score1);
        exams.addScore(score2);
        exams.addScore(score3);
        exams.addScore(score4);
        exams.addScore(score5);
        exams.addScore(score6);
        exams.addScore(score7);
        exams.addScore(score8);
        exams.addScore(score9);

        Set<String> students = exams.lastFiveStudentsWithExcellentMarkOnAnySubject();
        Assertions.assertEquals(students.size(), 5);

        Set<String> actualStudents = Set.of("AnnaAnna@mail.ru", "NatashaNatasha@mail.ru", "NikitaNikita@mail.ru", "UriyUriy@mail.ru", "VladimirVladimir@mail.ru");
        Assertions.assertEquals(students, actualStudents);
    }

    @Test
    void getAllScores() {
        Score score1 = new Score("Vadim", "Vadim@mail.ru", "mathematics", 5);
        Score score2 = new Score("Vadim", "Vadim@mail.ru", "linux", 5);
        Score score3 = new Score("Vladimir", "Vladimir@mail.ru", "c++", 5);
        Score score4 = new Score("Irina", "Irina@@mail.ru", "history", 5);
        Score score5 = new Score("Irina", "Irina@@mail.ru", "assembler", 5);
        Score score6 = new Score("Vladimir", "Vladimir@mail.ru", "biology", 5);
        Score score7 = new Score("Vadim", "Vadim@mail.ru", "sql", 5);
        Score score8 = new Score("Vadim", "Vadim@mail.ru", "python", 5);
        Score score9 = new Score("Vadim", "Vadim@mail.ru", "java", 5);

        exams.addScore(score1);
        exams.addScore(score2);
        exams.addScore(score3);
        exams.addScore(score4);
        exams.addScore(score5);
        exams.addScore(score6);
        exams.addScore(score7);
        exams.addScore(score8);
        exams.addScore(score9);

        Collection<Score> allScores = exams.getAllScores();
        Assertions.assertEquals(allScores.size(), 9);
    }
}