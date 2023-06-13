package org.example.students;

import org.example.students.exception.ScoreNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class MockExamination implements Examination{

        public int countCall = 0;

        @Override
        public void addScore(Score score) {

        }

        @Override
        public Score getScore(String name, String lastname, String subject) throws ScoreNotFoundException {
            return null;
        }

        @Override
        public double getAverageForSubject(String subject) {
            Collection<Score> allScores = getAllScores();

            return allScores.stream()
                    .filter(item -> item.subject().equals(subject))
                    .map(item -> item.score())
                    .mapToInt(Integer::valueOf)
                    .average()
                    .orElse(0.0);
        }

        @Override
        public Set<String> multipleSubmissionsStudentNames() {
            return null;
        }

        @Override
        public Set<String> lastFiveStudentsWithExcellentMarkOnAnySubject() {
            return null;
        }

        @Override
        public Collection<Score> getAllScores() {
            countCall++;
            Score score1 = new Score("Vadim", "Vadim@mail.ru", "mathematics", 4);
            Score score2 = new Score("Vadim", "Vadim@mail.ru", "linux", 5);
            Score score3 = new Score("Vladimir", "Vladimir@mail.ru", "mathematics", 5);
            Score score4 = new Score("Irina", "Irina@@mail.ru", "history", 5);
            Score score5 = new Score("Irina", "Irina@@mail.ru", "mathematics", 4);

            return List.of(score1, score2, score3, score4, score5);
        }

}
