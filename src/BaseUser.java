import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BaseUser {
    protected String userId;
    protected String username;
    protected String email;
    protected String password;
    protected String bio;

    protected String name;
    protected String city;

    // Social features
    protected List<BaseUser> friends;
    protected List<String> posts; // you can later make this a Post class

    public BaseUser(String userId, String username, String email, String password,String name,String city) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.bio = "";
        this.friends = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    // Getters
    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getBio() {
        return bio;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPosts(List<String> posts) {
        this.posts = posts;
    }

    public List<BaseUser> getFriends() {
        return friends;
    }

    public void setFriends(List<BaseUser> friends) {
        this.friends = friends;
    }

    public List<String> getPosts() {
        return posts;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // Behavior
    public void addFriend(BaseUser user) {
        if (!friends.contains(user)) {
            friends.add(user);
        }
    }
    public void addPost(String content) {
        posts.add(content);
    }

    public String getRole() {
        return "User";
    }
}