package baseball;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static baseball.Result.NOTHING;

public class Balls {

    private final List<Ball> balls;

    public Balls(int i, int i1, int i2) {
        this.balls = Arrays.asList(new Ball(i, 0), new Ball(i1, 1), new Ball(i2, 2));
    }

    public Balls(List<Integer> sourceIntList) {
        this.balls = Arrays.asList(new Ball(sourceIntList.get(0), 0), new Ball(sourceIntList.get(1), 1), new Ball(sourceIntList.get(2), 2));
    }

    public Balls(int targetIntList) {
        this.balls = Arrays.asList(new Ball(targetIntList/100, 0), new Ball(targetIntList%100/10, 1), new Ball(targetIntList%10, 2));
    }

    public List<Result> compare(Balls targetBalls) {
        List<Result> result = new ArrayList();
        List<Ball> targetBallList = targetBalls.getBalls();
        for (Ball ball : targetBallList) {
            result.add(compare(ball));
        }
        return result;
    }

    private Result compare(Ball targetBall) {
        for (Ball ball : balls) {
            Result result = ball.compare(targetBall);
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
