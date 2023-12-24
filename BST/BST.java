import java.util.Scanner;

class Node {

  int data;
  Node left;
  Node right;

  Node(int data) {
    this.data = data;
  }

  Node(int data, Node left, Node right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }
}

public class BST {

  static Node root = null;

  static void insert(int data) {
    Node newNode = new Node(data);
    if (root == null) {
      root = newNode;
      return;
    }
    Node temp = root, prev = root;
    while (temp != null) {
      prev = temp;
      if (temp.data > data) {
        temp = temp.left;
      } else {
        temp = temp.right;
      }
    }
    if (prev.data > data) {
      prev.left = newNode;
    } else {
      prev.right = newNode;
    }
  }

  static void search(int data) {
    if (root == null) {
      System.out.println("Empty Tree");
      return;
    }
    Node temp = root;
    while (temp != null) {
      if (temp.data == data) {
        System.out.println(data + " found at: " + temp);
        return;
      } else if (temp.data > data) {
        temp = temp.left;
      } else {
        temp = temp.right;
      }
    }
    System.out.println(data + " not found.");
  }

  static void delete(int data) {
    if (root == null) {
      System.out.println("Empty tree");
      return;
    }
    if (root.data == data && root.left == null && root.right == null) {
      root = null;
      return;
    }
    Node temp = root, prev = root;
    //temp => node to delete
    while (temp != null && temp.data != data) {
      prev = temp;
      if (temp.data > data) {
        temp = temp.left;
      } else {
        temp = temp.right;
      }
    }
    if (temp == null) {
      System.out.println(data + " not found");
      return;
    }
    //case 1: temp is leaf node
    if (temp.left == null && temp.right == null) {
      if (temp == prev.left) {
        prev.left = null;
      } else {
        prev.right = null;
      }
    }
    //case 2: temp is a node with only left child
    else if (temp.left != null && temp.right == null) {
      if (temp == root) { //if delete node is root and it has only left child/sub-tree
        root = root.left;
        return;
      }
      if (temp == prev.left) {
        prev.left = temp.left;
      } else {
        prev.right = temp.left;
      }
    }
    //case 3: temp is a node with only right child
    else if (temp.left == null && temp.right != null) {
      if (temp == root) { //if delete node is root and it has only right child/sub-tree
        root = root.right;
        return;
      }
      if (temp == prev.left) {
        prev.left = temp.right;
      } else {
        prev.right = temp.right;
      }
    }
    //case 4: temp is a node with two children
    else {
      //Logic:
      //choose left or right sub-tree of temp
      //if left sub-tree, find the node with maximum data value
      //if right sub-tree, find the node with minimum data value
      //maximum/minimum value will always be a leaf node
      //detach that min/max root node and replce the temp node with it
      //then, detach the subtrees of temp node and attach with the replaced(min/max) node

      //lets choose left sub-tree (hence, find max)
      Node max, prev_max;

      //Tip:
      //left most node of a BST is minimum
      //right most node of BST is maximum

      //so, go rigth of the sub-tree until null => the leaf node is maximum
      max = temp.left;
      prev_max = temp.left;
      while (max.right != null) {
        prev_max = max;
        max = max.right;
      }
      if (prev_max != max) {
        if (max.left != null) {
          prev_max.right = max.left;
        } else {
          prev_max.right = null;
        }
      } else { //if left sub-tree only have one node
        temp.left = null;
      }
      if (temp == root) { //if delete node is root and it has both children
        max.left = root.left;
        max.right = root.right;
        root = max;
        return;
      }
      if (temp == prev.left) {
        prev.left = max;
      } else {
        prev.right = max;
      }
      max.left = temp.left;
      max.right = temp.right;
    }
    temp = null;
  }

  static void preTraverse(Node node) { //pre-order traverse
    if (node != null) {
      System.out.print(node.data + "  ");
      preTraverse(node.left);
      preTraverse(node.right);
    }
  }

  static void inTraverse(Node node) { //in-order traverse
    if (node != null) {
      inTraverse(node.left);
      System.out.print(node.data + "  ");
      inTraverse(node.right);
    }
  }

  static void postTraverse(Node node) { //post-order traverse
    if (node != null) {
      postTraverse(node.left);
      postTraverse(node.right);
      System.out.print(node.data + "  ");
    }
  }

  public static void main(String[] args) {
    //=>Test Data:
    // insert(18);
    // insert(9);
    // insert(27);
    // insert(5);
    // insert(12);
    // insert(25);
    // insert(30);
    // insert(11);
    // insert(14);
    // insert(29);

    Scanner sc = new Scanner(System.in);
    int input, data;
    while (true) {
      System.out.print("\nBST: ");
      inTraverse(root);
      System.out.println("\nOperations: ");
      System.out.println("1.Insertion");
      System.out.println("2.Deletion");
      System.out.println("3.Search");
      System.out.print("Enter Input (or) Enter (0) to exit: ");
      input = sc.nextInt();
      if (input == 0) {
        break;
      }
      System.out.print("Enter data: ");
      data = sc.nextInt();
      switch (input) {
        case 1:
          insert(data);
          break;
        case 2:
          delete(data);
          break;
        case 3:
          search(data);
          break;
        default:
          System.out.println("Invalid Input");
      }
    }
  }
}
