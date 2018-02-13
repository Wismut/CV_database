package main;

public abstract class AbstractCar implements Car {
	protected int speed = 100;

	public void getDescription() {
		System.out.println("\n" + this.getClass().getSimpleName() + " Speed " + speed + ", EngineVolume: " + getEngineVolume());
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
