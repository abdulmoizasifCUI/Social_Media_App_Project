import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ProfilePage {

    public static Scene createProfileScene(Stage stage, BaseUser user, Scene previousScene) {
        Label nameLabel = new Label("Name: " + user.getName());
        Label emailLabel = new Label("Email: " + user.getEmail());
        Label bioLabel = new Label("Bio: " + user.getBio());

        // List of posts
        Label postLabel = new Label("Posts:");
        ListView<String> postList = new ListView<>();
        postList.getItems().addAll(user.getPosts());

        // Buttons
        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> stage.setScene(previousScene));

        VBox layout = new VBox(10,
                nameLabel,
                emailLabel,
                bioLabel,
                postLabel,
                postList,
                backBtn);
        layout.setPadding(new Insets(20));

        return new Scene(layout, 400, 500);
    }
}
