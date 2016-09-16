package com.codingmentorteam3.services;

import com.codingmentorteam3.daos.NoteDaoImpl;
import com.codingmentorteam3.entities.Note;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Bicsak Daniel
 */
@Stateless
public class NoteService {

    @Inject
    private NoteDaoImpl noteDao;

    public void createNote(Note note) {
        noteDao.create(note);
    }

    public Note getNote(Long noteId) {
        return noteDao.read(noteId);
    }

    public Note editNote(Note note) {
        return noteDao.update(note);
    }

    public Note deleteNote(Note note) {
        return noteDao.delete(note);
    }

}
