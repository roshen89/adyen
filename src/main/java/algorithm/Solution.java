package algorithm;

/**
 * @author ROSH - 2/7/2020
 */
public class Solution {

  public static int solution1(int[] A, int N) {
    int max = 0;
    int[] res = new int[A[A.length - 1] + 1];
    int len = A.length - 1;
    while (len >= 0 && A[A.length - 1] == A[len]) {
      len--;
    }
    res[A[A.length - 1]] = A.length - 1 - len;
    int cur = 0;
    for (int i = len; i >= 0; i--) {
      if (A[i + 1] != A[i] && A[i + 1] - A[i] <= N) {
        if (i + 1 != A.length - 1) {
          res[A[i + 1]] = cur + res[A[i + 1]];
        }
        res[A[i]] = Math.min(N, res[A[i + 1]]);
        cur = 1;
      } else if (A[i] == A[i + 1]) {
        cur++;
      } else if (A[i + 1] != A[i] && A[i + 1] - A[i] > N) {
        res[A[i]] = 0;
        cur = 1;
      }
    }

    for (int i = 0; i < A[A.length - 1] + 1; i++) {
      max = Math.max(res[i], max);
    }
    return max;
  }
}