package hnt.com.controller;

import hnt.com.entity.Log;
import hnt.com.message.CR;
import hnt.com.service.LogService;
import hnt.com.service.helper.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class XHRController {

    @Autowired
    LogService logService;

    @PostMapping("/portfolio/update")
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                logService.saveLog(file);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }

        return;
    }

    @PostMapping("/portfolio/clear")
    public void clear(Model model) {
        logService.clear();
        return;
    }

    @GetMapping("/portfolio/loading")
    public CR<List<Log>> loading() {
        return new CR<List<Log>> (200, logService.getAllLogInfo());
    }
}
