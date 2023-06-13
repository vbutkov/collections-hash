package org.example.students;

import org.example.students.exception.ScoreNotFoundException;

import java.util.*;

public class CachedExamination implements Examination {

    private final Map<String, Double> cache = new LRUCache<>(2);

    private final MockExamination mockExamination;

    public CachedExamination(MockExamination examination) {
        mockExamination = examination;
    }

    @Override
    public void addScore(Score score) {
        mockExamination.addScore(score);
    }

    @Override
    public Score getScore(String name, String lastname, String subject) throws ScoreNotFoundException {
        return mockExamination.getScore(name, lastname, subject);
    }

    @Override
    public double getAverageForSubject(String subject) {
        return cache.computeIfAbsent(subject, mockExamination::getAverageForSubject);
    }

    @Override
    public Set<String> multipleSubmissionsStudentNames() {
        return mockExamination.multipleSubmissionsStudentNames();
    }

    @Override
    public Set<String> lastFiveStudentsWithExcellentMarkOnAnySubject() {
        return mockExamination.lastFiveStudentsWithExcellentMarkOnAnySubject();
    }

    @Override
    public Collection<Score> getAllScores() {
        return mockExamination.getAllScores();
    }

}

class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}
