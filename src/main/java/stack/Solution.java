package stack;

import java.util.LinkedList;

/**
 * @author ROSH - 2/7/2020
 */
public class Solution {

  public Solution() {
    // write your code in Java SE 8
  }

  private LinkedList<LinkedList<Integer>> lists = new LinkedList<>();

  public void push(int value) {
    if (lists.isEmpty()) {
      LinkedList<Integer> list = new LinkedList<>();
      list.push(value);
      lists.push(list);
    } else {
      lists.peekFirst().push(value);
    }
  }

  public int top() {
    if (lists.isEmpty()) {
      return 0;
    } else {
      LinkedList<Integer> list = lists.peekFirst();
      return list.peekFirst() == null ? 0 : list.peekFirst();

    }
  }

  public void pop() {
    if (!lists.isEmpty()) {
      LinkedList<Integer> list = lists.peekFirst();
      if (!list.isEmpty()) {
        list.removeFirst();
      }
    }
  }

  public void begin() {
    if (!lists.isEmpty()) {
      LinkedList<Integer> list = lists.peekFirst();
      LinkedList<Integer> temp = new LinkedList<>(list);
      lists.push(temp);
    }
  }

  public boolean rollback() {
    if (!lists.isEmpty()) {
      // Remove top
      lists.pop();
      return true;
    } else {
      return false;
    }
  }

  public boolean commit() {
    if (lists.size() > 1) {
      // Replace second to top with top
      LinkedList<Integer> listToCommit = lists.pop();
      lists.removeFirst();
      lists.push(listToCommit);
      return true;
    } else {
      return false;
    }
  }

  public static void test() {
    // Define your tests here
    Solution sol = new Solution();
    sol.push(42);
    assert sol.top() == 42 : "top() should be 42";
  }
}

