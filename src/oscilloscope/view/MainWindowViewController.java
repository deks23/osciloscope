package oscilloscope.view;

import javafx.scene.image.Image;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import oscilloscope.Main;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import java.lang.Math;

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
	@FXML
	private Canvas figure;
	private GraphicsContext gc;
	private boolean switchOn;

	public MainWindowViewController() {

	}

	@FXML
	private void initialize() {
		this.firstFrequency.setValue(1.0);
		this.secondFrequency.setValue(1.0);
		this.firstPhase.setValue(1.0);
		this.secondPhase.setValue(1.0);

	}

	public void setMainApp(Main mainApp) {
		this.switchOn = false;
		this.mainApp = mainApp;
		this.gc = this.figure.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.setLineWidth(4.0);
		this.SetListeners();

	}

	private void setLab(int value) {
		this.lab.setText(Integer.toString(value));
	}

	private void SetListeners() {
		this.firstFrequency.valueProperty().addListener((observable, oldValue, newValue) -> {
			draw();
		});
		this.secondFrequency.valueProperty().addListener((observable, oldValue, newValue) -> {
			draw();
		});
		this.firstPhase.valueProperty().addListener((observable, oldValue, newValue) -> {
			draw();
		});
		this.secondPhase.valueProperty().addListener((observable, oldValue, newValue) -> {
			draw();
		});
	}

	private void draw() {
		if(this.switchOn==true) {
			gc.clearRect(0, 0, 400, 500);
			for (int i = 0; i < 100000; i++) {
				gc.strokeOval((int) ((Math.sin(i * getFirstFrequency() + getFirstPhase()) + 1.2) * 100),
						(int) ((Math.cos(i * getSecondFrequency() + getSecondPhase()) + 1.0) * 100), 1, 1);
			}
		}
	}

	public int getFirstFrequency() {
		return (int) this.firstFrequency.getValue();
	}

	public double getFirstPhase() {
		return this.firstPhase.getValue();
	}

	public int getSecondFrequency() {
		return (int) this.secondFrequency.getValue();
	}

	public double getSecondPhase() {
		return this.secondPhase.getValue();
	}

	private double getX(int time) {
		double q = 2 * time + getFirstPhase();
		double ssin = Math.sin(time * getFirstPhase());
		return (getFirstPhase() * ssin);
	}

	private double getY(int time) {
		double ssin = Math.sin(time);
		return (getSecondFrequency() * ssin);
	}

	@FXML
	private void buttonPress() {
		if (this.switchOn == true) {
			gc.clearRect(0, 0, 400, 500);
			this.switchOn = !this.switchOn;
		} else {
			this.switchOn = !this.switchOn;
			draw();

		}
	}

}
