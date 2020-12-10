package BinaryTree;

import com.sun.source.tree.ContinueTree;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

public class Traversal {
    //*********************************************************************************************************************************
    /*
    * 二叉树前序遍历
    * 失败的办法，我自己的
    * 我考虑的是，递归求解，三要素是：在两个子节点为空的时候停下来，但是不知道应该返回什么东西，这里被卡住了;
    * 接下来是分而治之，考虑的是三种情况，两个子树都存在、只存在左子树、只存在右子树，然后对应的递归，但是最后一个else报错。
    * 第一个问题在于设计思路，没法使用return;
    * 第二个问题是在于return了一句话，这个方法就结束了，执行不到这里
    *
    * 好的是考虑到了一个点，不能在方法中直接创建临时的数组，不然会有n个数组的开销，我的办法是在外面创建一个List，但是显然有更好的办法，使用public调用private，这种设计方式的这个作用需要记下来
    *
    * */
//    private List<Integer> list = new ArrayList<>();
//    public List<Integer> preorderTraversal(@NotNull TreeNode root) {
//        list.add(root.val);
//        if (root.left == null && root.right == null)
//            ;
//        else if (root.left != null && root.right == null)
//            return preorderTraversal(root.left);
//        else if (root.left == null && root.right != null)
//            return preorderTraversal(root.right);
//        else {
//            return preorderTraversal(root.left);
//            return preorderTraversal(root.right);
//        }
//
//        return list;
//    }

    //递归解决
    public List<Integer> preorderTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>();//在这里解决数组的创建问题
        preorderTraversal(root, res);
        return res;
    }
    private void preorderTraversal(TreeNode root, List<Integer> res){
        //注意这里，我之前的思考的分类太多，太细节反而不太好。
        // 这里的思路是，不管左右子树是否成立，我只需要关注要被放进去的根节点就可以了，
        // 递归的时候，就算某个子节点确实为空，但是在我们的子问题里，子节点其实是根节点！！！！！！！！！！！！！！！！！！！
        if (root == null)
            return;
        res.add(root.val);
        preorderTraversal(root.left, res);
        preorderTraversal(root.right, res);
    }
    //时间复杂度：O(n)，其中 nnn 是二叉树的节点数。每一个节点恰好被遍历一次。
    //空间复杂度：O(n)，为递归过程中栈的开销，平均情况下为 O(log n)，最坏情况下树呈现链状，为 O(n)。


    //迭代解决
    //我们也可以用迭代的方式实现方法一的递归函数，两种方式是等价的，
    // 区别在于递归的时候隐式地维护了一个栈，而我们在迭代的时候需要显式地将这个栈模拟出来，其余的实现与细节都相同，具体可以参考下面的代码。

    //首先我们应该创建一个Stack用来存放节点，首先我们想要打印根节点的数据，此时Stack里面的内容为空，所以我们优先将头结点加入Stack，然后打印。
    //之后我们应该先打印左子树，然后右子树。所以先加入Stack的就是右子树，然后左子树。
    private void preOrderTraversal(TreeNode root){
        if (root == null)
            return;
        Stack<TreeNode> nodes = new Stack<>();
        nodes.push(root);
        //这个停止条件完全没想到
        while (!nodes.isEmpty()){
            TreeNode node = nodes.pop();
            System.out.println(node.val+ " ");
            //关键在于这里，中左右中左右，先就是root进去，然后弹出，接下来进右左，弹出此时为中的左，再进弹出的左的右左
            if (node.right != null)
                nodes.push(node.right);
            if (node.left != null)
                nodes.push(node.left);
        }
    }




    //*************************************************************************************************************************
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
     }
  }
}
