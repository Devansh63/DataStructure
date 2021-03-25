package Project1_DevanshAgrawalCS260;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

public class GarageSet implements Serializable {

	private CarDataNode head;
	private CarDataNode tail;
	private int size;
	private GarageExitBag garageExitbag;

	public GarageSet() { // COnstructor
		head = null;
		tail = null;
		size = 0;
		garageExitbag = new GarageExitBag();
	}

	public boolean chechkin(String licensePlate) { // To check in a car in to the garage
		if (head == null) {
			size++;
			head = new CarDataNode(licensePlate, size, null, null);
			head.checkIn();
		} else if (CarDataNode.searchFromNode(head, licensePlate) == null) {
			if (tail == null) {
				tail = head;

				while (tail.getNext() != null) {
					tail = tail.getNext();
				}

				size++;
				tail = tail.addNodeAfter(licensePlate, size);
				tail.checkIn();
			} else {
				size++;
				tail = tail.addNodeAfter(licensePlate, size);
				tail.checkIn();
			}
		} else {
			return false;
		}
		return true;

	}

	public boolean checkOut(String licensePlate) { // TO checkout a car from the garage
		CarDataNode targetCar = CarDataNode.searchFromNode(head, licensePlate);
		if (targetCar != null) {
			if (targetCar.getNext() == null) {
				size--;

				if (targetCar == head) {
					targetCar.checkOut();
					head = null;

					garageExitbag.addNodeAfter(targetCar);
				} else {
					targetCar.checkOut();
					targetCar.getPrev().setIndex(size);
					targetCar.getPrev().setNext(null);
					tail = targetCar.getPrev();

					garageExitbag.addNodeAfter(targetCar);
				}
			} else {
				size--;

				CarDataNode cursor = targetCar;
				if (targetCar == head) {
					while (cursor.getNext() != null) {
						cursor = cursor.getNext();
						cursor.setIndex(cursor.getIndex() - 1);
					}

					targetCar.checkOut();
					targetCar.getNext().setPrev(null);
					head = targetCar.getNext();

					garageExitbag.addNodeAfter(targetCar);

				} else {
					while (cursor.getNext() != null) {
						cursor = cursor.getNext();
						cursor.setIndex(cursor.getIndex() - 1);
					}

					targetCar.checkOut();
					targetCar.removeNode();

					garageExitbag.addNodeAfter(targetCar);
				}
			}
		} else {
			return false;

		}
		return true;
	}

	public String toString() { // TO print out the node
		String string = " ";
		CarDataNode cursor = head;
		while (cursor != null) {
			string += cursor.toString();
			cursor = cursor.getNext();
		}
		return string;
	}

	public static void saveGsData(GarageSet garageSet) throws IOException { // saves the data
		PrintWriter saveFile = new PrintWriter("Garage Set.txt");
		saveFile.print(garageSet.toString());

		FileOutputStream fileOutPutStream = new FileOutputStream("Garage Set.txt");
		ObjectOutputStream objectOutputStram = new ObjectOutputStream(fileOutPutStream);

		objectOutputStram.writeObject(garageSet);

		objectOutputStram.flush();
		objectOutputStram.close();
		saveFile.flush();
		saveFile.close();
	}

//	public static GarageSet loadGCDate() throws IOException, ClassNotFoundException {  // Could not use this as it caused an error
//		GarageSet garageSet = null;													   //Error: Unable to initialize main class Project2_DevanshAgrawalCS260.Driver 
//		FileInputStream fileInputStream = new FileInputStream("Garage Set.txt");	   //Caused by: java.lang.NoClassDefFoundError: ClassNotFoundException	
//		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//		
//		garageSet = (GarageSet) objectInputStream.readObject();
//		
//		fileInputStream.close();
//		objectInputStream.close();
//		
//
//		return garageSet;
//	}

	public GarageExitBag getGarageExitbag() { // Auto generated getters and setters.
		return garageExitbag;
	}

	public void setGarageExitbag(GarageExitBag garageExitbag) {
		this.garageExitbag = garageExitbag;
	}

}
