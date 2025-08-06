public class GradeStatisticsSystem {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};

        int sum = 0;
        int max = scores[0];
        int min = scores[0];

        int countA = 0, countB = 0, countC = 0, countD = 0, countF = 0;

  
        for (int score : scores) {
            sum += score;

            if (score > max) max = score;
            if (score < min) min = score;

            if (score >= 90) countA++;
            else if (score >= 80) countB++;
            else if (score >= 70) countC++;
            else if (score >= 60) countD++;
            else countF++;
        }

        double average = sum / (double) scores.length;

        int aboveAverageCount = 0;
        for (int score : scores) {
            if (score > average) aboveAverageCount++;
        }

        System.out.println("=== 成績統計報表 ===");
        System.out.printf("成績列表：");
        for (int score : scores) {
            System.out.print(score + " ");
        }
        System.out.println();

        System.out.printf("平均分：%.2f\n", average);
        System.out.println("最高分：" + max);
        System.out.println("最低分：" + min);

        System.out.println("\n等第分布：");
        System.out.println("A (90–100)： " + countA + " 人");
        System.out.println("B (80–89)：  " + countB + " 人");
        System.out.println("C (70–79)：  " + countC + " 人");
        System.out.println("D (60–69)：  " + countD + " 人");
        System.out.println("F (<60)   ：  " + countF + " 人");

        System.out.println("\n高於平均分的學生人數：" + aboveAverageCount);
    }
}
