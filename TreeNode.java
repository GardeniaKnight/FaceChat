public class TreeNode {
    private User user;
    private TreeNode left;
    private TreeNode right;



    public TreeNode(){

    }
    public TreeNode(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
    public void printInfo(){
        String gender;
        if (user.getGender()==0){
            gender = "Man";
        }else gender = "Woman";
        System.out.println("Account number: "+user.getAccountNumber()+"\t\tID: "+user.getID()+"\t\tGender: "+gender+"\t\tHometown: "+user.getHometown()+"\t\tWorkplace: "+user.getWorkPlace());
    }

}
