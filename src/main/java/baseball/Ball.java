package baseball;

public class Ball {
    private final int value;
    private final int index;

    public Ball(int value, int index) {
        this.value = value;
        this.index = index;
    }
    public Result compare(Ball targetBall){
        if (value == targetBall.value && index == targetBall.index){
            return Result.STRIKE;
        }
        if (value == targetBall.value){
            return Result.BALL;
        }
        return Result.NOTHING;
    }
}
