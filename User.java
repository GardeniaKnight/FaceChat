public class User {
    private String ID;
    private int password;
    private int accountNumber;
    private String name;
    private int gender;
    public final static int Man = 0;
    public final static int Woman = 1;

    private String hometown;
    private String workPlace;
    private Post[] myPosts;
    private Post[] myLikedPosts;
    private Tree myFriends;

    public User() {
    }

    public User(String ID, int password, int accountNumber, String name, int gender, String hometown, String workPlace) {
        this.ID = ID;
        this.password = password;
        this.accountNumber = accountNumber;
        this.name = name;
        this.gender = gender;
        this.hometown = hometown;
        this.workPlace = workPlace;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public Post[] getMyPosts() {
        return myPosts;
    }

    public void setMyPosts(Post[] myPosts) {
        this.myPosts = myPosts;
    }

    public Post[] getMyLikedPosts() {
        return myLikedPosts;
    }

    public void setMyLikedPosts(Post[] myLikedPosts) {
        this.myLikedPosts = myLikedPosts;
    }

    public Tree getMyFriends() {
        return myFriends;
    }

    public void setMyFriends(Tree myFriends) {
        this.myFriends = myFriends;
    }
}
