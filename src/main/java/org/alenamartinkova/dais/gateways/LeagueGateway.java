package org.alenamartinkova.dais.gateways;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.alenamartinkova.dais.business.League;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class LeagueGateway {
    private ArrayList<League> leagues;

    static final ArrayList<String> columns = new ArrayList<>(
            Arrays.asList("leagueID", "name", "category")
    );
    public LeagueGateway(){
        this.leagues = fetch();
    };

    public static ArrayList<League> fetch() {
        TypeReference<ArrayList<League>> typeReference = new TypeReference<>() {};
        ArrayList<League> l = new ArrayList<>();
        ObjectMapper mapper = new XmlMapper();

        try {
            InputStream input = new FileInputStream(new File("./xml/League.xml"));
            l = mapper.readValue(input, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return l;
    }

    public static League fetchByID(Integer id) {
        var typeReference = new TypeReference<ArrayList<League>>() {};
        var l = new ArrayList<League>();
        ObjectMapper mapper = new XmlMapper();

        try {
            InputStream input = new FileInputStream(new File("./xml/League.xml"));
            l = mapper.readValue(input, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        League league = null;

        for(int i = 0; i < l.size(); i++) {
            if(l.get(i).getLeagueID() == id) {
                league = new League.LeagueBuilder(id).name(l.get(i).getName()).category(l.get(i).getCategory()).build();
                break;
            }
        }

        return league;
    }

    public Integer insert(League l) {
        this.leagues.add(l);

        ObjectMapper mapper = new XmlMapper();
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.putPOJO("League", leagues);

        try {
            mapper.writeValue(new File("./xml/League.xml"), rootNode);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 1;
    }
}

