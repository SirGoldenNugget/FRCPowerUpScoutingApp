package Database;

public class Team {
    private int teamNumber;
    private int switches;
    private int scales;
    private int vaults;
    private int climbs;

    public Team(int teamNumber, int switches, int scales, int vaults, int climbs) {
        this.teamNumber = teamNumber;
        this.switches = switches;
        this.scales = scales;
        this.vaults = vaults;
        this.climbs = climbs;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public int getSwitches() {
        return switches;
    }

    public void setSwitches(int switches) {
        this.switches = switches;
    }

    public int getScales() {
        return scales;
    }

    public void setScales(int scales) {
        this.scales = scales;
    }

    public int getVaults() {
        return vaults;
    }

    public void setVaults() {
        this.vaults = vaults;
    }

    public int getClimbs() {
        return climbs;
    }

    public void setClimbs(int climbs)
    {
        this.climbs = climbs;
    }
}
