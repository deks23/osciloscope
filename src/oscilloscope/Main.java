package oscilloscope;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import oscilloscope.view.MainWindowViewController;

import java.io.IOException;


public class Main extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;
	
	public Main() {
	

	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Malarze");
		this.primaryStage.setMinWidth(400);
		this.primaryStage.setMinHeight(600);
		initRootLayout();
	}




	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/MainWindowView.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);

			primaryStage.setScene(scene);
			primaryStage.show();
			MainWindowViewController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}






	public static void main(String[] args) {
		launch(args);

	}
}
