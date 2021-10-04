package baseball;

import nextstep.utils.Console;
import nextstep.utils.Randoms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Application {
    public static void main(String[] args) {
        // TODO 숫자 야구 게임 구현
        List<Integer> sourceIntList = getThreeRandomNumbers();
        Balls sourceBalls = new Balls(sourceIntList);

        System.out.println("겜시작");
        int targetIntList = Integer.parseInt(Console.readLine());

        Balls targetBalls = new Balls(targetIntList);
        List<Result> result = sourceBalls.compare(targetBalls);
        printResult(result);
    }

    private static List<Integer> getThreeRandomNumbers() {
        List<Integer> result = new ArrayList<>();
        while(result.size()<3) {
            int val = Randoms.pickNumberInRange(1, 9);
            if (result.contains(val)) {
                continue;
            }
            result.add(val);
        }
        return result;
    }


    private static void printResult(List<Result> result) {
        int strikeCounts = countStrike(result);
        int ballCounts = countBall(result);
        if (strikeCounts > 0 && ballCounts > 0) {
            System.out.println(String.format("%d스트라이크 %d볼", strikeCounts, ballCounts));
        } else if (strikeCounts > 0) {
            System.out.println(String.format("%d스트라이크", strikeCounts));
        } else if (ballCounts > 0) {
            System.out.println(String.format("%d볼", ballCounts));
        } else {
            System.out.println("낫싱");
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
