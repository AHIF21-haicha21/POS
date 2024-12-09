package at.htlkaindorf.adapters;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public LocalDate unmarshal(String s) throws Exception {
        return LocalDate.parse(s, DTF);
    }

    @Override
    public String marshal(LocalDate localDate) throws Exception {
        return DTF.format(localDate);
    }
}
