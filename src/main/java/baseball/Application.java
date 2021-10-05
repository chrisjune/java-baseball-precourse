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

        while (true) {
            int targetInt = getTargetInt();
            Balls targetBalls = new Balls(targetInt);

            List<Result> result = sourceBalls.compare(targetBalls);
            String result2 = printResult(result);
            System.out.println(result2);
            if (result2.equals("3스트라이크")) {
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 끝");
                return;
            }
        }
    }

    private static int getTargetInt() {
        System.out.print("숫자를 입력해주세요 : ");
        int targetIntList = Integer.parseInt(Console.readLine());
        return targetIntList;
    }

    private static int getSourceInt() {
        List<Integer> result = new ArrayList<>();
        while (result.size() < 3) {
            int val = Randoms.pickNumberInRange(1, 9);
            if (result.contains(val)) {
                continue;
            }
            result.add(val);
        }
        return result.get(0) * 100 + result.get(1) * 10 + result.get(2);
    }


    private static String printResult(List<Result> result) {
        int strikeCounts = countStrike(result);
        int ballCounts = countBall(result);
        if (strikeCounts > 0 && ballCounts > 0) {
            return String.format("%d스트라이크 %d볼", strikeCounts, ballCounts);
        } else if (strikeCounts > 0) {
            return String.format("%d스트라이크", strikeCounts);
        } else if (ballCounts > 0) {
            return String.format("%d볼", ballCounts);
        } else {
            return "낫싱";
        }
    }


    private static int countStrike(List<Result> result) {
        if (!result.contains(Result.STRIKE)) {
            return 0;
        }
        int count = 0;
        for (Result r : result) {
            if (r.equals(Result.STRIKE)) {
                count++;
            }
        }
        return count;
    }

    private static int countBall(List<Result> result) {
        if (!result.contains(Result.BALL)) {
            return 0;
        }
        int count = 0;
        for (Result r : result) {
            if (r.equals(Result.BALL)) {
                count++;
            }
        }
        return count;
    }

}
