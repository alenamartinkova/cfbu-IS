package vis.gateways;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class TableXML {
    protected String tableName;
    protected ArrayList<String> columns;

    protected ObjectMapper mapper;
    protected InputStream input;

    public TableXML(String tableName, ArrayList columns) {
        this.tableName = tableName;
        this.columns = columns;
        this.mapper = new XmlMapper();

        try {
            this.input = new FileInputStream(new File("./xml/" + tableName + ".xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected String buildInsert(int valuesCount, int from_column) {
        String query = "";


        return query;
    }

    protected String buildUpdate(int from_column) {
        String query = "";
        return query;
    }
}