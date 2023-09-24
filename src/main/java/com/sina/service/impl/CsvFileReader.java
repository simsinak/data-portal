package com.sina.service.impl;

import com.sina.domain.FileInfo;
import com.sina.domain.Record;
import com.sina.service.FileReader;
import com.sina.utility.DateFormatterUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Sina Askarnejad
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class CsvFileReader implements FileReader {

    private final static String DOUBLE_QUOTE = "\"";

    private final DateFormatterUtility dateFormatterUtility;

    @Override
    public FileInfo readContent(MultipartFile file) throws IOException {
        try (Stream<String> lines = new BufferedReader(new InputStreamReader(file.getInputStream())).lines()) {
            List<Record> records = lines.skip(1)
                    .map(this::convertLineToRecord)
                    .collect(Collectors.toList());
            return new FileInfo(records);
        } catch (IOException | UncheckedIOException e) {
            log.error("Failed To read file Content" + file.getOriginalFilename(), e);
            throw e;
        }
    }

    private Record convertLineToRecord(String line) {
        String[] tokens = line.trim().split(" *, *");
        return Record.builder()
                .source(trimContent(tokens[0]))
                .codeListCode(trimContent(tokens[1]))
                .code(trimContent(tokens[2]))
                .displayValue(trimContent(tokens[3]))
                .longDescription(trimContent(tokens[4]))
                .fromDate(dateFormatterUtility.convertDate(trimContent(tokens[5])))
                .toDate(dateFormatterUtility.convertDate(trimContent(tokens[6])))
                .sortingPriority(trimContent(tokens[7]).isBlank() ? null : Integer.parseInt(trimContent(tokens[7])))
                .build();
    }

    private String trimContent(String content) {
        if (content.startsWith(DOUBLE_QUOTE) && content.endsWith(DOUBLE_QUOTE)) {
            return content.substring(1, content.length() - 1);
        } else if (content.startsWith(DOUBLE_QUOTE)) {
            return content.substring(1);
        } else if (content.endsWith(DOUBLE_QUOTE)) {
            return content.substring(0, content.length() - 1);
        }
        return content;
    }
}
