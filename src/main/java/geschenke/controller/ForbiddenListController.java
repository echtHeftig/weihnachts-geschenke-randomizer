package geschenke.controller;


import geschenke.model.ForbiddenList;
import geschenke.model.ForbiddenListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("forbidden")
public class ForbiddenListController {

    @Autowired
    ForbiddenListServiceImpl forbiddenListServiceImpl;

    @RequestMapping(headers = "Accept=application/json")
    public ResponseEntity<Void> addForbiddenEntry(@RequestBody ForbiddenList forbiddenList) {
        forbiddenListServiceImpl.saveForbiddenPair(forbiddenList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
