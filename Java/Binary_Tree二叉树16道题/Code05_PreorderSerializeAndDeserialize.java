package Binary_Tree二叉树16道题;

// 二叉树先序序列化 和 反序列化
// https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/
public class Code05_PreorderSerializeAndDeserialize {

    public static class TreeNode {
        public int val ;
        public TreeNode left ;
        public TreeNode right ;
        public TreeNode(int v) {
            val = v ;
        }
    }

    public class Codec {

        // 序列化，null用#做占位符
        public void f(TreeNode root , StringBuilder builder) {
            if (root == null) {
                // 如果该节点为null，用#作为占位符，,做分隔符
                builder.append("#,");
            } else {
                builder.append(root.val + ",");
                f(root.left , builder) ;
                f(root.right , builder) ;
            }
        }
        public String serialize(TreeNode root){
            StringBuilder builder = new StringBuilder() ;
            f(root , builder) ;
            return builder.toString() ;
        }

        // 反序列化

        // 设置全局变量，cnt 控制节点位置，初始化为0
        public static int cnt ;

        public TreeNode g (String[] val) {
            String cur = val[cnt++]; // 提取第一个字符串
            // 如果该字符串的值为#，就代表该节点为null
            if (cur.equals("#")){
                return null ;
            } else {
                // 否则就是节点不为空，就将String类型的输入转化为整型并赋值给新建的二叉树节点
                TreeNode headNode = new TreeNode(Integer.valueOf(cur));
                headNode.left = g(val) ;
                headNode.right = g(val) ;
                return headNode ;
            }
        }
        public TreeNode deserialize(String data) {
            String[] val = data.split(",") ; // 传入的字符串先以“，”分割
            cnt = 0 ; // 控制变量从0开始
            return g(val) ;
        }
    }
}
