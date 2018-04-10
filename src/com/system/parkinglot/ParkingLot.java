package com.system.parkinglot;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.system.parkinglot.model.Bus;
import com.system.parkinglot.model.Car;
import com.system.parkinglot.model.CompactSlot;
import com.system.parkinglot.model.LargeSlot;
import com.system.parkinglot.model.Motorbike;
import com.system.parkinglot.model.Slot;
import com.system.parkinglot.model.SmallSlot;
import com.system.parkinglot.model.Vehicle;

public class ParkingLot {
	private static final int NUMBER_OF_SMALL_SLOTS = 5;
	private static final int NUMBER_OF_COMPACT_SLOTS = 20;
	private static final int NUMBER_OF_LARGE_SLOTS = 2;
	private Stack<SmallSlot> smallSlots;
	private Stack<CompactSlot> compactSlots;
	private Stack<LargeSlot> largeSlots;
	public Map<Long, Slot> occupiedSlots;

	public static void main(String[] args) {
		ParkingLot lot=new ParkingLot();
		Car anvy=new Car("ANWESH");
		lot.parkVehicle(new Motorbike("jaffa1"));
		long key=lot.parkVehicle(anvy);
		lot.parkVehicle(new Motorbike("jaffa2"));
		lot.parkVehicle(new Motorbike("jaffa3"));
		lot.parkVehicle(new Motorbike("jaffa4"));
		lot.parkVehicle(new Motorbike("jaffa5"));
		lot.parkVehicle(new Motorbike("jaffa6"));
		lot.parkVehicle(new Bus("B1"));
		lot.parkVehicle(new Bus("B2"));
		System.out.println(lot.parkVehicle(new Bus("B3")));
		System.out.println("small slot count "+lot.smallSlots.size());
		System.out.println("large slot count "+lot.largeSlots.size());
		System.out.println("compact slot count "+lot.compactSlots.size());
		lot.unParkVehicle(key);
		System.out.println("compact slot count "+lot.compactSlots.size());
	}

	public ParkingLot() {
		occupiedSlots = new HashMap<>();
		createSlots();
	}

	private void createSlots() {
		smallSlots = new Stack<>();
		compactSlots = new Stack<>();
		largeSlots = new Stack<>();
		for (int i = 0; i < NUMBER_OF_SMALL_SLOTS; i++) {
			smallSlots.push(new SmallSlot(i));
		}
		for (int i = 0; i < NUMBER_OF_COMPACT_SLOTS; i++) {
			compactSlots.push(new CompactSlot(i));
		}
		for (int i = 0; i < NUMBER_OF_LARGE_SLOTS; i++) {
			largeSlots.push(new LargeSlot(i));
		}
	}

	public boolean unParkVehicle(long key) {
		if (!occupiedSlots.containsKey(key))
			return false;
		Slot slot = occupiedSlots.remove(key);
		if (SmallSlot.class.isInstance(slot))
			smallSlots.push((SmallSlot) slot);
		if (CompactSlot.class.isInstance(slot))
			compactSlots.push((CompactSlot) slot);
		else
			largeSlots.push((LargeSlot) slot);
		return true;
	}

	public long parkVehicle(Vehicle vehicle) {
		long uniqueToken = -1;
		if (vehicle == null)
			return uniqueToken;
		uniqueToken = vehicle.hashCode() * 43L;
		if (vehicle instanceof Motorbike) {
			if (!smallSlots.isEmpty()) {
				occupiedSlots.put(uniqueToken, smallSlots.pop());
			} else if (!compactSlots.isEmpty()) {
				occupiedSlots.put(uniqueToken, compactSlots.pop());
			} else if (!largeSlots.isEmpty()) {
				occupiedSlots.put(uniqueToken, largeSlots.pop());
			} else
				return -1;
		} else if (vehicle instanceof Car) {
			if (!compactSlots.isEmpty()) {
				occupiedSlots.put(uniqueToken, compactSlots.pop());
			} else if (!largeSlots.isEmpty()) {
				occupiedSlots.put(uniqueToken, largeSlots.pop());
			} else
				return -1;
		} 
		else {
			if (!largeSlots.isEmpty()) {
				occupiedSlots.put(uniqueToken, largeSlots.pop());
			}
			else return -1;
		}
		return uniqueToken;
	}
}
