package org.example.students;

import org.example.students.exception.ScoreNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CachedExaminationTest {

    private final MockExamination examination = new MockExamination();

    @BeforeEach
    void setUp() {
    }

    private final Examination cachedExamination = new CachedExamination(examination);

    @Test
    void callOnceForRepeatingRequest() {
        String request = "mathematics";

        double averageForSubject = cachedExamination.getAverageForSubject(request);

        Assertions.assertEquals(averageForSubject, 13.0 / 3);

        cachedExamination.getAverageForSubject(request);
        cachedExamination.getAverageForSubject(request);
        cachedExamination.getAverageForSubject(request);

        Assertions.assertEquals(1, examination.countCall);

        request = "linux";
        cachedExamination.getAverageForSubject(request);
        Assertions.assertEquals(2, examination.countCall);

        request = "linux";
        cachedExamination.getAverageForSubject(request);
        Assertions.assertEquals(2, examination.countCall);

        request = "history";
        cachedExamination.getAverageForSubject(request);
        Assertions.assertEquals(3, examination.countCall);
    }
}