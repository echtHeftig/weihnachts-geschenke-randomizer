package org.mk.geschenke.controller;


import org.mk.geschenke.model.ForbiddenList;
import org.mk.geschenke.model.ForbiddenListServiceImpl;
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
