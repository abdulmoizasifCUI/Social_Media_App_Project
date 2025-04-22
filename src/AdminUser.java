public class AdminUser extends BaseUser {
    public AdminUser(String id, String username, String email, String password,String name, String city) {
        super(id, username, email, password, name, city);
    }

    @Override
    public String getRole() {
        return "Admin";
    }

    public void banUser(BaseUser user) {
        // Example admin functionality
        System.out.println("User " + user.getUsername() + " has been banned.");
    }
}
