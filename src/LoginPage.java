import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends Application {

    private Scene loginScene;
    private List<BaseUser> users = new ArrayList<>();

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

        // Build UI
        TextField emailField = new TextField();
        PasswordField passField = new PasswordField();
        Label messageLabel = new Label();
        Button loginBtn = new Button("Login");

        VBox layout = new VBox(10,
                new Label("Email:"), emailField,
                new Label("Password:"), passField,
                loginBtn, messageLabel);
        layout.setPadding(new Insets(20));
        layout.setId("login-pane");

        // Scene and CSS
        loginScene = new Scene(layout, 300, 250);
        // Ensure login.css is in your resources root (src/main/resources or src/) so this loads
        loginScene.getStylesheets()
                .add(getClass().getResource("/login.css").toExternalForm());

        // Login button action
        loginBtn.setOnAction(e -> {
            String email = emailField.getText();
            String password = passField.getText();

            BaseUser matchedUser = users.stream()
                    .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                    .findFirst()
                    .orElse(null);

            if (matchedUser != null) {
                Scene dashboardScene = HomePage.createHomeScene(primaryStage, loginScene, matchedUser);
                primaryStage.setScene(dashboardScene);
            } else {
                messageLabel.setText("Invalid credentials.");
                messageLabel.setStyle("-fx-text-fill: red;");
            }
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
