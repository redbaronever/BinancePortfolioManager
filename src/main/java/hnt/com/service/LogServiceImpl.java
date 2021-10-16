package hnt.com.service;

import hnt.com.entity.Log;
import hnt.com.repository.LogRepository;
import hnt.com.service.helper.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogRepository logRepository;

    @PersistenceContext
    public EntityManager entityManager;

    @Override
    public void saveLog(MultipartFile file) throws IOException {
        // 1. Read records
        List<Log> logs = CSVHelper.readLog(file.getInputStream());

        // 2. Sorting the record based on date ASC
        logs.sort(new Comparator<Log>() {
            @Override
            public int compare(Log o1, Log o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });

        // 3. If the records is already existed then don't insert
        List<Log> existedLogs = logRepository.findAll();
        logs = logs.stream().filter(item->!existedLogs.contains(item)).collect(Collectors.toList());

        logRepository.saveAll(logs);
    }

    @Override
    public void clear() {
        logRepository.clear(entityManager);
    }
}
