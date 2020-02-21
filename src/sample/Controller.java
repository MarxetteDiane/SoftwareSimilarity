package sample;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;

public class Controller {

    @FXML
    Button search;
    @FXML
    GridPane gridPane;
    @FXML
    AnchorPane grid;
    @FXML
    AnchorPane ap;
    @FXML
    ScrollPane scrollPane;

    Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void test() {
        Stage primaryStage = new Stage();
        scrollPane = new ScrollPane(grid);
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(784,699);
        anchorPane.getChildren().add(scrollPane);

        Scene scene = new Scene(scrollPane,1000,699);
        primaryStage.setScene(scene);
        primaryStage.show();
        stage.close();
    }

    public void browse() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("/Users/marxette/Desktop"));
        File selectedFile = directoryChooser.showDialog(stage);

        GetFiles getFiles = new GetFiles();
        getFiles.setSelectedFile(selectedFile);
        List<File> allFiles = getFiles.getFiles();

        Similator similator = new Similator();
        Float[][] correlation = similator.Similator(allFiles);

        int width = 0;
        int height = 0;
        for (int i = 0; i < allFiles.size(); i++) {
            for (int j = 0; j < allFiles.size(); j++) {
                StackPane stackPane = new StackPane();
                Rectangle rect = new Rectangle();
                Text measure = new Text();
                rect.setWidth(95);
                rect.setHeight(35);
                stackPane.setLayoutX(width);
                stackPane.setLayoutY(height);
                width += 95;
                if (correlation[i][j] == 0) {
                    rect.setFill(Color.WHITE);
                } else if ((0 < correlation[i][j]) && (correlation[i][j] < 0.1)) {
                    rect.setFill(Color.LIGHTGREEN);
                } else if ((0.1 < correlation[i][j]) && (correlation[i][j] < 0.2)) {
                    rect.setFill(Color.LIGHTBLUE);
                } else if ((0.2 < correlation[i][j]) && (correlation[i][j] < 0.3)) {
                    rect.setFill(Color.LIGHTPINK);
                } else if ((0.3 < correlation[i][j]) && (correlation[i][j] < 0.4)) {
                    rect.setFill(Color.LIGHTSEAGREEN);
                } else if ((0.4 < correlation[i][j]) && (correlation[i][j] < 0.5)) {
                    rect.setFill(Color.LIGHTSTEELBLUE);
                } else if ((0.5 < correlation[i][j]) && (correlation[i][j] < 0.6)) {
                    rect.setFill(Color.LIGHTGOLDENRODYELLOW);
                } else if ((0.6 < correlation[i][j]) && (correlation[i][j] < 0.7)) {
                    rect.setFill(Color.LIGHTYELLOW);
                } else if ((0.7 < correlation[i][j]) && (correlation[i][j] < 0.8)) {
                    rect.setFill(Color.LIGHTCORAL);
                } else if ((0.8 < correlation[i][j]) && (correlation[i][j] < 0.99)) {
                    rect.setFill(Color.LIGHTCYAN);
                } else if (correlation[i][j] == 1) {
                    rect.setFill(Color.BLACK);
                }
                measure.setText(Float.toString(correlation[i][j]));
                stackPane.getChildren().addAll(rect,measure);
                ap.getChildren().add(stackPane);
            }
            width = 0;
            height += 35;
        }
    }

    public void search() {

    }
}
