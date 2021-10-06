package baseball;

import java.util.List;

public class Results {

    private final List<Result> results;

    public Results(List<Result> results) {
        this.results = results;
    }

    private int getStrikeCount() {
        return countStrikeBall(Result.STRIKE);
    }

    private int getBallCount() {
        return countStrikeBall(Result.BALL);
    }

    private int countStrikeBall(Result resultType) {
        if (!results.contains(resultType)) {
            return 0;
        }

        int count = 0;
        for (Result r : results) {
            count = getCount(count, r, resultType);
        }
        return count;
    }

    private int getCount(int count, Result r, Result ball) {
        if (r.equals(ball)) {
            count++;
        }
        return count;
    }

    public String resultsToString() {
        int strikeCounts = getStrikeCount();
        int ballCounts = getBallCount();
        if (strikeCounts > 0 && ballCounts > 0) return String.format("%d스트라이크 %d볼", strikeCounts, ballCounts);
        if (strikeCounts > 0) return String.format("%d스트라이크", strikeCounts);
        if (ballCounts > 0) return String.format("%d볼", ballCounts);
        return "낫싱";
    }
}

