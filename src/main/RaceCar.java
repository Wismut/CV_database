package main;

public class RaceCar extends AbstractCar {
	public RaceCar() {
		speed = 200;
	}

	@Override
	public double getEngineVolume() {
		return 3.5;
	}
}
