package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.ContactType;
import ru.javawebinar.webapp.model.Resume;
import ru.javawebinar.webapp.model.Section;
import ru.javawebinar.webapp.model.SectionType;

import java.io.*;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class FileStorage extends AbstractStorage<File> {

    private File dir;

    public FileStorage(String path) {
        this.dir = new File(path);
        if (!dir.isDirectory() || !dir.canWrite())
            throw new IllegalArgumentException("'" + path + "' is not a directory or is not writable");
    }

    @Override
    protected void doSave(File file, Resume r) {
        try {
            file.createNewFile();
            write(file, r);
        } catch (IOException e) {
            throw new WebAppException("Couldn't create file " + file.getAbsolutePath(), r, e);
        }
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

        }
        return r;
    }

    @Override
    protected File getContext(String fileName) {
        return new File(fileName);
    }

    @Override
    protected boolean exist(File file) {
        return file.exists();
    }

    @Override
    protected void doClear() {
        File[] files = dir.listFiles();
        if (files == null) return;
        for (File file : files) {
            doDelete(file);
        }
    }

    @Override
    protected void doUpdate(File file, Resume r) {
        write(file, r);
    }

    @Override
    public Resume doLoad(File file) {
        return read(file);
    }

    @Override
    protected void doDelete(File file) {
        if (file.delete())
            throw new WebAppException("File " + file.getAbsolutePath() + " can not be deleted");
    }

    @Override
    protected List<Resume> doGetAll() {
        File[] files = dir.listFiles();
        List<Resume> resumeList = new ArrayList<>();
        if (files == null) {
            return resumeList;
        }
        for (File file : files) {
            resumeList.add(doLoad(file));
        }
        return resumeList;
    }

    @Override
    public int size() {
        String[] list = dir.list();
        if (list == null) {
            throw new WebAppException("Couldn't read directory " + dir.getAbsolutePath());
        }
        return list.length;
    }
}
