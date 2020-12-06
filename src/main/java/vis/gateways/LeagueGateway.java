package vis.gateways;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import vis.business.League;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class LeagueGateway {
    protected ObjectMapper mapper;
    protected InputStream input;
    private ArrayList<League> leagues;

    static final ArrayList columns = new ArrayList<>(
            Arrays.asList("leagueID", "name", "category")
    );
    public LeagueGateway(){
        this.leagues = this.fetch();
    };

    public ArrayList<League> fetch() {
        TypeReference<ArrayList<League>> typeReference = new TypeReference<ArrayList<League>>() {};
        ArrayList<League> l = new ArrayList<>();
        this.mapper = new XmlMapper();

        try {
            this.input = new FileInputStream(new File("./xml/League.xml"));
            l = mapper.readValue(input, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return l;
    }

    public Integer insert(League l) {
        this.leagues.add(l);

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

