package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application{
    Label HammDist_Label;
    Button Show_Station;
    TextField HammDist_Field;
    Insets gridPadding;
    Slider slider;
    GridPane layout;
    Controller c = new Controller();

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hamming Distance");

        layout = new GridPane();
        layout.setAlignment(Pos.TOP_LEFT);



        HammDist_Label = new Label();
        HammDist_Label.setText("Enter Hamming Dist:");
        Font font = Font.font("Verdana", FontWeight.BOLD, 12);
        HammDist_Label.setFont(font);
        layout.add(HammDist_Label, 0, 0);



        slider = new Slider(1,4,1 );
        slider.setSnapToTicks(true);
        slider.setMajorTickUnit(4);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setValueChanging(true);
        layout.add(slider, 0,2);

        HammDist_Field = new TextField();
        HammDist_Field.setEditable(false);
        HammDist_Field.setPrefColumnCount(15);

        HammDist_Field.setText("9");
        layout.add(HammDist_Field, 1, 0);

        Show_Station = new Button();
        Show_Station.setText("Do not click me");
        Show_Station.setOnAction(e->System.out.println("WOW! It worked!"));
        //layout.add(Show_Station);

        gridPadding = new Insets(10,10,10,10);
        layout.setPadding(gridPadding);
        layout.setHgap(10);
        layout.setVgap(10);

        Scene scene = new Scene(layout);

        primaryStage.setScene(scene);

        primaryStage.show();
    }


    public static void main(String[] args) {
            launch(args);
    }

}
