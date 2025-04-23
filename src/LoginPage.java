import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends Application {

    private Scene loginScene;
    private final List<BaseUser> users = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        // Create sample users
        users.add(new AdminUser("1", "admin", "admin@yahoo.com", "1234", "Admin", "Lahore"));
        BaseUser user1 = new RegularUser("2", "Sana", "sana@yahoo.com", "pass123", "Sana Javaid", "Islamabad");
        user1.addPost("💬 Feeling great today!");
        user1.addPost("📷 Just posted a new photo!");
        user1.addPost("📍 Hanging out at the beach.");
        users.add(user1);
        users.add(new RegularUser("3", "Sonia", "sonia@yahoo.com", "pass", "Sonia Amin", "Faisalabad"));
        // Test user for Moiz
        users.add(new RegularUser("4", "moiz", "moiz007@gmail.com", "moiz12345", "Moiz Khan", "Karachi"));

        // Build UI controls
        TextField emailField = new TextField();
        PasswordField passField = new PasswordField();
        Label messageLabel = new Label();
        Button loginBtn  = new Button("Login");
        Button signupBtn = new Button("Sign Up");

        // Layout for buttons
        HBox buttonBox = new HBox(10, loginBtn, signupBtn);

        // Main layout
        VBox layout = new VBox(10,
                new Label("Email:"),    emailField,
                new Label("Password:"), passField,
                buttonBox,
                messageLabel
        );
        layout.setPadding(new Insets(20));
        layout.setId("login-pane");

        // Scene with CSS
        loginScene = new Scene(layout, 300, 250);
        loginScene.getStylesheets()
                .add(getClass().getResource("/login.css").toExternalForm());

        // Login action
        loginBtn.setOnAction(e -> {
            String input    = emailField.getText().trim();
            String password = passField.getText();

            BaseUser matchedUser = users.stream()
                    .filter(u -> (u.getEmail().equalsIgnoreCase(input) || u.getUsername().equalsIgnoreCase(input))
                            && u.getPassword().equals(password))
                    .findFirst()
                    .orElse(null);

            if (matchedUser != null) {
                Scene home = HomePage.createHomeScene(primaryStage, loginScene, matchedUser);
                primaryStage.setScene(home);
            } else {
                messageLabel.setText("Invalid credentials.");
                messageLabel.setStyle("-fx-text-fill: red;");
            }
        });

        // Sign Up action
        signupBtn.setOnAction(e -> {
            Scene signupScene = SignUpPage.createSignUpScene(primaryStage, loginScene, users);
            primaryStage.setScene(signupScene);
        });

        // Show stage
        primaryStage.setTitle("Login");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}