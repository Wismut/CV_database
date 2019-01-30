package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.ContactType;
import ru.javawebinar.webapp.model.Resume;
import ru.javawebinar.webapp.model.Section;
import ru.javawebinar.webapp.model.SectionType;

import java.io.*;
import java.util.EnumMap;
import java.util.Map;

public class DataStreamFileStorage extends FileStorage {

    public DataStreamFileStorage(String path) {
        super(path);
    }

    protected void write(File file, Resume r) {
        try (FileOutputStream fos = new FileOutputStream(file);
             DataOutputStream dos = new DataOutputStream(fos)) {
            dos.writeUTF(r.getFullName());
            dos.writeUTF(r.getLocation());
            dos.writeUTF(r.getHomePage());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> contact : contacts.entrySet()) {
                dos.writeInt(contact.getKey().ordinal());
                dos.writeUTF(contact.getValue());
            }
            for (Map.Entry<SectionType, Section> section : r.getSections().entrySet()) {
                dos.writeInt(section.getKey().ordinal());
                dos.writeUTF(section.getValue().toString());
            }
        } catch (IOException e) {
            throw new WebAppException("Couldn't write file " + file.getAbsolutePath(), r, e);
        }
    }

    protected Resume read(File file) {
        Resume r = new Resume();
        try (InputStream is  = new FileInputStream(file); DataInputStream dis = new DataInputStream(is)) {
            r.setFullName(dis.readUTF());
            r.setLocation(dis.readUTF());
            r.setHomePage(dis.readUTF());
            Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                contacts.put(ContactType.VALUES[size], dis.readUTF());
            }
            r.setContacts(contacts);
            // TODO: 1/30/2019
        } catch (IOException e) {
            throw new WebAppException("Couldn't read file " + file.getAbsolutePath(), r, e);
        }
        return r;
    }
}
