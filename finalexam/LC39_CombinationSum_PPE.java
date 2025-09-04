import java.util.*;

public class LC39_CombinationSum_PPE {
    static List<List<Integer>> res = new ArrayList<>();
    static List<Integer> path = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        int[] candidates = new int[n];
        for (int i = 0; i < n; i++) candidates[i] = sc.nextInt();

        Arrays.sort(candidates);
        backtrack(candidates, target, 0);

        for (List<Integer> comb : res) {
            for (int i = 0; i < comb.size(); i++) {
                System.out.print(comb.get(i));
                if (i < comb.size() - 1) System.out.print(" ");
            }
            System.out.println();
        }
    }

    static void backtrack(int[] cand, int remain, int start) {
        if (remain == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < cand.length; i++) {
            if (cand[i] > remain) break;
            path.add(cand[i]);
            backtrack(cand, remain - cand[i], i); 
            path.remove(path.size() - 1);
        }
    }
}
