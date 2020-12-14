import business.League;

public class Main {
    public static void main(String[] args) {
        League l = new League.LeagueBuilder(7).name("Test").category(4).build();
        System.out.println(l);
    }
}
