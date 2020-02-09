  //Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9,
// 10, 11, ... 
//
// Note: 
//n is positive and will fit within the range of a 32-bit signed integer (n < 23
//1).
// 
//
// Example 1:
// 
//Input:
//3
//
//Output:
//3
// 
// 
//
// Example 2:
// 
//Input:
//11
//
//Output:
//0
//
//Explanation:
//The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, 
//which is part of the number 10.
// 
// Related Topics Math

  
  package leetcode.editor.en;
  public class NthDigit{
      public static void main(String[] args) {
           Solution solution = new NthDigit().new Solution();
           System.out.println(solution.findNthDigit(3));
          System.out.println(solution.findNthDigit(11));
          System.out.println(solution.findNthDigit(10));
          System.out.println(solution.findNthDigit(100000000));
      }
      //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findNthDigit(int n) {
        long currentLen = 1;
        long base = 10;
        long lastBase = 0;
        n++;
        while (currentLen * (base - lastBase) < n) {
            n -= currentLen * (base - lastBase);
            lastBase = base;
            base *= 10;
            currentLen++;
        }

        int mTh = (int) ((n - 1) / currentLen);
        int bit = (int) ((n - 1) % currentLen);

        long number = lastBase + mTh;
        return String.valueOf(number).charAt(bit) - '0';
    }
}
//leetcode submit region end(Prohibit modification and deletion)

  }