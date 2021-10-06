package baseball;

import nextstep.utils.Console;
import nextstep.utils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        String willContinue = "1";
        while (willContinue.equals("1")) {
            playOneTime();
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            willContinue = Console.readLine();
        }
    }

    private static void playOneTime() {
        int sourceInt = getSourceInt();
        Balls sourceBalls = new Balls(sourceInt);
        String gameResult = "";
        while (!gameResult.equals("3스트라이크")) {
            int targetInt = getTargetInt();
            Balls targetBalls = new Balls(targetInt);

            List<Result> result = sourceBalls.compare(targetBalls);
            gameResult = printResult(result);
            System.out.println(gameResult);
        }
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 끝");
    }

    private static int getTargetInt() {
        System.out.print("숫자를 입력해주세요 : ");
        int result = 0;
        try {
            result = Integer.parseInt(Console.readLine());
            result = validate3DigitsNumber(result);
        } catch (NumberFormatException e) {
            System.out.printf("[ERROR]%s%n", e);
            result = getTargetInt();
        }
        return result;
    }

    private static int validate3DigitsNumber(int result) {
        if (result < 100 || result > 999) {
            System.out.println("[ERROR] 세자리 숫자를 입력해주세요");
            result = getTargetInt();
        }
        return result;
    }

    private static int getSourceInt() {
        List<Integer> result = new ArrayList<>();
        while (result.size() < 3) {
            getRandomSingleNumber(result);
        }
        return result.get(0) * 100 + result.get(1) * 10 + result.get(2);
    }

    private static void getRandomSingleNumber(List<Integer> result) {
        int val = Randoms.pickNumberInRange(1, 9);
        if (result.contains(val)) {
            return;
        }
        result.add(val);
    }


    private static String printResult(List<Result> result) {
        int strikeCounts = countStrikeBall(result, Result.STRIKE);
        int ballCounts = countStrikeBall(result, Result.BALL);
        if (strikeCounts > 0 && ballCounts > 0) return String.format("%d스트라이크 %d볼", strikeCounts, ballCounts);
        if (strikeCounts > 0) return String.format("%d스트라이크", strikeCounts);
        if (ballCounts > 0) return String.format("%d볼", ballCounts);
        return "낫싱";
    }

    private static int countStrikeBall(List<Result> result, Result resultType) {
        if (!result.contains(resultType)) {
            return 0;
        }

        int count = 0;
        for (Result r : result) {
            count = getCount(count, r, resultType);
        }
        return count;
    }

    private static int getCount(int count, Result r, Result ball) {
        if (r.equals(ball)) {
            count++;
        }
        return count;
    }

}
