package org.example.students;

import org.example.students.exception.ScoreNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryExamination implements Examination {

    private final Map<String, Score> scores = new HashMap<>();

    @Override
    public void addScore(Score score) {
        scores.put(score.name() + score.lastname() + score.subject(), score);
    }

    @Override
    public Score getScore(String name, String lastname, String subject) throws ScoreNotFoundException {
        String key = name + lastname + subject;
        Score maybeScore = scores.get(key);
        if (maybeScore == null) {
            throw new ScoreNotFoundException("Score not found exception");
        }
        return scores.get(key);
    }

    @Override
    public double getAverageForSubject(String subject) {
        double sumScorersForSubject = 0;
        int count = 0;
        for (Map.Entry<String, Score> entries : scores.entrySet()) {
            if (entries.getValue().subject().equals(subject)) {
                sumScorersForSubject += entries.getValue().score();
                count++;
            }
        }
        double avgScore = getAverageForSubjectStream(subject);

        return avgScore;
    }

    public double getAverageForSubjectStream(String subject) {
        return scores.entrySet().stream()
                .filter(item -> item.getValue().subject().equals(subject))
                .map(item -> item.getValue().score())
                .mapToInt(Integer::valueOf)
                .average()
                .orElse(0.0);
    }

    @Override
    public Set<String> multipleSubmissionsStudentNames() {
        HashMap<String, Integer> studentScores = new HashMap<>();
        for (Map.Entry<String, Score> entries : scores.entrySet()) {
            String key = entries.getValue().name() + entries.getValue().lastname();

            Integer count = studentScores.getOrDefault(key, 0);
            studentScores.put(key, count + 1);
        }

        return studentScores.entrySet().stream()
                .filter(item -> item.getValue() > 1)
                .map(item -> item.getKey())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> lastFiveStudentsWithExcellentMarkOnAnySubject() {
        return scores.entrySet().stream()
                .filter(item -> item.getValue().score() == 5)
                .map(item -> item.getValue().name() + item.getValue().lastname())
                .sorted(Comparator.naturalOrder())
                .limit(5)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public Collection<Score> getAllScores() {
        return scores.entrySet().stream()
                .map(item -> item.getValue())
                .collect(Collectors.toList());
    }
}
