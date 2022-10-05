package dad.imc;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private VBox root;

    private HBox pesoHbox;
    private TextField pesoTextField;

    private HBox alturaHbox;
    private TextField alturaTextField;

    private Label imcLabel;
    private Label resultadoLabel;

    @Override
    public void start(Stage stage) throws IOException {
        this.root = new VBox();

        this.pesoHbox = new HBox();
        this.pesoTextField = new TextField();
        pesoHbox.getChildren().addAll(
                new Label("Peso: "),
                pesoTextField,
                new Label(" kg")
        );
        pesoTextField.textProperty().addListener(this::calcularImc);

        this.alturaHbox = new HBox();
        this.alturaTextField = new TextField();
        alturaHbox.getChildren().addAll(
                new Label("Altura: "),
                alturaTextField,
                new Label(" cm")
        );
        alturaTextField.textProperty().addListener(this::calcularImc);

        this.imcLabel = new Label("IMC: (peso * altura^ 2)");
        this.resultadoLabel = new Label("Bajo peso | Normal | Sobrepeso | Obeso");

        root.getChildren().addAll(pesoHbox, alturaHbox, imcLabel, resultadoLabel);
        root.setAlignment(Pos.CENTER);
        root.setFillWidth(false);
        root.setSpacing(5);

        Scene scene = new Scene(root, 320, 200);

        stage.setTitle("HolaFX");
        stage.setScene(scene);
        stage.show();
    }

    private void calcularImc(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        float peso;
        float altura;
        try {
            peso = Integer.parseInt(pesoTextField.getText());
            altura = Integer.parseInt(alturaTextField.getText());
        } catch (Exception e) {
            return;
        }

        altura /= 100f;

        if (altura <= 0)
            return;

        float imc = peso / (altura * altura);
        imcLabel.setText("IMC: " + String.format("%2f", imc));

        if (imc < 18.5) {
            resultadoLabel.setText("Bajo peso");
        } else if (imc < 25) {
            resultadoLabel.setText("Normal");
        } else if (imc < 30) {
            resultadoLabel.setText("Sobrepeso");
        } else {
            resultadoLabel.setText("Obeso");
        }
    }

    public static void main(String[] args) {
        launch();
    }

}