/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Note;

/**
 *
 * @author 794456
 */
public class NoteDB {

    private int countrows;

    public int insert(Note note) {
        EntityManager em = DBUnit.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(note);
            trans.commit();
            countrows = 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot insert " + note.toString(), ex);
        } finally {
            em.close();
        }

        return countrows;

    }

    public List<Note> getAll() {
        EntityManager em = DBUnit.getEmFactory().createEntityManager();
        try {
            List<Note> noteList = em.createNamedQuery("Notes.findAll", Note.class).getResultList();
            return noteList;
        } finally {
            em.close();
        }
    }

    public Note get(int noteId) {
      
        EntityManager em = DBUnit.getEmFactory().createEntityManager();
        try {
            Note note = em.find(Note.class, noteId);
            return note;
        } finally {
            em.close();
        }
    }

    public int delete(Note note) {

        int countNum = 0;

        EntityManager em = DBUnit.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.remove(em.merge(note));
            trans.commit();

            countNum = 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot delete " + note.toString(), ex);
        } finally {
            em.close();
        }

        return countNum;
    }

    public int update(Note note) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Note getNote(int noteID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
