package business;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.xml.bind.annotation.XmlID;

@JacksonXmlRootElement(localName = "League")
public class League {
    @XmlID
    Integer leagueID;
    String name;
    Integer category;

    public League(Integer id, String n,  Integer c) {
        this.leagueID = id;
        this.name = n;
        this.category = c;
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
}

