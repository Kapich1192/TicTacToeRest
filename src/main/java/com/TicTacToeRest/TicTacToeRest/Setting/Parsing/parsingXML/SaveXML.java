package com.TicTacToeRest.TicTacToeRest.Setting.Parsing.parsingXML;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.IOException;
public interface SaveXML {

    default void startDocument(XMLStreamWriter writer,int idGame, String player1, String player2) throws IOException, XMLStreamException {

        writer.writeStartDocument();
        writer.writeCharacters("\n");
        writer.writeStartElement("Gameplay");
        writer.writeCharacters("\n");
        writer.writeStartElement("Game");
        writer.writeCharacters("\n");
    }

    default void saveElement(XMLStreamWriter writer, String  step ,int number,String x,String y,String marker) throws  XMLStreamException {
        String plId;
        if(marker.equals("O"))plId="2";else plId="1";
        writer.writeCharacters("\t");
        writer.writeStartElement("Step" );
        writer.writeAttribute("num",""+number );
        writer.writeAttribute("playerId",plId );
        writer.writeAttribute("x",x );
        writer.writeAttribute("y",y );
        writer.writeCharacters(""+number);
        writer.writeEndElement();
        writer.writeCharacters("\n");
        writer.flush();
    }
    default void saveElement(XMLStreamWriter writer, String  step ,int number,String x,String marker) throws  XMLStreamException {
        String plId;
        if(marker.equals("|O|"))plId="2";else plId="1";
        writer.writeCharacters("\t");
        writer.writeStartElement("Step" );
        writer.writeAttribute("num",""+number );
        writer.writeAttribute("playerId",plId );
        writer.writeAttribute("x",x );
        writer.writeCharacters(""+number);
        writer.writeEndElement();
        writer.writeCharacters("\n");
        writer.flush();
    }
    default void saveElement(XMLStreamWriter writer, String  result ) throws  XMLStreamException
    {
        writer.writeEndElement();
        writer.writeCharacters("\n");
        writer.writeStartElement("GameResult");
        writer.writeCharacters(result);
        writer.writeEndElement();
        writer.writeCharacters("\n");
        writer.writeEndElement();
        writer.flush();
    }

    default void endDocument(XMLStreamWriter writer)throws XMLStreamException  {
        writer.writeCharacters("\n");


    }
}
