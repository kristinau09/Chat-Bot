import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
 @author Kristina
*/ 

public class Wind {
	
	private double speed;
	private int deg;

	public double getSpeed() {
	return speed;
	}

	public void setSpeed(double speed) {
	this.speed = speed;
	}

	public int getDeg() {
	return deg;
	}

	public void setDeg(int deg) {
	this.deg = deg;
	}

}
