package baseball;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static baseball.Result.NOTHING;

public class Balls {

    private final List<Ball> balls;

    public Balls(int targetIntList) {
        this.balls = Arrays.asList(
                new Ball(targetIntList / 100, 0),
                new Ball(targetIntList % 100 / 10, 1),
                new Ball(targetIntList % 10, 2)
        );
    }

    public Results compareBalls(Balls targetBalls) {
        List<Result> result = new ArrayList<>();
        List<Ball> targetBallList = targetBalls.getBalls();
        for (Ball ball : targetBallList) {
            result.add(compareBall(ball));
        }
        return new Results(result);
    }

    private Result compareBall(Ball targetBall) {
        for (Ball ball : balls) {
            Result result = ball.compare(targetBall);
            // TODO: 2뎁스 제거
            if (result != NOTHING) {
                return result;
            }
        }
        return NOTHING;
    }


    public List<Ball> getBalls() {
        return balls;
    }
}
