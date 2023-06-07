import javax.swing.text.html.HTMLDocument;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Operator {
    private User user;
    private Tree allAccountsTree;

    public Operator(User user, Tree allAccountsTree) {
        this.user = user;
        this.allAccountsTree = allAccountsTree;
    }

    public void showMainPage() {
        boolean backFlag = false;
        while (!backFlag) {
            System.out.println("===========Welcome to Main Page===========");
            System.out.println("1.Information");
            System.out.println("2.Friends");
            System.out.println("3.Posts");
            System.out.println("4.Log out");
            backFlag = processMain();
        }
    }

    private boolean processMain() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please choose an option");
        String choice = sc.next();
        switch (choice) {
            case "1":
                showUserInformation();
                break;
            case "2":
                showFriends();
                break;
            case "3":
                showPostPrompt();
                return true;
            default:
                System.out.println("The input is invalid,please re-enter it.");
        }
        return false;
    }

    private void showUserInformation() {
        System.out.println("===============================================");
        System.out.println("Your ID number:" + user.getAccountNumber());
        System.out.println("Your name:" + user.getName());
        System.out.println("Your hometown:" + user.getHometown());
        System.out.println("Your work:" + user.getWorkPlace());
        System.out.println("===============================================");
        System.out.println("Do you want to make some changes? yes/no");
        Scanner sc = new Scanner(System.in);
        if (sc.next().equalsIgnoreCase("yes")) {
            System.out.println("===========Welcome to Modify Page===========");
            OUT:
            while (true) {
                System.out.println("What do you want to change?");
                System.out.println("1.Name");
                System.out.println("2.Password");
                System.out.println("3.Work");
                System.out.println("4.Hometown");
                System.out.println("5.Back");
                String opt = sc.next();
                switch (opt) {
                    case "1":
                        System.out.println("Please enter your new name:");
                        String name = sc.next();
                        user.setName(name);
                        break;
                    case "2":
                        System.out.println("Please enter your old Password:");
                        int code = optimizedNextInt();
                        if (code == user.getPassword()) {
                            System.out.println("Please enter your new Password:");
                            int newCode = optimizedNextInt();
                            user.setPassword(newCode);
                        } else {
                            System.out.println("Wrong code!");
                        }
                        break;
                    case "3":
                        System.out.println("Please enter your new work:");
                        String work = sc.next();
                        user.setName(work);
                        break;
                    case "4":
                        System.out.println("Please enter your new hometown:");
                        String hometown = sc.next();
                        user.setName(hometown);
                        break;
                    case "5":
                        break OUT;
                    default:
                        System.out.println("Invalid input");
                }
            }

        } else if (sc.next().equalsIgnoreCase("no")) {

        } else {
            System.out.println("Something invalid");
        }
    }

    private void showFriends() {//展示选2之后的界面
        boolean backFlag = false;
        while (!backFlag) {
            System.out.println("==========Options of Friends==========");
            System.out.println("1.Display friends");
            System.out.println("2.Search");
            System.out.println("3.Friend recommendations");
            System.out.println("4.Back");
            backFlag = processFriends(allAccountsTree, user);
        }
    }

    public boolean processFriends(Tree tree, User me) {
        FriendOptions fo = new FriendOptions();
        Scanner sc = new Scanner(System.in);
        System.out.println("Please choose an option:");
        String choice = sc.next();
        switch (choice) {
            case "1":
                showFriendsDisplay(tree,me.getMyFriends());
                break;
            case "2":
                System.out.println("Please enter the account you want to find");
                int accountNumber = sc.nextInt();
                TreeNode nodeToSearch;
                nodeToSearch = tree.findInTree(accountNumber);
                if (nodeToSearch == null) {
                    System.out.println("Sorry, we cannot find this account.");
                } else {
                    fo.showThisPersonInformation(nodeToSearch);
                }
                break;
            case "3":
                showFriendRecommendations(me);
                break;
            case "4":
                return true;
            default:
                System.out.println("The input is invalid,please re-enter it.");
        }
        return false;
    }
    public void showFriendRecommendations(User me){
        boolean backFlag = false;
        while (!backFlag) {
        TreeNode p = me.getMyFriends().getRoot();
        if (p!=null){
            me.getMyFriends().showTreeInOrder(p.getLeft());
            showStrangers(p.getUser(),me);
            me.getMyFriends().showTreeInOrder(p.getRight());
        }
        System.out.println();
        System.out.println("1.Add a stranger");
        System.out.println("2.Back");
            backFlag = processFriendRecommendations(allAccountsTree,me.getMyFriends());
        }
    }
    public void showStrangers(User myFriend,User me){
        TreeNode p = myFriend.getMyFriends().getRoot();
        if (p!=null){
            myFriend.getMyFriends().showTreeInOrder(p.getLeft());
            if (me.getMyFriends().findInTree(p.getUser().getAccountNumber()) == null){
                p.printInfo();
            }
            myFriend.getMyFriends().showTreeInOrder(p.getRight());
        }
    }

    public boolean processFriendRecommendations(Tree users,Tree myFriends){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please choose an option:");
        String choice = sc.next();
        switch (choice){
            case "1":
                System.out.println("Please enter the account number.");
                boolean flag2 = false;
                while (!flag2) {
                    String input = sc.next();
                    try {
                        Integer number = Integer.valueOf(input);
                        TreeNode nodeToFind = users.findInTree(number);
                        if (nodeToFind == null) {
                            System.out.println("The account number can not be found,please re-enter it.");
                            continue;
                        } else {
                            addAStranger(myFriends.getRoot().getUser(),nodeToFind.getUser());
                            System.out.println("Successfully added!");
                            return true;
                        }
                    }catch(Exception exception) {
                        System.out.println("The input is invalid,please re-enter it.");
                        continue;
                    }
                }
                break;
            case "2":
                return true;
        }
        return false;
    }
    public void showFriendsDisplay(Tree users,Tree myFriends) {
        boolean backFlag = false;
        while (!backFlag) {
            System.out.println("==========Options of Displaying Friends");
            System.out.println("1.Display all friends");
            System.out.println("2.Display friends in the same hometown");
            System.out.println("3.Display friends in the same work place");
            System.out.println("4.Back");
            backFlag = processFriendsDisplay(users,myFriends);
        }
    }

    public boolean processFriendsDisplay(Tree users,Tree myFriends) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter an option:");
        String choice = sc.next();
        switch (choice) {
            case "1":
                boolean backFlag1 = false;
                while (!backFlag1) {
                    myFriends.showTreeInOrder(myFriends.getRoot());
                    backFlag1 = processFriends(users,myFriends);
                }
                break;
            case "2":
                boolean backFlag2 = false;
                while (!backFlag2) {
                    showFriendsHometown(myFriends.getRoot(),myFriends.getRoot().getUser());
                    backFlag2 = processFriends(users,myFriends);
                }
                break;
            case "3":
                boolean backFlag3 = false;
                while (!backFlag3) {
                    showFriendsWorkPlace(myFriends.getRoot(),myFriends.getRoot().getUser());
                    backFlag3 = processFriends(users,myFriends);
                }
                break;
            case "4":
                return true;
            default:
                System.out.println("The input is invalid,please re-enter it.");
        }
        return false;
    }

    public boolean processFriends(Tree users,Tree myFriends) {
        System.out.println();
        System.out.println("If you want to make an operation in one of them, please enter the account number.");
        System.out.println("If you want to get back to the previous page, please enter 'back'.");
        System.out.println("Please enter:");
        Scanner sc = new Scanner(System.in);
        boolean flag = false;
        while (!flag) {
            String input = sc.next();
            if (input.equalsIgnoreCase("back")) {
                return true;
            }
            try {
                Integer number = Integer.valueOf(input);
                TreeNode nodeToFind = myFriends.findInTree(number);
                if (nodeToFind == null) {
                    System.out.println("The account number can not be found,please re-enter it.");
                    continue;
                } else {
                    boolean backFlag = false;
                    while (!backFlag) {
                        nodeToFind.printInfo();
                        System.out.println("1.Show his or her friends");
                        System.out.println("2.Show his or her posts");
                        System.out.println("3.Delete him or her");
                        System.out.println("4.Back");
                        backFlag = processAFriend(users,myFriends.getRoot(), nodeToFind);
                    }
                    return false;
                }

            } catch (Exception exception) {
                System.out.println("The input is invalid,please re-enter it.");
                continue;
            }
        }
        return false;
    }

    public boolean processAFriend(Tree users,TreeNode me, TreeNode friend) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter an option:");
        String choice = sc.next();
        switch (choice) {
            case "1":
                boolean backFlag = false;
                while (!backFlag) {
                    friend.getUser().getMyFriends().showTreeInOrder(friend.getUser().getMyFriends().getRoot());
                    System.out.println("Among them, your mutual friends are:");
                    showMutualFriends(me.getUser(), friend.getUser());
                    System.out.println();
                    System.out.println("1.Make an operation in a mutual friend");
                    System.out.println("2.Add a stranger.");
                    System.out.println("3.Back");
                    backFlag = processFriendsOrStrangers(users, me.getUser().getMyFriends());
                }
                break;
            case "2":
                break;
            case "3":
                deleteAFriend(me.getUser(), friend.getUser());
                return true;
            case "4":
                return true;
        }
        return false;
    }
    public boolean processFriendsOrStrangers(Tree users,Tree myFriends){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please choose an option");
        String choice = sc.next();
        switch (choice){
            case "1":
                System.out.println("Please enter the account number.");
                boolean flag1 = false;
                while (!flag1) {
                    String input = sc.next();
                    try {
                        Integer number = Integer.valueOf(input);
                        TreeNode nodeToFind = myFriends.findInTree(number);
                        if (nodeToFind == null) {
                            System.out.println("The account number can not be found,please re-enter it.");
                            continue;
                        } else {
                            boolean backFlag = false;
                            while (!backFlag) {
                                nodeToFind.printInfo();
                                System.out.println("1.Show his or her friends");
                                System.out.println("2.Show his or her posts");
                                System.out.println("3.Delete him or her");
                                System.out.println("4.Back");
                                backFlag = processAFriend(users,myFriends.getRoot(), nodeToFind);
                            }
                        }
                    } catch (Exception exception) {
                        System.out.println("The input is invalid,please re-enter it.");
                        continue;
                    }
                }
                break;
            case "2":
                System.out.println("Please enter the account number.");
                boolean flag2 = false;
                while (!flag2) {
                    String input = sc.next();
                    try {
                        Integer number = Integer.valueOf(input);
                        TreeNode nodeToFind = users.findInTree(number);
                        if (nodeToFind == null) {
                            System.out.println("The account number can not be found,please re-enter it.");
                            continue;
                        } else {
                            addAStranger(myFriends.getRoot().getUser(),nodeToFind.getUser());
                            System.out.println("Successfully added!");
                            return false;
                        }
                    }catch(Exception exception) {
                        System.out.println("The input is invalid,please re-enter it.");
                        continue;
                    }
                }
                break;
            case "3":
                return true;
        }
        return false;
    }
    public void addAStranger(User me,User stranger){
        TreeNode myNode = new TreeNode(me);
        TreeNode otherNode = new TreeNode(stranger);
        me.getMyFriends().addToTree(otherNode);
        stranger.getMyFriends().addToTree(myNode);
    }
    public void deleteAFriend(User me, User friend) {
        me.getMyFriends().deleteFromTree(friend.getAccountNumber());
        friend.getMyFriends().deleteFromTree(me.getAccountNumber());
    }
    private void showPostPrompt() {
        boolean backFlag = false;
        while (!backFlag) {
            System.out.println("===========Welcome to Post Page===========");
            System.out.println("1.Posts Community");
            System.out.println("2.Write a new post");
            System.out.println("3.My Posts");
            System.out.println("4.Liked Posts");
            System.out.println("5.Back");
            backFlag = processPostPrompt();
        }

    }

    private boolean processPostPrompt() {
        System.out.println("Please enter the letter of desired operation:");
        Scanner sc = new Scanner(System.in);
        String option = sc.next();
        switch (option) {
            case "1":
                break;
            case "2":

                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
                return true;
            default:
                System.out.println("Please enter the correct number");
                break;
        }
        return false;
    }


    public void showSearchInterface(Tree users,TreeNode me,Tree friends) {
        boolean backFlag = false;
        while (!backFlag) {
            System.out.println("==========Search Interface==========");
            System.out.println("1.Search for user through account number");
            System.out.println("2.Search for user through part of ID or complete ID");
            System.out.println("3.Search friends with similarities");
            System.out.println("4.back");
            backFlag=processOfSearch(users,me,friends);
        }
    }
    public boolean processOfSearch(Tree users,TreeNode me,Tree friends){//这是search的程序
        System.out.println("Please enter your option:");
        Scanner sc=new Scanner(System.in);
        String option=sc.next();
        switch (option){
            case "1":
                System.out.println("Please enter the account number");
                int account=optimizedNextInt();
                TreeNode toFind=users.findInTree(account);
                if(toFind==null){
                    System.out.println("This account cannot be found.");
                    return false;
                }else{
                    showCommonInterfaceAfterSearch(toFind,me,users,friends);
                    return false;
                }
            case "2":
                System.out.println("Please enter the ID");//这一段还没想好
                return false;
            case "3":
                String field;
                Scanner scanner=new Scanner(System.in);
                System.out.println("Please enter the field:");
                field=scanner.next();
                if(field.equals("gender")){
                    System.out.println("All friends with the same gender:");
                    HashSet<TreeNode> hashSet=new HashSet<>();
                    hashSet=sameGender(me,friends);
                    System.out.println(hashSet);
                    System.out.println("Enter the account number that you want to operate,enter -1 to go back");
                    Scanner sc1=new Scanner(System.in);
                    int inputNumber=sc1.nextInt();
                    if(inputNumber==-1){
                        return false;
                    }
                    Iterator<TreeNode> iterator= hashSet.iterator();
                    while (iterator.hasNext()){
                        TreeNode thisNode=iterator.next();
                        if(inputNumber==thisNode.getUser().getAccountNumber()){
                            showCommonInterfaceAfterSearch(thisNode,me,users,friends);
                        }
                    }
                }
                else if(field.equals("hometown")){
                    System.out.println("All friends with the same hometown:");
                    HashSet<TreeNode> hashSet=new HashSet<>();
                    hashSet=sameHomeTown(me,friends);
                    System.out.println(hashSet);
                    System.out.println("Enter the account number that you want to operate,enter -1 to go back");
                    Scanner sc1=new Scanner(System.in);
                    int inputNumber=sc1.nextInt();
                    if(inputNumber==-1){
                        return false;
                    }
                    Iterator<TreeNode> iterator= hashSet.iterator();
                    while (iterator.hasNext()){
                        TreeNode thisNode=iterator.next();
                        if(inputNumber==thisNode.getUser().getAccountNumber()){
                            showCommonInterfaceAfterSearch(thisNode,me,users,friends);
                        }
                    }
                }
                else if(field.equals("workplace")){
                    System.out.println("All friends with the same workplace:");
                    HashSet<TreeNode> hashSet=new HashSet<>();
                    hashSet=sameWorkPlace(me,friends);
                    System.out.println(hashSet);
                    System.out.println("Enter the account number that you want to operate,enter -1 to go back");
                    Scanner sc1=new Scanner(System.in);
                    int inputNumber=sc1.nextInt();
                    if(inputNumber==-1){
                        return false;
                    }
                    Iterator<TreeNode> iterator= hashSet.iterator();
                    while (iterator.hasNext()){
                        TreeNode thisNode=iterator.next();
                        if(inputNumber==thisNode.getUser().getAccountNumber()){
                            showCommonInterfaceAfterSearch(thisNode,me,users,friends);
                        }
                    }
                }
            case "4":
                return true;
            default:return false;
                }

    }
    public HashSet<TreeNode> sameHomeTown(TreeNode me,Tree tree){
        HashSet<TreeNode> allFind=new HashSet<>();
        TreeNode toFind=tree.getRoot();
        if (toFind!=null){
            sameHomeTown(toFind.getLeft(),tree);
            if(toFind.getUser().getHometown().equals(me.getUser().getHometown())){
                allFind.add(toFind);
            }
            sameHomeTown(toFind.getRight(),tree);
        }
        return allFind;
    }
    public HashSet<TreeNode> sameWorkPlace(TreeNode me,Tree tree){
        HashSet<TreeNode> allFind=new HashSet<>();
        TreeNode toFind=tree.getRoot();
        if (toFind!=null){
            sameWorkPlace(toFind.getLeft(),tree);
            if(toFind.getUser().getWorkPlace().equals(me.getUser().getWorkPlace())){
                allFind.add(toFind);
            }
            sameWorkPlace(toFind.getRight(),tree);
        }
        return allFind;
    }


    public HashSet<TreeNode> sameGender(TreeNode me,Tree tree){
        HashSet<TreeNode> allFind=new HashSet<>();
        TreeNode toFind=tree.getRoot();
        if (toFind!=null){
            sameGender(toFind.getLeft(),tree);
            if(toFind.getUser().getGender()==me.getUser().getGender()){
                allFind.add(toFind);
            }
            sameGender(toFind.getRight(),tree);
        }
        return allFind;
    }
    public void showCommonInterfaceAfterSearch(TreeNode person,TreeNode me,Tree users,Tree friends){
        System.out.println("This person can be found, please enter your option:");
        boolean backFlag=false;
        while (!backFlag){
            System.out.println("1.Show this user's information");
            System.out.println("2.Find this user's mutual friends with you");
            System.out.println("3.Show his friends");
            System.out.println("4.Add this user to be your friend");
            System.out.println("5.Back");
            backFlag=commonOperatorAfterSearch(person,me,users,friends);
        }
    }
    public boolean commonOperatorAfterSearch(TreeNode person,TreeNode me,Tree users,Tree friends){
        Scanner sc=new Scanner(System.in);
        String option=sc.next();
        switch (option){
            case "1":
                person.printInfo();
                return false;
            case "2":
                showMutualFriends(person.getUser(),me.getUser());
                commonOperationOfAPerson(users,friends,me);
                return false;
            case "3":
                friends.showTreeInOrder(person);
                commonOperationOfAPerson(users,friends,me);
                return false;
            case "4":
                addAStranger(me.getUser(),person.getUser());
                System.out.println("Add successfully!");
                return false;
            case "5":
                return true;
            default:
                return false;
        }
    }
    public void commonOperationOfAPerson(Tree users,Tree friends,TreeNode me){
        System.out.println("Please enter the account you want to operate:");
        int accountNumber=optimizedNextInt();
        TreeNode toFind=users.findInTree(accountNumber);
        if(toFind==null){
            System.out.println("Sorry, this account cannot be found!");
        }
        else{
            System.out.println("1.Show the user's information");
            System.out.println("2.Show the user's friends");
            System.out.println("3.Show the user's mutual friends with you");
            System.out.println("4.Add the user to be your friend");
            System.out.println("5.back");
            Scanner sc=new Scanner(System.in);
            String option= sc.next();
            switch (option){
                case "1":
                    toFind.printInfo();
                    commonOperationOfAPerson(users,friends,me);
                    break;
                case "2":
                    friends.showTreeInOrder(toFind);
                    commonOperationOfAPerson(users,friends,me);
                    break;
                case "3":
                    showMutualFriends(me.getUser(),toFind.getUser());
                    commonOperationOfAPerson(users,friends,me);
                    break;
                case "4":
                    addAStranger(me.getUser(),toFind.getUser());
                    System.out.println("Add successfully!");
                    break;
                case "5":
                    break;
            }
        }
    }
    public void showFriendsHometown(TreeNode p, User me) {
        if (p != null) {
            showFriendsHometown(p.getLeft(),me);
            if (p.getUser().getHometown().equalsIgnoreCase(me.getHometown())) {
                p.printInfo();
            }
            showFriendsHometown(p.getRight(),me);
        }
    }

    public void showFriendsWorkPlace(TreeNode p, User me) {
        if (p != null) {
            showFriendsWorkPlace(p.getLeft(),me);
            if (p.getUser().getWorkPlace().equalsIgnoreCase(me.getWorkPlace())) {
                p.printInfo();
            }
            showFriendsWorkPlace(p.getRight(),me);
        }
    }

    public void showMutualFriends(User me, User myFriend) {
        TreeNode p = me.getMyFriends().getRoot();
        if (p != null) {
            me.getMyFriends().showTreeInOrder(p.getLeft());
            if (myFriend.getMyFriends().findInTree(p.getUser().getAccountNumber()) != null) {
                p.printInfo();
            }
            me.getMyFriends().showTreeInOrder(p.getRight());
        }

    }


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
