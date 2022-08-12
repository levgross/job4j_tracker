package ru.job4j.map.attestation;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        return pupils.stream()
                .flatMap(pupil -> pupil.subjects().stream())
                .mapToDouble(Subject::score)
                .average()
                .orElse(0D);
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        return pupils.stream()
                .flatMap(pupil -> pupil.subjects().stream()
                        .mapToDouble(Subject::score)
                        .average()
                        .stream().mapToObj(score -> new Label(pupil.name(), score)))
                .collect(Collectors.toList());
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {

        return pupils.stream()
                .flatMap(pupil -> pupil.subjects().stream())
                .collect(Collectors.groupingBy(
                        Subject::name,
                        LinkedHashMap::new,
                        Collectors.averagingDouble(Subject::score)))
                        .entrySet().stream()
                        .map(v -> new Label(v.getKey(), v.getValue()))
                        .collect(Collectors.toList());
    }

    public static Label bestStudent(List<Pupil> pupils) {
        return pupils.stream()
                .map(pupil -> new Label(
                        pupil.name(), pupil.subjects().stream()
                        .mapToDouble(Subject::score)
                        .sum()))
                .max(Comparator.comparing(Label::score))
                .orElse(null);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        return pupils.stream()
                .flatMap(pupil -> pupil.subjects().stream())
                .collect(Collectors.groupingBy(
                        Subject::name,
                        LinkedHashMap::new,
                        Collectors.summingDouble(Subject::score)))
                .entrySet().stream()
                .map(v -> new Label(v.getKey(), v.getValue()))
                .max(Comparator.comparing(Label::score))
                .orElse(null);
    }
}
