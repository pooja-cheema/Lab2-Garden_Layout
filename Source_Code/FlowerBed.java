package application;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

// RectangleContainer class for holding circles (Composite Class)
public class FlowerBed implements GardenComponent {

	private Rectangle rectangle;
	private List<GardenComponent> children;
	private double initialMouseX, initialMouseY;

	public FlowerBed(double x, double y, double width, double height, Color color) {
		rectangle = new Rectangle(x, y, width, height);
		rectangle.setFill(color);
		rectangle.setStroke(Color.BLACK);

		children = new ArrayList<>();
	}

	public Rectangle getShape() {
		return rectangle;
	}

	public void add(GardenComponent circle) {
		children.add(circle);
	}

	public List<GardenComponent> getChildren() {
		return children;
	}

	public void setInitialMousePosition(double x, double y) {
		initialMouseX = x;
		initialMouseY = y;
	}

	public double getInitialMouseX() {
		return initialMouseX;
	}

	public double getInitialMouseY() {
		return initialMouseY;
	}

	@Override
	public void moveBy(double deltaX, double deltaY) {
		// Move the rectangle itself
		rectangle.setX(rectangle.getX() + deltaX);
		rectangle.setY(rectangle.getY() + deltaY);

		// Move each circle inside this container
		for (GardenComponent child : children) {
			child.moveBy(deltaX, deltaY);
		}
	}
}
