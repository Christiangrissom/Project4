package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Main extends Application{
    ListView StationList;
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
        Font HammDistLabelFont = Font.font("Verdana", FontWeight.BOLD, 12);
        HammDist_Label.setFont(HammDistLabelFont);
        layout.add(HammDist_Label, 0, 0);



        slider = new Slider(1,4,1 );
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(
                    ObservableValue<? extends Number> observableValue,
                    Number oldValue,
                    Number newValue) {
                HammDist_Field.setText(String.valueOf((int)slider.getValue()));
            }
        });

        slider.setSnapToTicks(true);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setValueChanging(true);
        layout.add(slider, 0,2);

        HammDist_Field = new TextField();
        HammDist_Field.setEditable(false);
        HammDist_Field.setPrefColumnCount(15);
        HammDist_Field.setText("1");
        layout.add(HammDist_Field, 1, 0);

        Show_Station = new Button();
        Show_Station.setText("Show Station");
        Show_Station.setOnAction(actionEvent -> {//change this to work for the next part of the code
            if(true){
            System.out.println("The button works, and so does the if statement.");
            }
        });
        layout.add(Show_Station,0,3);


        ArrayList<String> al = new ArrayList();//use a method or a class to simplify this
        al.add("STA1");
        al.add("STA2");
        al.add("STA3");
        al.add("STA4");
        al.remove("STA4");

        ObservableList<String> names = FXCollections.observableArrayList(al);
        StationList = new ListView<String>(names);
        StationList.setEditable(false);
        layout.add(StationList,0,4);


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
