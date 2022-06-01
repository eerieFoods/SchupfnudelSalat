package com.github.eeriefoods.snsserver.kurs.api;

import com.github.eeriefoods.snsserver.kurs.domain.KursFactory;
import com.github.eeriefoods.snsserver.kurs.service.IKursService;
import com.github.eeriefoods.snsserver.student.api.StudentData;
import com.github.eeriefoods.snsserver.student.domain.StudentFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "kurs")
public class KursController {

    private final KursDataFactory kursDataFactory;
    private final IKursService kursService;
    private final KursFactory kursFactory;
    private final StudentFactory studentFactory;

    public KursController(KursDataFactory kursDataFactory, IKursService kursService, KursFactory kursFactory, StudentFactory studentFactory) {
        this.kursDataFactory = kursDataFactory;
        this.kursService = kursService;
        this.kursFactory = kursFactory;
        this.studentFactory = studentFactory;
    }

    @GetMapping(path = "{kursId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public KursData getKurs(@PathVariable String kursId) {
        return kursDataFactory.from(kursService.getKurs(kursId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public KursData createKurs(@Valid @RequestBody KursData kursData) {
        return kursDataFactory.from(kursService.createKurs(kursFactory.from(kursData)));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public KursData updateKurs(@Valid @RequestBody KursData kursData) {
        return kursDataFactory.from(kursService.updateKurs(kursFactory.from(kursData)));
    }

    @PutMapping(path = "{kursId}/add",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public KursData addMemberToKurs(@PathVariable String kursId, @Valid @RequestBody StudentData studentData) {
        return kursDataFactory.from(kursService.addStudent(kursId, studentFactory.from(studentData)));
    }

    @DeleteMapping(path = "{kursId}/remove",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public KursData removeMemberFromKurs(@PathVariable String kursId, @Valid @RequestBody StudentData studentData) {
        return kursDataFactory.from(kursService.removeStudent(kursId, studentFactory.from(studentData)));
    }

    @DeleteMapping(path = "{kursId}")
    public void deleteKurs(@PathVariable String kursId) {
        kursService.deleteKurs(kursId);
    }

}
