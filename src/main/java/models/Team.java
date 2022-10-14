package models;

import java.util.Arrays;
import java.util.Objects;

public class Team {

    private String house;

    private String keeper;

    private String seeker;

    private String[] chasers;

    private static final String POSITION_CHASER = "chaser";
    private static final String POSITION_SEEKER = "seeker";
    private static final String POSITION_KEEPER = "keeper";

    public Team(String house, String keeper, String seeker, String[] chasers) {
        if (house == null || keeper == null || seeker == null) {
            throw new IllegalArgumentException("fields can not be null");
        }
        if (house.isBlank() || keeper.isBlank() || seeker.isBlank()) {
            throw new IllegalArgumentException("fields can not be blank");
        }
        if (chasers.length != 3) {
            throw new IllegalArgumentException("must have 3 chasers");
        }
        if (hasNull(chasers) || hasBlank(chasers)) {
            throw new IllegalArgumentException("Illegal Elements");
        }
        this.house = house;
        this.keeper = keeper;
        this.seeker = seeker;
        this.chasers = Arrays.copyOf(chasers, chasers.length);
    }

    public Team (Team source) {
        this.house = source.house;
        this.keeper = source.keeper;
        this.seeker = source.seeker;
        this.chasers = Arrays.copyOf(source.chasers, source.chasers.length);
    }

    public String getHouse() {
        return house;
    }

    public String getKeeper() {
        return keeper;
    }

    public String getSeeker() {
        return seeker;
    }

    public String[] getChasers() {
        return Arrays.copyOf(chasers, chasers.length);
    }


    public static String getPositionChaser() {
        return POSITION_CHASER;
    }

    public void setHouse(String house) {
        checkField(house);
        this.house = house;
    }

    public void setKeeper(String keeper) {
        checkField(keeper);
        this.keeper = keeper;
    }

    public void setSeeker(String seeker) {
        checkField(seeker);
        this.seeker = seeker;
    }

    public void setChasers(String[] chasers) {
        if (chasers.length != 3 || hasBlank(chasers) || hasNull(chasers)) {
            throw new IllegalArgumentException("Illegal chaser arguments");
        }
        this.chasers = Arrays.copyOf(chasers, chasers.length);
    }

    public static String getPositionSeeker() {
        return POSITION_SEEKER;
    }

    public static String getPositionKeeper() {
        return POSITION_KEEPER;
    }

    public static boolean hasNull (String[] array) {
       return Arrays.stream(array).anyMatch((element) -> element == null);
    }

    public static boolean hasBlank (String[] array) {
        return Arrays.stream(array).anyMatch((element) -> element.isBlank());
    }

    public void checkField(String field) {
        if (field == null || field.isBlank()) {
            throw new IllegalArgumentException(field + " can not be blank/null");
        }
    }

    @Override
    public String toString() {
        return "House: " + this.house + "\n" +
                "Keeper: " + this.keeper + "\n" +
                "Seeker: "  + this.seeker + "\n" +
                "Chasers: " + Arrays.toString(this.chasers) + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(house, team.house) && Objects.equals(keeper, team.keeper) && Objects.equals(seeker, team.seeker) && Arrays.equals(chasers, team.chasers);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(house, keeper, seeker);
        result = 31 * result + Arrays.hashCode(chasers);
        return result;
    }
}
