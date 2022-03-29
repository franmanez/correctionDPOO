package edu.uoc.correction.model;

import java.util.HashMap;

public class TestNote {
    private String testName;
    private double score;
    private HashMap<String, Double> methodsNote = new HashMap<>();

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public HashMap<String, Double> getMethodsNote() {
        return methodsNote;
    }

    public void setMethodsNote(HashMap<String, Double> methodsNote) {
        this.methodsNote = methodsNote;
    }
}
