package oscilloscope.view;

import javafx.scene.image.Image;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import oscilloscope.Main;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import java.lang.Math;

import com.sun.javafx.geom.Rectangle;

import javafx.scene.input.MouseEvent;

import java.io.File;
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
	private ImageView pic;
	@FXML
	private Canvas figure;
	@FXML
	private Rectangle rectangle;
	private double qwe;
	
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
		gc.setFill(Color.BLACK);
		gc.setLineWidth(4.0);
		this.SetListeners();
		

		pic.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				pic.setRotate(mouseEvent.getSceneX() % 360);
				// System.out.println(mouseEvent.getX());
				System.out.println(pic.getRotate() );
				draw();
			}
		});

	}

	private void SetListeners() {
		//this.pic.rota.addListener((observable, oldValue, newValue) -> {
		//	draw();
		//});
		
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
		if (this.switchOn == true) {
			gc.clearRect(0, 0, 400, 500);
			for (int i = 0; i < 100000; i++) {
				gc.strokeOval((int) ((Math.sin(i * getFirstFrequency() + get()/100) + 1.2) * 100),
						(int) ((Math.cos(i * getSecondFrequency() + getSecondPhase()) + 1.0) * 100), 1, 1);
			}
		}
	}

	public double get() {
		return  pic.getRotate();
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
