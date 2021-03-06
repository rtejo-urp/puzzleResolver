package pieceRecognizer;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Point;

public class Corner implements Comparable<Corner> {
    private int    x;
    private int    y;
    private double grade;

    public Corner(int x, int y, double grade) {
	super();
	this.x = x;
	this.y = y;
	this.grade = grade;
    }

    public int getX() {
	return this.x;
    }

    public void setX(int x) {
	this.x = x;
    }

    public int getY() {
	return this.y;
    }

    public void setY(int y) {
	this.y = y;
    }

    public double getGrade() {
	return this.grade;
    }

    public void setGrade(double grade) {
	this.grade = grade;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	long temp;
	temp = Double.doubleToLongBits(this.grade);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	result = prime * result + this.x;
	result = prime * result + this.y;
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	Corner other = (Corner) obj;
	if (Double.doubleToLongBits(this.grade) != Double.doubleToLongBits(other.grade)) {
	    return false;
	}
	if (this.x != other.x) {
	    return false;
	}
	if (this.y != other.y) {
	    return false;
	}
	return true;
    }

    @Override
    public int compareTo(Corner o) {
	if (this.getGrade() > o.getGrade()) {
	    return 1;
	}
	if (this.getGrade() == o.getGrade()) {
	    return 0;
	}
	return -1;
    }

    public double distance(Corner corner) {

	return Math.sqrt(Math.pow(getX() - corner.getX(), 2) + Math.pow(getY() - corner.getY(), 2));
    }

    public static List<Point> toPoints(List<Corner> corners) {
	List<Point> points = new ArrayList<>();
	for (Corner corner : corners) {
	    points.add(corner.getPoint());
	}
	return points;
    }

    private Point getPoint() {
	return new Point(getX(), getY());
    }

}
