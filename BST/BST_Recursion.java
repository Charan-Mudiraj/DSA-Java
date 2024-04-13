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

public class BST_Recursion {

  static Node root = null;

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
          root = insert(root, data);
          break;
        case 2:
          delete(data);
          break;
        case 3:
          search(root, data);
          break;
        default:
          System.out.println("Invalid Input");
      }
    }
  }

  static Node insert(Node node, int data) {
    if (node == null) {
      return new Node(data);
    }

    if (data < node.data) {
      node.left = insert(node.left, data);
    } else if (data > node.data) {
      node.right = insert(node.right, data);
    }
    return node;
  }

  static void search(Node node, int data) {
    if (node == null) {
      System.out.println(data + " not found.");
      return;
    }
    if (data == node.data) {
      System.out.println(data + " found at: " + node);
      return;
    } else if (data < node.data) {
      search(node.left, data);
    } else {
      search(node.right, data);
    }
  }

  static void delete(int data) {}

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
}
