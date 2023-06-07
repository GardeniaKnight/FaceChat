public class Test {
    public static void main(String[] args) {
        User a = new User("A",1,1,"a",0,"CSC","CSU");
        User b = new User("B",2,2,"b",1,"CDC","CSU");
        User c = new User("C",3,3,"c",0,"CSC","CQDX");
        User d = new User("D",4,4,"c",1,"CSC","CQDX");

        TreeNode e = new TreeNode(a);
        TreeNode f = new TreeNode(b);
        TreeNode g = new TreeNode(c);
        TreeNode h = new TreeNode(d);

        Tree myTree1 = new Tree();
        myTree1.setRoot(e);
        a.setMyFriends(myTree1);

        Tree myTree2 = new Tree();
        myTree2.setRoot(f);
        b.setMyFriends(myTree2);

        Tree myTree3 = new Tree();
        myTree3.setRoot(g);
        c.setMyFriends(myTree3);

        Tree myTree4 = new Tree();
        myTree4.setRoot(h);
        d.setMyFriends(myTree4);

        Tree testTree = new Tree();
        TreeNode i = new TreeNode(a);
        TreeNode j = new TreeNode(b);
        TreeNode k = new TreeNode(c);
        TreeNode l = new TreeNode(d);

        testTree.addToTree(i);
        testTree.addToTree(j);
        testTree.addToTree(k);
        testTree.addToTree(l);
        Operator test = new Operator(a,testTree);
        test.addAStranger(a,b);
        test.addAStranger(a,c);
        test.addAStranger(b,c);
        test.addAStranger(b,d);
        boolean backFlag = false;
            test.showMainPage();
    }
}
