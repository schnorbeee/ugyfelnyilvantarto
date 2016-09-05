package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.Note;
import javax.ejb.Stateless;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class NoteDaoImpl extends AbstractDao<Note> {

    public NoteDaoImpl() {
        super(Note.class);
    }

}
