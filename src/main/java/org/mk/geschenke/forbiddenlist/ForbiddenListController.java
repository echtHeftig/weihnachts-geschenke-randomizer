package org.mk.geschenke.forbiddenlist;


import org.mk.geschenke.domain.ForbiddenList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("forbidden")
public class ForbiddenListController {

    @Autowired
    ForbiddenListServiceImpl forbiddenListServiceImpl;

    @PostMapping
    public ResponseEntity<Void> addForbiddenEntry(@RequestBody ForbiddenList forbiddenList) {
        forbiddenListServiceImpl.saveForbiddenPair(forbiddenList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
