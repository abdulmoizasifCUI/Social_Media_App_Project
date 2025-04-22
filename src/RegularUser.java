public class RegularUser extends BaseUser {
    public RegularUser(String id, String username, String email, String password,String name, String city) {
        super(id, username, email, password,name,city);
    }

    @Override
    public String getRole() {
        return "Regular";
    }
}