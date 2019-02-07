package ru.javawebinar.webapp.storage;

import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;
import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.*;

import java.io.*;
import java.util.*;

public class DataStreamFileStorage extends FileStorage {

    private static final String NULL = "null";

    public DataStreamFileStorage(String path) {
        super(path);
    }

    @Override
    protected void write(OutputStream os, Resume r) throws IOException {
//        try (FileOutputStream fos = new FileOutputStream(os);
        try (DataOutputStream dos = new DataOutputStream(os)) {
            writeString(dos, r.getUuid());
            writeString(dos, r.getFullName());
            writeString(dos, r.getLocation());
            writeString(dos, r.getHomePage());
            Map<ContactType, String> contacts = r.getContacts();
            writeCollection(dos, contacts.entrySet(), entry -> {
                dos.writeInt(entry.getKey().ordinal());
                writeString(dos, entry.getValue());
            });
            Map<SectionType, Section> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                SectionType type = entry.getKey();
                Section section = entry.getValue();
                writeString(dos, type.name());
                switch (type) {
                    case OBJECTIVE:
//                        writeString(dos, ((TextSection) section).getValue());
                        break;
                    case ACHIEVEMENT:
//                        writeString(dos, ((TextSection) section).getValue());
                        break;
                    case QUALIFICATIONS:
                        writeCollection(dos, ((MultiTextSection) section).getValues(), value -> writeString(dos, value));
                        break;
                    case EXPERIENCE:
//                        writeString(dos, ((TextSection) section).getValue());
                        break;
                    case EDUCATION:
//                        writeString(dos, ((TextSection) section).getValue());
                        break;
                }
            }
        }
    }

    @Override
    protected Resume read(InputStream is) throws IOException {
        Resume r = new Resume();
        try (DataInputStream dis = new DataInputStream(is)) {
            r.setUuid(readString(dis));
            r.setFullName(readString(dis));
            r.setLocation(readString(dis));
            r.setHomePage(readString(dis));
            Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                contacts.put(ContactType.VALUES[size], readString(dis));
            }
            r.setContacts(contacts);
            final int sectionSize = dis.readInt();
            for (int i = 0; i < sectionSize; i++) {
                SectionType sectionType = SectionType.valueOf(readString(dis));
                switch (sectionType) {
                    case OBJECTIVE:
                       r.addObjective(readString(dis));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        r.addSection(sectionType, new MultiTextSection(readList(dis, () -> readString(dis))));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
//                        writeString(dos, ((TextSection) section).getValue());
                        break;
                }
            }
            return r;
            // TODO: 1/30/2019
        }
    }

    private void writeString(DataOutputStream dos, String string) throws IOException {
        dos.writeUTF(string == null ? NULL : string);
    }

    private String readString(DataInputStream dis) throws IOException {
        String str = dis.readUTF();
        return str.equals(NULL) ? null : str;
    }

    private interface ElementWriter<T> {
        void write(T t) throws IOException;
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, ElementWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }

    private interface ElementReader<T> {
        T read() throws IOException;
    }

    private <T> List<T> readList(DataInputStream dis, ElementReader<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }

}
