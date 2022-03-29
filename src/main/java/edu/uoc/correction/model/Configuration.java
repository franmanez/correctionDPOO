package edu.uoc.correction.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class Configuration {
    private String directoryStudentsPACs;
    private String name;
    TreeMap<String, ExerciceConfiguration> mapExercisesTests;
    private double scorePAC;

    public Configuration(){
        mapExercisesTests = new TreeMap<>();

        Scanner sc = null;
        double notePAC = 0;
        try {
            sc = new Scanner(new File(System.getProperty("user.dir") + "/src/main/resources/config.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert sc != null;
        this.setDirectoryStudentsPACs(sc.nextLine().split("=")[1].trim());
        this.setName(sc.nextLine().split("=")[1].trim());
        while(sc.hasNext()) {

            String[] splitMethodsNote = sc.nextLine().split("#");
            String tests = splitMethodsNote[0];
            String methods = splitMethodsNote.length == 2 ? splitMethodsNote[1] : null;

            String[] split = tests.split("=");
            String exerciseName = split[0].trim();
            String[] splitTests = split[1].split(",");
            ExerciceConfiguration exerciceConfiguration = new ExerciceConfiguration();
            List<TestNote> listTestNotes = new ArrayList<>();
            for(String test : splitTests){

                String[] splitTestNote = test.split(":");
                TestNote testNote = new TestNote();
                testNote.setTestName(splitTestNote[0].trim());
                testNote.setScore(Double.parseDouble(splitTestNote[1].trim()));

                if(methods != null){
                    exerciceConfiguration.setScoreByMethods(true);
                    String[] splitMethods = methods.split(",");
                    for(String method : splitMethods){
                        String methodName = method.split(":")[0].trim();
                        Double methodScore = Double.parseDouble(method.split(":")[1].trim());
                        testNote.getMethodsNote().put(methodName, methodScore);
                    }
                }

                listTestNotes.add(testNote);
                notePAC += testNote.getScore();

            }
            exerciceConfiguration.setListTestNote(listTestNotes);
            this.getMapExercisesTests().put(exerciseName, exerciceConfiguration);
        }
        this.setScorePAC(notePAC);

    }

    public String getDirectoryStudentsPACs() {
        return directoryStudentsPACs;
    }

    public void setDirectoryStudentsPACs(String directoryStudentsPACs) {
        this.directoryStudentsPACs = directoryStudentsPACs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreeMap<String, ExerciceConfiguration> getMapExercisesTests() {
        return mapExercisesTests;
    }

    /*public void setMapExercisesTests(TreeMap<String, List<TestNote>> mapExercisesTests) {
        this.mapExercisesTests = mapExercisesTests;
    }*/

    public double getScorePAC() {
        return scorePAC;
    }

    public void setScorePAC(double scorePAC) {
        this.scorePAC = scorePAC;
    }
}
