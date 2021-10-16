package hnt.com.service;

import hnt.com.entity.Log;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.List;

public interface LogService {
    void saveLog(MultipartFile file) throws IOException;

    void clear();

    List<Log> getAllLogInfo();
}
