import java.util.*;

public class Tester {

    private Tree allAccountsTree;

    public Tester() {
        this.allAccountsTree = new Tree();
    }

    public static void main(String[] args) {
        Tester tester = new Tester(); //create an instance of Tester
        //return to the menu page util the user choose to exit
        boolean backFlag = false;
        while (!backFlag) {
            tester.showWelcome();
            backFlag = tester.processWelcome();
        }
    }

    public void showWelcome() {
        System.out.println("============Welcome to the initial page============");
        System.out.println("A.Register a new account");
        System.out.println("B.Sign in your account");
        System.out.println("C.Exit");
    }

    public boolean processWelcome() {
        System.out.println();
        System.out.println("Please enter the letter of desired operation:");
        Scanner sc = new Scanner(System.in);
        String option = sc.next();
        switch (option) {
            case "a":
            case "A":
                User account = register();
                System.out.println("Success register!");
                showUserInformation(account);
                break;
            case "b":
            case "B":
                User b = login(
                        allAccountsTree);
                if (b == null) {
                    System.out.println("Failed login in!");
                } else {
                    System.out.println("Successfully login in!");
                    Operator op = new Operator(b, allAccountsTree);
                    op.showMainPage();
//                    showUserInformation(b);

//                    boolean backFlag = false;
//                    while (!backFlag) {
//                        showUserCommand();
//                        backFlag = processCommand();
//                    }
                }
                break;
            case "c":
            case "C":
                return true;
            default:
                System.out.println("Please enter the correct letter");
                break;
        }
        return false;
    }

    //    private void showUserCommand() {
//        Scanner sc = new Scanner(System.in);
//        while (true) {
//            System.out.println("============Welcome to the operate page============");
//            System.out.println("1.Show your information");
//            System.out.println("2.Change your information");
//            System.out.println("3.Add a new friend");
//            System.out.println("4.Delete a friend");
//            System.out.println("5.Show all friends");
//            System.out.println("6.Find a friend");
//            System.out.println("7.My posts");
//            System.out.println("8.Friends posts");
//            System.out.println("9.Liked posts");
//            System.out.println("10.Log out");
//            System.out.println("Please enter the letter of desired operation:");
//            String option = sc.next();
//        }
//    }
    private User register() {
        System.out.println("============Welcome to the register page============");
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your ID(nickname):");
        String ID = sc.next();
        System.out.println("Please enter your name(real name):");
        String name = sc.next();
        int code;
        OUT:
        while (true) {
            System.out.println("Please enter your password(Only numbers allowed):");
            int code1 = sc.nextInt();
            System.out.println("Please confirm your password:");
            int code2 = sc.nextInt();
            if (code1 == code2) {
                code = code1;
                break OUT;
            } else {
                System.out.println("Inconsistent passwords,please enter again");
            }
        }
        int gender = 0;
        while (true) {
            System.out.println("Please enter your gender(M/W):");
            String genderInput = sc.next();
            if (genderInput.equalsIgnoreCase("W")) {
                gender = User.Woman;
                break;
            } else if (genderInput.equalsIgnoreCase("M")) {
                gender = User.Man;
                break;
            }
        }
        System.out.println("Please enter your hometown:");
        String hometown = sc.next();
        System.out.println("Please enter your work place:");
        String workPlace = sc.nextLine();
        int accountNumber = getAccountNumbers(allAccountsTree);
        User a = new User(ID,code,accountNumber,name,gender,hometown,workPlace);
        Tree myTree = new Tree();
        TreeNode me = new TreeNode(a);
        myTree.setRoot(me);
        a.setMyFriends(myTree);
        TreeNode nodeToAdd = new TreeNode(a);
        allAccountsTree.addToTree(nodeToAdd);
        return a;
    }

    private int getAccountNumbers(Tree accounts) {
        Random r = new Random();
        while (true) {
            String Numbers = "";
            for (int i = 0; i < 4; i++) {//暂时改成了四位数
                Numbers += r.nextInt(10);
            }
            int numbers = Integer.parseInt(Numbers);
            User a = getAccountByAccountNumbers(numbers, accounts);
            if (a == null) {
                return numbers;
            }
        }
    }

    private User getAccountByAccountNumbers(int accountNumbers, Tree accounts) {
        TreeNode nodeToSearch;
        nodeToSearch = accounts.findInTree(accountNumbers);
        if (nodeToSearch != null) {
            return nodeToSearch.getUser();
        }
        return null;
    }

    private User login(Tree accounts) {
        System.out.println("============Welcome to the login page============");
        Scanner sc = new Scanner(System.in);
        if (accounts.isTreeEmpty()) {
            System.out.println("There's no account in the system,please register first!");
            return null;
        } else {
            while (true) {
                System.out.println("Please enter your account number:");
                int userAccount = optimizedNextInt();
                int count = 0;
                User a = getAccountByAccountNumbers(userAccount, accounts);
                if (a == null) {
                    System.out.println("This account doesn't exist");
                } else {
                    System.out.println("Hello, " + a.getID());
                    while (count < 3) {
                        System.out.println("Please enter your password:\n" +
                                "(if there is more than three times wrong, please login again!)");
                        int code = sc.nextInt();
                        if (code == a.getPassword()) {
                            return a;
                        } else {
                            System.out.println("The code is wrong!");
                            count++;
                        }
                    }
                    System.out.println("More than three times! Please login again!");
                    return null;
                }
            }
        }
    }

    private void showUserInformation(User account) {
        System.out.println("===============================================");
        System.out.println("Your account number:" + account.getAccountNumber());
        System.out.println("Your ID:" + account.getID());
        System.out.println("Your name:" + account.getName());
        System.out.println("Your gender:" + account.getGender());
        System.out.println("Your hometown:" + account.getHometown());
        System.out.println("Your work:" + account.getWorkPlace());
        System.out.println("===============================================");
    }


    /**
     * an optimized method for continue input until an integer is entered
     *
     * @return the input integer
     */
    public static int optimizedNextInt() {
        Scanner sc = new Scanner(System.in);
        int num;
        try {
            num = sc.nextInt();
        } catch (Exception exception) {
            System.out.println("please input an integer:");
            //using recursion until user put in an integer
            return optimizedNextInt();
        }
        return num;
    }
}
