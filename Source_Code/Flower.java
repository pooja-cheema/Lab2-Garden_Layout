package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Flower extends Circle implements GardenComponent {

	private FlowerBed container1;
	private FlowerBed container2;

	public Flower(double x, double y, double radius, Color color, FlowerBed container1, FlowerBed container2) {
		super(x, y, radius, color);
		this.container1 = container1;
		this.container2 = container2;

		setStroke(Color.BLACK);

		// Set up click event for moving circles between containers
		setOnMouseClicked(event -> {
			// Check which container the circle belongs to
			if (this.container1.getChildren().contains(this)) {
				transferCircleToContainer2(this, container2); // Move to container2
			} else {
				transferCircleToContainer1(this, container1); // Move to container1
			}
		});
	}

	@Override
	public void moveBy(double deltaX, double deltaY) {
		setCenterX(getCenterX() + deltaX);
		setCenterY(getCenterY() + deltaY);
	}

	public FlowerBed getContainer1() {
		return container1;
	}

	public FlowerBed getContainer2() {
		return container2;
	}

	public void transferCircleToContainer2(Flower circle, FlowerBed targetContainer) {
		FlowerBed currentContainer1 = circle.container1;
		currentContainer1.getChildren().remove(circle);
		targetContainer.add(circle);

		// Update the circle's position to the new container's position
		double newX = circle.getCenterX() + targetContainer.getShape().getX() - currentContainer1.getShape().getX();
		double newY = circle.getCenterY() + targetContainer.getShape().getY() - currentContainer1.getShape().getY();

		circle.setCenterX(newX);
		circle.setCenterY(newY);

		// Add the circle to the new container's list of children
		targetContainer.getChildren().add(circle);
	}

	public void transferCircleToContainer1(Flower circle, FlowerBed targetContainer) {
		FlowerBed currentContainer2 = circle.container2;
		currentContainer2.getChildren().remove(circle);
		targetContainer.add(circle);

		// Update the circle's position to the new container's position
		double newX = circle.getCenterX() + targetContainer.getShape().getX() - currentContainer2.getShape().getX();
		double newY = circle.getCenterY() + targetContainer.getShape().getY() - currentContainer2.getShape().getY();

		circle.setCenterX(newX);
		circle.setCenterY(newY);

		// Add the circle to the new container's list of children
		targetContainer.getChildren().add(circle);
	}
}
