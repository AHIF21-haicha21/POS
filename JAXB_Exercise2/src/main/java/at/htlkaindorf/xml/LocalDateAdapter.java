package at.htlkaindorf.xml;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate unmarshal(String s) throws Exception {
        return LocalDate.parse(s, DTF);
    }

    @Override
    public String marshal(LocalDate localDate) throws Exception {
        return localDate.format(DTF);
    }
}
