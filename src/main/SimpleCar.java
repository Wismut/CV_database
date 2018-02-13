package main;

public class SimpleCar extends AbstractCar {
	@Override
	public double getEngineVolume() {
		return 1.7;
	}

	@Override
	public void getDescription() {
		super.getDescription();
		System.out.println("Something custom");
	}
}
