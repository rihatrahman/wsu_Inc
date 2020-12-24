/**
 * Class for Users
 * @author rihat rahman
 */
public class User {

    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String category;

    /**
     * Constructor
     * @param firstname first name of user
     * @param lastname last name of user
     * @param username username
     * @param password password
     * @param category category of user (owner/admin/inventory manager/purchaser)
     */
    public User(String firstname, String lastname, String username, String password, String category) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.category = category;
    }

    /**
     * Default Constructor
     */
    public User() {
        this.firstname = "";
        this.lastname = "";
        this.username = "";
        this.password = "";
        this.category = "";
    }

    /**
     * @return first name of user
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * set first name of user
     * @param firstname first name of user
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return last name of user
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * set last name of user
     * @param lastname last name of user
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * set username
     * @param username username of user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password of user
     */
    public String getPassword() {
        return password;
    }

    /**
     * set password
     * @param password password of user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return category of user
     */
    public String getCategory() {
        return category;
    }

    /**
     * set category of user (owner/admin/inventory manager/purchaser)
     * @param category category of user
     */
    public void setCategory(String category) {
        this.category = category;
    }
}