package edu.uoc.correction.model;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * @author Fran Manez
 * @version 1.0
 */
@XmlRootElement(name = "testcase")
@XmlAccessorType(XmlAccessType.FIELD)
public class Testcase implements Serializable {
    @XmlAttribute
    private String name;

    @XmlAttribute
    private String classname;

    @XmlAttribute
    private double time;

    private int successful;

    private Failure failure;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getSuccessful() {
        return successful;
    }

    public void setSuccessful(int successful) {
        this.successful = successful;
    }

    public Failure getFailure() {
        return failure;
    }

    public void setFailure(Failure failure) {
        this.failure = failure;
    }

    @Override
    public String toString() {
        if(failure != null) return "Testcase [name=" + name + ", classname=" + classname + ", \nfailure=" + failure + "]";
        else return "Testcase [name=" + name + ", classname=" + classname + "]";
    }
}
