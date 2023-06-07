public class Tree {
    private TreeNode root;
    private boolean isTaller;

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public Tree() {
        root = null;
        isTaller = false;
    }

    public Tree(TreeNode root) {
        this.root = root;
        this.isTaller = false;
    }

    public boolean isTreeEmpty() {
        if (root == null) {
            return true;
        } else return false;
    }

    public TreeNode findInTree(int accountNumber) {//这是大树
        TreeNode upto;
        boolean found = false;

        if (root == null) {
            return null;
        } else {
            upto = root;

            while (upto != null && !found) {
                if (accountNumber == (upto.getUser().getAccountNumber()))
                    found = true;
                else {
                    if (accountNumber < (upto.getUser().getAccountNumber()))
                        upto = upto.getLeft();
                    else
                        upto = upto.getRight();
                }
            }
        }
        if (found)
            return upto;
        else
            return null;
    }

    public boolean addToTree(TreeNode toAdd) {//添加节点到大树
        TreeNode current = null;
        TreeNode previous = null;
        TreeNode found;
        if (root == null)
            root = toAdd;
        else {
            found = findInTree(toAdd.getUser().getAccountNumber());
            if (found != null)
                return false;

            current = root;
            while (current != null) {
                previous = current;

                if (toAdd.getUser().getAccountNumber() < (current.getUser().getAccountNumber()))

                    current = current.getLeft();
                else
                    current = current.getRight();
            }

            if (toAdd.getUser().getAccountNumber() < (previous.getUser().getAccountNumber()))
                previous.setLeft(toAdd);
            else
                previous.setRight(toAdd);
        }
        return true;
    }


    public boolean deleteFromTree(int accountNumber) {//大树
        TreeNode current = root;
        TreeNode previous = root;
        boolean found = false;

        while (current != null && !found) {
            if (accountNumber == (current.getUser().getAccountNumber())) {
                found = true;
            } else {
                previous = current;

                if (accountNumber < (current.getUser().getAccountNumber()))
                    current = current.getLeft();
                else
                    current = current.getRight();
            }
        }

        if (!found)
            return false;
        else {
            if (current == root) {
                root = removeNode(root);
            } else {
                if (accountNumber < (previous.getUser().getAccountNumber()))
                    previous.setLeft(removeNode(previous.getLeft()));
                else
                    previous.setRight(removeNode(previous.getRight()));
            }
            return true;
        }
    }

    public TreeNode removeNode(TreeNode toDelete) {//大树
        TreeNode current;
        TreeNode previous;

        if (toDelete.getLeft() == null && toDelete.getRight() == null)
            toDelete = null;
        else if (toDelete.getLeft() == null)

            toDelete = toDelete.getRight();
        else if (toDelete.getRight() == null)
            toDelete = toDelete.getLeft();
        else {
            current = toDelete.getLeft();
            previous = null;

            while (current.getRight() != null) {
                previous = current;
                current = current.getRight();
            }

            toDelete.getUser().setID(current.getUser().getID());/////为什么是ID

            if (previous == null)
                toDelete.setLeft(current.getLeft());
            else
                previous.setRight(current.getLeft());
        }
        return toDelete;
    }

    public void showTreeInOrder(TreeNode p) {
        if (p != null) {
            showTreeInOrder(p.getLeft());
            p.printInfo();
            showTreeInOrder(p.getRight());
        }
    }

    public int getTreeSize(Tree aTree, TreeNode theRootOfaTree) {
        int num = 0;
        if (theRootOfaTree != null) {
            getTreeSize(aTree, theRootOfaTree.getLeft());
            num++;
            getTreeSize(aTree, theRootOfaTree.getRight());
        }
        return num;
    }


}
