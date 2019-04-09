package Database;

public class Team {
    private int teamNumber;
    private int hatches_lower;
    private int hatches_upper;
    private int hatches_dropped;
    private int cargo_lower;
    private int cargo_upper;
    private int sandstorm;
    private int endgame;

    public Team(int teamNumber, int hatches_lower, int hatches_upper, int hatches_dropped, int cargo_lower, int cargo_upper, int sandstorm, int endgame) {
        this.teamNumber = teamNumber;
        this.hatches_lower = hatches_lower;
        this.hatches_upper = hatches_upper;
        this.hatches_dropped = hatches_dropped;
        this.cargo_lower = cargo_lower;
        this.cargo_upper = cargo_upper;
        this.sandstorm = sandstorm;
        this.endgame = endgame;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public int getHatches() {
        return hatches_lower + hatches_upper;
    }

    public int getHatchesLower() {
        return hatches_lower;
    }

    public int getHatchesUpper() {
        return hatches_upper;
    }

    public int getHatchesDropped() {
        return hatches_dropped;
    }

    public int getCargo() {
        return cargo_lower + cargo_upper;
    }

    public int getCargoLower() {
        return cargo_lower;
    }

    public int getCargoUpper() {
        return cargo_upper;
    }

    public int getSandstorm() {
        return sandstorm;
    }

    public int getEndgame() {
        return endgame;
    }
}
