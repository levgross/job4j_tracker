package ru.job4j.map.attestation;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        double sum = 0D;
        int count = 0;
        for (var pupil : pupils) {
            var subjects = pupil.subjects();
            for (var subject : subjects) {
                sum += subject.score();
                count++;
            }
        }
        return count == 0 ? 0D : sum / count;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> resList = new ArrayList<>(pupils.size());
        for (var pupil : pupils) {
            var subjects = pupil.subjects();
            double sum = 0D;
            int count = 0;
            for (var subject : subjects) {
                sum += subject.score();
                count++;
            }
            resList.add(new Label(pupil.name(), count == 0 ? 0D : sum / count));
        }
        return resList;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        Map<String, Integer> subjectsMap = new LinkedHashMap<>();
        List<Label> subjectsList = new ArrayList<>();
        for (var pupil : pupils) {
            var subjects = pupil.subjects();
            for (var subject : subjects) {
                subjectsMap.merge(subject.name(), subject.score(), Integer::sum);
            }
        }
        for (var subject : subjectsMap.keySet()) {
            subjectsList.add(new Label(subject, subjectsMap.get(subject) / pupils.size()));
        }
        return subjectsList;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> resList = new ArrayList<>(pupils.size());
        for (var pupil : pupils) {
            var subjects = pupil.subjects();
            int sum = 0;
            for (var subject : subjects) {
                sum += subject.score();
            }
            resList.add(new Label(pupil.name(), sum));
        }
        resList.sort(Comparator.naturalOrder());
        return resList.get(resList.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        Map<String, Integer> subjectsMap = new LinkedHashMap<>();
        List<Label> subjectsList = new ArrayList<>();
        for (var pupil : pupils) {
            var subjects = pupil.subjects();
            for (var subject : subjects) {
                subjectsMap.merge(subject.name(), subject.score(), Integer::sum);
            }
        }
        for (var subject : subjectsMap.keySet()) {
            subjectsList.add(new Label(subject, subjectsMap.get(subject)));
        }
            subjectsList.sort(Comparator.naturalOrder());
            return subjectsList.get(subjectsList.size() - 1);
    }
}
