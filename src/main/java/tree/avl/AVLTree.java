package tree.avl;



public class AVLTree<T extends Comparable<T>> {

    private AVLTreeNode<T> mRoot;    // 根节点

    // AVL树的节点
    class AVLTreeNode<T extends Comparable<T>> {
        T key;  // 关键值
        int height; // 高度
        AVLTreeNode<T> left;
        AVLTreeNode<T> right;

        public AVLTreeNode(T key, AVLTreeNode<T> left, AVLTreeNode<T> right) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.height = 0;
        }
    }

    /**
     * 获取AVL树的高度
     * @param root  根节点
     * @return int 树的高度
     * @date 2020/10/12
     */
    private int height(AVLTreeNode<T> root) {
        if (root != null) {
            return root.height;
        }
        return 0;
    }

    public int height() {
        return height(mRoot);
    }

    /**
     * 比较两个值的大小
     * @author gonglei
     * @date 2020/10/12
     */
    private int max(int a, int b) {
        return Math.max(a, b);
    }

    /**
     * 查找以root为跟结点的最小结点
     * @param root AVL的根节点
     * @return
     * @date 2020/10/12
     */
    private AVLTreeNode<T> minimum(AVLTreeNode<T> root) {
        if (root == null) {
            return null;
        }
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    /**
     * 查找以root为根的AVL树的最大结点
     * @param root AVL树的根结点
     * @return
     * @date 2020/10/12
     */
    private AVLTreeNode<T> maximum(AVLTreeNode<T> root) {
        if (root == null) {
            return null;
        }
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    /**
     * LL旋转
     * @param k2 当前最小失衡节点
     * @return k1 调整后的根节点
     * @author gonglei
     * @date 2020/10/12
     */
    private AVLTreeNode<T> llRotation(AVLTreeNode<T> k2) {

        AVLTreeNode<T> k1;

        k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;

        // 更新k1和k2的高度
        k2.height = max(height(k2.left), height(k2.right) + 1);
        k1.height = max(height(k1.left), k2.height) + 1;
        return k1;
    }

    /**
     * RR旋转
     * @param k1 当前最小失衡节点
     * @return k2 调整后的根节点
     * @date 2020/10/12
     */
    private AVLTreeNode<T> rrRotation(AVLTreeNode<T> k1) {

        AVLTreeNode<T> k2;

        k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;

        k1.height = max(height(k1.left), height(k1.right)) + 1;
        k2.height = max(height(k2.right), k1.height) + 1;

        return k2;
    }

    /**
     * LR旋转
     * 1）先对k3进行的RR旋转
     * 2）再对k3进行LL旋转
     * @param k3 当前最小失衡节点
     * @return
     * @date 2020/10/12
     */
    private AVLTreeNode<T> lrRotation(AVLTreeNode<T> k3) {

        k3.left = rrRotation(k3.left);
        return llRotation(k3);
    }


    /**
     * RL旋转
     * 1）现对K3进行LL旋转
     * 2） 再对k1进行RR旋转
     * @param k1 当前最小失衡节点
     * @return
     * @date 2020/10/12
     */
    private AVLTreeNode<T> rlRotation(AVLTreeNode<T> k1) {
        // k3 = k1.right
        k1.right = llRotation(k1.right);
        // k2 = k3.left
        return rrRotation(k1);
    }

    /**
     * 将节点插入到AVL树中，并返回根节点
     * @param root 根节点
     * @param key 插入结点的值
     * @return root
     * @date 2020/10/12
     */
    private AVLTreeNode<T> insert(AVLTreeNode<T> root, T key) {
        if (root == null) {
            // 新建根节点
            root = new AVLTreeNode<T>(key, null, null);
        }
        if (root == null) {
            System.out.println("ERROR: create node failed!");
            return null;
        } else {
            int cmp = key.compareTo(root.key);

            if (cmp < 0) {  // 将key插入到左子树
                root.left = insert(root.left, key);
                // 插入结点后，若AVL树失衡，则进行相应的调节
                if (height(root.left) - height(root.right) == 2) {
                    if (key.compareTo(root.left.key) < 0) {
                        // LL调整
                        root = llRotation(root);
                    } else {
                        // LR调整
                        root = lrRotation(root);
                    }
                }
            } else if (cmp > 0){    // 将key插入到右子树
                root.right = insert(root.right, key);
                if (height(root.right) - height(root.left) == 2) {
                    if (key.compareTo(root.right.key) > 0) {
                        // RR调整
                        root = rrRotation(root);
                    } else {
                        root = rlRotation(root);
                    }
                }
            } else {
                System.out.println("ERROR ： 不允许添加相同的节点");
            }
        }
        // 更新AVL树的高度
        root.height = max(height(root.left), height(root.left)) + 1;

        return root;
    }

    public void insert(T key) {
        mRoot = insert(mRoot, key);
    }

    /**
     * 删除节点z，并返回根节点
     * @param root AVL树根结点
     * @param z 待删除的结点
     * @return root
     * @date 2020/10/12
     */
    private AVLTreeNode<T> remove(AVLTreeNode<T> root, AVLTreeNode<T> z) {

        if (root == null || z == null) {
            return null;
        }

        int cmp = z.key.compareTo(root.key);

        if (cmp < 0) {  // 左子树
            root.left = remove(root.left, z);
            if (height(root.right) - height(root.left) == 2) {
                // 最小失衡节点
                AVLTreeNode<T> r = root.right;
                if (height(r.left) > height(r.right)) {
                    root = rlRotation(root);
                } else {
                    root = rrRotation(root);
                }
            }
        } else if (cmp > 0) {   // 右子树
            root.right = remove(root.right, z);
            if (height(root.left) - height(root.left) == 2) {
                AVLTreeNode<T> l = root.left;
                if (height(l.right) > height(l.left)) {
                    root = lrRotation(root);
                } else {
                    root = llRotation(root);
                }
            }
        } else {    // root结点就是待删除结点
            // root的左右孩子都非空
            if (root.left != null && root.right != null) {
                if (height(root.left) > height(root.right)) {
                    // 如果root的左子树比右子树高
                    // 1） 找到左子树的最大结点
                    // 2）将该最大结点赋值给root
                    // 3）删除该最大结点   --> 删除后AVL树仍然是平衡的
                    AVLTreeNode<T> max = maximum(root.left);
                    root.key = max.key;
                    root.left = remove(root.left, max);
                } else {
                    // 如果右子树不比左子树低
                    // 1）找到右子树的最小结点
                    // 2） 将最小结点的值赋给root
                    // 3） 删除该最小结点
                    AVLTreeNode<T> min = minimum(root.right);
                    root.key = min.key;
                    root.right = remove(root.right, min);
                }
            } else {    // 左右子树有一侧为null
                AVLTreeNode<T> tmp = root;
                root = (root.left != null) ? root.left : root.right;
                tmp = null;

            }

        }
        return root;
    }
}
