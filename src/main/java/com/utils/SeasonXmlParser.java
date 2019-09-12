package com.utils;

import com.app.Player;
import com.app.Season;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;

public class SeasonXmlParser {

    public static Season seasonParser(File xmlFile) {

        Season loadedSeason = new Season();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            NodeList startDateElements = document.getElementsByTagName("StartDate");
            String startDateNode = startDateElements.item(0).getFirstChild().getTextContent();
            loadedSeason.setStartDate(LocalDate.parse(startDateNode, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            NodeList playersElements = document.getElementsByTagName("Player");
            int playerQty = playersElements.getLength();
            HashMap<String, Player> players = new HashMap<>();
            for (int i = 0; i < playerQty; i++) {
                String playerName = playersElements.item(i).getFirstChild().getTextContent();
                players.put(playerName, new Player(playerName));
            }
            loadedSeason.setPlayers(players);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loadedSeason;
    }

}
