package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

	private static final double CIRCLE_RADIUS = 10;
	private static final double RECTANGLE_WIDTH = 200;
	private static final double RECTANGLE_HEIGHT = 500;

	private Pane pane;
	private FlowerBed container1;
	private FlowerBed container2;

	@Override
	public void start(Stage primaryStage) {
		pane = new Pane();

		// Create two rectangles (container1 and container2)
		container1 = new FlowerBed(50, 50, RECTANGLE_WIDTH, RECTANGLE_HEIGHT, Color.WHITE);
		container2 = new FlowerBed(300, 50, RECTANGLE_WIDTH, RECTANGLE_HEIGHT, Color.TAN);

		// Add circles to container rectangle1
		Flower circle1 = new Flower(70, 80, CIRCLE_RADIUS, Color.BLUE, container1, container2);
		Flower circle2 = new Flower(120, 80, CIRCLE_RADIUS, Color.RED, container1, container2);
		Flower circle3 = new Flower(170, 80, CIRCLE_RADIUS, Color.GREEN, container1, container2);
		Flower circle4 = new Flower(220, 80, CIRCLE_RADIUS, Color.YELLOW, container1, container2);

		container1.add(circle1);
		container1.add(circle2);
		container1.add(circle3);
		container1.add(circle4);

		// Add rectangles to the pane
		pane.getChildren().addAll(container1.getShape(), container2.getShape());
		for (GardenComponent component : container1.getChildren()) {
			if (component instanceof Node) {
				pane.getChildren().add((Node) component);
			}
		}
		for (GardenComponent component : container2.getChildren()) {
			if (component instanceof Node) {
				pane.getChildren().add((Node) component);
			}
		}

		// Add mouse event handlers for dragging the rectangles
		setRectangleDragEvent(container1);
		setRectangleDragEvent(container2);

		Scene scene = new Scene(pane, 600, 680);
		scene.setFill(Color.PALEGREEN);
		primaryStage.setTitle("Garden Layout");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void setRectangleDragEvent(FlowerBed container) {
		container.getShape().setOnMousePressed(event -> {
			// Remember the initial mouse position
			container.setInitialMousePosition(event.getSceneX(), event.getSceneY());
		});

		container.getShape().setOnMouseDragged(event -> {
			// Calculate the movement of the mouse
			double deltaX = event.getSceneX() - container.getInitialMouseX();
			double deltaY = event.getSceneY() - container.getInitialMouseY();

			// Move the rectangle and all its children (circle flowers) accordingly
			container.getShape().setX(container.getShape().getX() + deltaX);
			container.getShape().setY(container.getShape().getY() + deltaY);

			// Move circles within the container rectangles
			for (GardenComponent circle : container.getChildren()) {
				((Flower) circle).moveBy(deltaX, deltaY);
			}

			// Update the initial mouse position for the next drag event
			container.setInitialMousePosition(event.getSceneX(), event.getSceneY());
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}
