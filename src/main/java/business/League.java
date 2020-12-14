package business;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.xml.bind.annotation.XmlID;

@JacksonXmlRootElement(localName = "League")
public class League {
    @XmlID
    Integer leagueID;
    String name;
    Integer category;

    public League(LeagueBuilder lb) {
        this.leagueID = lb.id;
        this.name = lb.name;
        this.category = lb.category;
    }

    public League(){};

    public Integer getLeagueID() {
        return this.leagueID;
    }

    public Integer getCategory() {
        return this.category;
    }

    public String getName() {
        return this.name;
    }

    public static class LeagueBuilder
    {
        private Integer id;
        private String name;
        private Integer category;

        public LeagueBuilder(Integer id) {
            this.id = id;
        }

        public LeagueBuilder name(String name) {
            this.name = name;
            return this;
        }
        public LeagueBuilder category(Integer category) {
            this.category = category;
            return this;
        }

        //Return the finally consrcuted User object
        public League build() {
            League l = new League(this);
            return l;
        }
    }
}

