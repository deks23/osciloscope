package oscilloscope.view;

import javafx.scene.image.Image;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import oscilloscope.Main;
import javafx.scene.control.ComboBox;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainWindowViewController {
	private Main mainApp;
	@FXML
	private Slider firstFrequency;
	@FXML
	private Slider secondFrequency;
	@FXML
	private Slider firstPhase;
	@FXML
	private Slider secondPhase;
	@FXML
	private Label lab;

	public MainWindowViewController() {

	}

	@FXML
	private void initialize() {
		this.firstFrequency.setValue(2.2);
		this.SetListeners();

	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;

	}

	private void setLab(int value) {
		this.lab.setText(Integer.toString(value));
	}

	public double getFirstFrequency() {
		return firstFrequency.getValue();
	}

	private void SetListeners() {
		this.firstFrequency.valueProperty().addListener((observable, oldValue, newValue) -> {
			this.setLab(newValue.intValue());
		});
		this.secondFrequency.valueProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("SF");
		});
		this.firstPhase.valueProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("FP");
		});
		this.secondPhase.valueProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("SP");
		});
	}
}
