package hnt.com.service.helper;

import hnt.com.entity.Log;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CSVHelper {

    public static String[] TYPE = {"text/csv", "application/octet-stream"};

    static String[] HEADERS = {"Id", "Title", "Description", "Published"};

    public static boolean hasCSVFormat(MultipartFile file) {
        Optional<String> isValidType = Arrays.stream(TYPE).filter(item->item.contentEquals(file.getContentType())).findAny();
        if (!isValidType.isPresent()) {
            return false;
        }
        return true;
    }

    public static List<Log> readLog(InputStream is) {
        try {
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            CSVParser csvParser = new CSVParser(fileReader,
                    CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

            List<Log> logs = new ArrayList<Log>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            for (CSVRecord csvRecord : csvRecords) {
                Log log = new Log(
                        csvRecord.get("Pair"),
                        csvRecord.get("Side"),
                        new BigDecimal(csvRecord.get("Price").replaceAll(",", "")),
                        csvRecord.get("Executed").replaceAll(",", ""),
                        csvRecord.get("Amount").replaceAll(",", ""),
                        csvRecord.get("Fee").replaceAll(",", ""),
                        LocalDateTime.parse(csvRecord.get(0).replaceAll(",", ""), formatter)
                );

                logs.add(log);
            }

            fileReader.close();
            csvParser.close();

            return logs;
        } catch (Exception e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}