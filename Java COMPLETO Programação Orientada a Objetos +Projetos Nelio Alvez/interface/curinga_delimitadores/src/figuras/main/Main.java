package figuras.main;

import java.util.ArrayList;
import java.util.List;

import figuras.entities.Circle;
import figuras.entities.Rectangle;
import figuras.entities.Shape;

public class Main {

	public static void main(String[] args) {
		List<Shape> myShapes = new ArrayList<Shape>();

		myShapes.add(new Rectangle(3.0, 2.0));
		myShapes.add(new Circle(2.0));

		List<Circle> myCircles = new ArrayList<>();
		myCircles.add(new Circle(2.0));
		myCircles.add(new Circle(3.0));

		System.out.println("Total area: " + totalArea(myCircles));
	}

	/*
	 * listas do tipo corringa nao sao listas corringa; listas corringa não podem
	 * adicionar novos elementos
	 */
	public static double totalArea(List<? extends Shape> list) {
		double sum = 0.0;
		for (Shape s : list) {
			sum += s.area();
		}

		return sum;
	}

}
