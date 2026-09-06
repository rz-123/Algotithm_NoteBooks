package Binary_Tree二叉树16道题;

import javax.swing.tree.TreeNode;

// 按层序列化与反序列化
// https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/
public class Code06_LevelorderSerializeAndDeserialize {

    public static class TreeNode{
        public int val ;
        public TreeNode left ;
        public TreeNode right ;

        public TreeNode(int v){
            val = v ;
        }
    }

    // 队列数组辅助
    public class Codec{
        public static int MAX = 10001 ;
        public static TreeNode[] queue = new TreeNode[MAX] ;
        public static int l , r ;
        // 序列化
        public String serialize(TreeNode root){
            StringBuilder builder = new StringBuilder() ;
            if (root != null){
                // 头结点先加入
                builder.append(root.val + ",") ;
                l = r = 0 ; // 队列左右边界初始化为空
                queue[r++] = root ; // 存进队列
                while (l < r) {
                    root = queue[l++] ; // 从队列左边取出节点
                    if (root.left != null){
                        builder.append(root.left.val + ",") ;
                        queue[r++] = root.left;
                    } else {
                        builder.append("#,") ; // 否则左孩子就是空，#做占位符
                    }
                    if (root.right != null){
                        builder.append(root.right.val + ",");
                        queue[r++] = root.right ;
                    } else {
                        builder.append("#,") ;
                    }
                }
            }
            return builder.toString() ;
        }

        // 反序列化
        public TreeNode deserialize(String data){
            if (data.equals("")){
                return null ;
            }
            String[] nodes  = data.split(",") ; // 以逗号切割字符串
            int index = 0 ;
            TreeNode root = generate(nodes[index++]) ;
            l = r = 0 ;
            queue[r++] = root ;
            while ( l < r ) {
                TreeNode treeNode = queue[l++];
                treeNode.left = generate(nodes[index++]) ; // 赋值给左孩子
                treeNode.right = generate(nodes[index++]) ; // 赋值给右孩子
                if (treeNode.left != null){
                    queue[r++] = treeNode.left ;
                }
                if (treeNode.right != null){
                    queue[r++] = treeNode.right ;
                }
            }
            return root ;
        }
        // 判断是不是#，如果是就代表该位置为空，如果不是就将字符串转成整型赋值给新建节点上
        private TreeNode generate(String val){
            return val.equals("#") ? null : new TreeNode(Integer.valueOf(val));
        }
    }
}
