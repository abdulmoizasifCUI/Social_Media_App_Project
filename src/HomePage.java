import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomePage {

    public static Scene createHomeScene(Stage stage, Scene loginScene,BaseUser user) {
        Label welcome = new Label("Welcome, " + user.getName());
        Label role = new Label("Role: " + user.getRole());
        Button logout = new Button("Logout");
        Button profile = new Button("View Profile");

        logout.setOnAction(e -> stage.setScene(loginScene));
        profile.setOnAction(e -> {
            Scene profileScene = ProfilePage.createProfileScene(stage, user, loginScene);
            stage.setScene(profileScene);
        });

        VBox layout = new VBox(15, welcome, role, profile, logout);
        layout.setPadding(new Insets(20));

        return new Scene(layout, 300, 200);
    }
}
