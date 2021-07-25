package codeforces;

public class AlicesBirthday {
    public int[] partition(int k) {
        if (k % 3 == 1) {
            return new int[]{-1};
        } else {
            if (k % 3 == 0) {
                int[] result = new int[k / 3];
                for (int i = 0; 3 * i < k; i++) {
                    result[i] = 3 + 3 * i;
                }
                return result;
            } else {
                int[] result = new int[(k + 1) / 3];
                result[0] = 1;
                for (int i = 0; i < k / 3; i++) {
                    result[i + 1] = 5 + 3 * i;
                }
                return result;
            }
        }
    }
}
