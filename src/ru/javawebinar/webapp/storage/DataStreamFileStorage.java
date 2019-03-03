package ru.javawebinar.webapp.storage;


import ru.javawebinar.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class DataStreamFileStorage extends FileStorage {
    public DataStreamFileStorage(String path) {
        super(path);
    }

    @Override
    protected void write(OutputStream os, Resume r) throws IOException {
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
                dos.writeUTF(type.name());
                switch (type) {
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) section).getValue());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeCollection(dos, ((MultiTextSection) section).getValues(), value -> dos.writeUTF(value));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeCollection(dos, ((OrganizationSection) section).getValues(), (org) -> {
                            dos.writeUTF(org.getLink().getName());
                            dos.writeUTF(org.getLink().getUrl());
                            writeCollection(dos, org.getPeriods(), (period) -> {
                                writeLocalDate(dos, period.getStartDate());
                                writeLocalDate(dos, period.getEndDate());
                                dos.writeUTF(period.getPosition());
                                dos.writeUTF(period.getContent());
                            });
                        });
                        break;
                }
            }
        }
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate ld) throws IOException {
        Objects.requireNonNull(ld, "LocalDate can't be null, use Period.NOW");
        dos.writeInt(ld.getYear());
        dos.writeUTF(ld.getMonth().name());
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
                        r.addSection(sectionType, new MultiTextSection(readList(dis, dis::readUTF)));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        r.addSection(sectionType, new OrganizationSection(readList(dis, () -> new Organization(new Link(dis.readUTF(), dis.readUTF()),
                                readList(dis, () -> new Organization.Period(readLocalDate(dis), readLocalDate(dis)))))));
                        break;
                }
            }
            return r;
            // TODO: 1/30/2019
        }
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), Month.valueOf(dis.readUTF()), 1);
    }

    private void writeString(DataOutputStream dos, String string) throws IOException {
        dos.writeUTF(string);
    }

    private String readString(DataInputStream dis) throws IOException {
        String str = dis.readUTF();
        return str;
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
