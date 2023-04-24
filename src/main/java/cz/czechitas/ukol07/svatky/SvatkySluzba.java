package cz.czechitas.ukol07.svatky;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Month;
import java.time.MonthDay;
import java.util.List;

public class SvatkySluzba {

    private final ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();
    private final Path cestaKDatum = Path.of("svatky.json");
    private final SeznamSvatku seznamSvatku;

    public SvatkySluzba() throws IOException {
        // načíte seznam svátků ze souboru svatky.json

        seznamSvatku = objectMapper.readValue(cestaKDatum.toFile(), SeznamSvatku.class);
    }

    public List<String> vyhledatSvatkyDnes() {
        return vyhledatSvatkyKeDni(MonthDay.now());
    }

    public List<String> vyhledatSvatkyKeDni(MonthDay day) {
        // získat seznam svátků
        // převést na Stream
        // pomocí metody filter() vybrat jen ty, které odpovídají zadanému dni (porovnat MonthDay pomocí metodyequals())
        // pomocí metody map() získat z objektu jméno
        // pomocí toList() převést na List

        List<String> svatkyKeDni = seznamSvatku.getSvatky().stream()
                .filter(s -> s.getDen().equals(day))
                .map(Svatek::getJmeno)
                .toList();

        return svatkyKeDni;
    }
}
