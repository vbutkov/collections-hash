package org.example.students;

import org.example.students.exception.ScoreNotFoundException;

import java.util.Collection;
import java.util.Set;

public interface Examination {
    void addScore(Score score);

    Score getScore(String name, String lastname, String subject) throws ScoreNotFoundException;

    double getAverageForSubject(String subject);

    Set<String> multipleSubmissionsStudentNames();

    Set<String> lastFiveStudentsWithExcellentMarkOnAnySubject();

    Collection<Score> getAllScores();
}
