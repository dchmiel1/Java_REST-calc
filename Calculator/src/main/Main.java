package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Klasa main rozpoczynająca działanie programu
 * @author Daniel_Chmielewiec
 *
 */
public class Main extends Application {

	/**
	 * Metoda rozpoczynająca działanie programu
	 * @param args Argumenty podane wraz z rozpoczęciem programu
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Metoda wyświetlająca okienko kalkulatora i ustawiająca jego podstawowe parametry
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/resources/fxml/CalcScreen.fxml"));
			VBox vbox = loader.load();
			Scene scene = new Scene(vbox);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Calculator");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
}