package baseball;

import nextstep.utils.Console;
import nextstep.utils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Application {
    private static final String gameStartFlag = "1";

    public static void main(String[] args) {
        playMultipleGames();
    }

    private static void playMultipleGames() {
        //여러게임 실행
        String willContinue = gameStartFlag;
        while (willContinue.equals(gameStartFlag)) {
            playSingleGame();
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            willContinue = Console.readLine();
        }
    }

    private static void playSingleGame() {
        //단일게임 실행
        Balls sourceBalls = new Balls(getSourceInt());
        String gameResult = "";
        while (!gameResult.equals("3스트라이크")) {
            //Guess
            gameResult = makeGuess(sourceBalls);
        }
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 끝");
    }

    private static String makeGuess(Balls sourceBalls) {
        // 추측게임
        Balls targetBalls = new Balls(getTargetInt());
        Results results = sourceBalls.compareBalls(targetBalls);
        String gameResult = results.resultsToString();
        System.out.println(gameResult);
        return gameResult;
    }

    private static int getTargetInt() {
        //콘솔입력
        System.out.print("숫자를 입력해주세요 : ");
        int result = 0;
        try {
            result = validate3DigitsNumber(Integer.parseInt(Console.readLine()));
        } catch (NumberFormatException e) {
            System.out.printf("[ERROR]%s%n", e);
            result = getTargetInt();
        }
        return result;
    }

    private static int validate3DigitsNumber(int result) {
        //입력숫자 세자리 검증
        if (result < 100 || result > 999) {
            System.out.println("[ERROR] 세자리 숫자를 입력해주세요");
            result = getTargetInt();
        }
        return result;
    }

    private static int getSourceInt() {
        // 랜덤한 세자리숫자 추출
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
}
