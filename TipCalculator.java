//use property listeners to perform the calculations whenever the
//user modifies the bill amount or changes the custom tip percentage. Also
//uses a property binding to update the Label that displays the tip
//percentage.
// Main app class that loads and displays the Tip Calculator's GUI
package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TipCalculator extends Application {
	@Override
	public void start(Stage stage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("TipCalculator.fxml"));

		Scene scene = new Scene(root); // attach scene graph to scene
		stage.setTitle("Tip Calculator Part B"); // displayed in window's title bar
		stage.setScene(scene); // attach scene to stage
		stage.show(); // display the stage
	}

	public static void main(String[] args) {
	// create a TipCalculator object and call its start method
		launch(args);
	}
}