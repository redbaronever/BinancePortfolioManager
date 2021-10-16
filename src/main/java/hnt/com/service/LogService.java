package hnt.com.service;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.io.IOException;

public interface LogService {
    void saveLog(MultipartFile file) throws IOException;

    void clear();
}
