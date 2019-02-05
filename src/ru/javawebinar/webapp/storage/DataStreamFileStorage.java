package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.*;

import java.io.*;
import java.util.EnumMap;
import java.util.Map;

public class DataStreamFileStorage extends FileStorage {

    private static final String NULL = "null";

    public DataStreamFileStorage(String path) {
        super(path);
    }

    protected void write(File file, Resume r) {
        try (FileOutputStream fos = new FileOutputStream(file);
             DataOutputStream dos = new DataOutputStream(fos)) {
            writeString(dos, r.getFullName());
            writeString(dos, r.getLocation());
            writeString(dos, r.getHomePage());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> contact : contacts.entrySet()) {
                dos.writeInt(contact.getKey().ordinal());
                writeString(dos, contact.getValue());
            }
            Map<SectionType, Section> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                SectionType type = entry.getKey();
                Section section = entry.getValue();
                writeString(dos, type.name());
                switch (type) {
                    case OBJECTIVE:
                        writeString(dos, ((TextSection) section).getValue());
                        break;
                    case ACHIEVEMENT:
                        writeString(dos, ((TextSection) section).getValue());
                        break;
                    case QUALIFICATIONS:
                        writeString(dos, ((TextSection) section).getValue());
                        break;
                    case EXPERIENCE:
                        writeString(dos, ((TextSection) section).getValue());
                        break;
                    case EDUCATION:
                        writeString(dos, ((TextSection) section).getValue());
                        break;
                }
            }
        } catch (IOException e) {
            throw new WebAppException("Couldn't write file " + file.getAbsolutePath(), r, e);
        }
    }

    protected Resume read(File file) {
        Resume r = new Resume(file.getName());
        try (InputStream is  = new FileInputStream(file); DataInputStream dis = new DataInputStream(is)) {
            r.setFullName(readString(dis));
            r.setLocation(readString(dis));
            r.setHomePage(readString(dis));
            Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                contacts.put(ContactType.VALUES[size], readString(dis));
            }
            r.setContacts(contacts);
            // TODO: 1/30/2019
        } catch (IOException e) {
            throw new WebAppException("Couldn't read file " + file.getAbsolutePath(), r, e);
        }
        return r;
    }

    private void writeString(DataOutputStream dos, String string) throws IOException {
        dos.writeUTF(string == null ? NULL : string);
    }

    private String readString(DataInputStream dis) throws IOException {
        String str = dis.readUTF();
        return str.equals(NULL) ? null : str;
    }

}
