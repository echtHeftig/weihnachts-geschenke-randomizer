package org.mk.geschenke.presenttable;

import org.mk.geschenke.domain.PresentTable;

import java.util.HashMap;
import java.util.List;

public interface PresentTableService {

    List<PresentTable> getPresentTableList() throws SchenkenderBeschenkenderException;

    HashMap<String, String> getForbiddenList();
}
