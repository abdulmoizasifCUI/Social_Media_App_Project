import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class SignUpPage {

    public static Scene createSignUpScene(Stage stage,
                                          Scene loginScene,
                                          List<BaseUser> users) {
        // Form fields
        TextField usernameField = new TextField();
        TextField emailField    = new TextField();
        PasswordField passField = new PasswordField();
        TextField nameField     = new TextField();
        TextField cityField     = new TextField();
        TextArea  bioArea       = new TextArea();
        bioArea.setPrefRowCount(3);

        Label message = new Label();
        Button createBtn = new Button("Create Account");
        Button backBtn   = new Button("Back to Login");

        // Layout
        VBox root = new VBox(8,
                new Label("Username:"),  usernameField,
                new Label("Email:"),     emailField,
                new Label("Password:"),  passField,
                new Label("Full Name:"), nameField,
                new Label("City:"),      cityField,
                new Label("Bio:"),       bioArea,
                createBtn, backBtn, message
        );
        root.setPadding(new Insets(20));
        root.setId("login-pane");

        // Apply same CSS
        Scene scene = new Scene(root, 350, 500);
        scene.getStylesheets()
                .add(SignUpPage.class.getResource("/login.css")
                        .toExternalForm());

        // Back button → login
        backBtn.setOnAction(e -> stage.setScene(loginScene));

        // Create Account logic
        createBtn.setOnAction(e -> {
            String uname = usernameField.getText().trim();
            String email = emailField.getText().trim();
            String pass  = passField.getText();
            String full  = nameField.getText().trim();
            String city  = cityField.getText().trim();
            String bio   = bioArea.getText().trim();

            // Simple validation
            if (uname.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                message.setText("Username, email & password are required.");
                return;
            }
            // Duplicate check
            boolean exists = users.stream().anyMatch(u ->
                    u.getEmail().equalsIgnoreCase(email) ||
                            u.getUsername().equalsIgnoreCase(uname)
            );
            if (exists) {
                message.setText("That email or username is already taken.");
                return;
            }

            // Create user
            RegularUser newUser = new RegularUser(
                    Integer.toString(users.size() + 1),
                    uname, email, pass, full, city
            );
            newUser.setBio(bio);
            users.add(newUser);

            // Go straight to HomePage
            Scene home = HomePage.createHomeScene(stage, loginScene, newUser);
            stage.setScene(home);
        });

        return scene;
    }
}
