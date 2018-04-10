package com.system.parkinglot.model;

public class Vehicle {
	private String licensePlate;
	private Color color;

	public enum Color {
		RED(1), BLACK(2), WHITE(3), GREY(4);
		int value;

		Color(int val) {
			value = val;
		}
	}
	public Vehicle(String plate) {
		licensePlate=plate;
	}
	/**
	 * @return the licensePlate
	 */
	public String getLicensePlate() {
		return licensePlate;
	}

	/**
	 * @param licensePlate
	 *            the licensePlate to set
	 */
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

}
