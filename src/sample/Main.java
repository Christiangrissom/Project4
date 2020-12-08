package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application{

    Integer Hamm_Dist = 1;
    Button Show_Station;
    TextField HammDist_Field;
    Label HammDist_Label;
    Insets gridPadding;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hamming Distance");

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.TOP_LEFT);



        HammDist_Label = new Label();
        HammDist_Label.setText("Enter Hamming Dist:");
        layout.add(HammDist_Label, 0, 0);

        HammDist_Field = new TextField();
        HammDist_Field.setEditable(false);
        HammDist_Field.setPrefColumnCount(15);
        HammDist_Field.setText(Integer.toString(Hamm_Dist));
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
