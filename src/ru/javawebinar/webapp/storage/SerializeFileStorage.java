package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.Resume;

import java.io.*;

public class SerializeFileStorage extends FileStorage {

    public SerializeFileStorage(String path) {
        super(path);
    }

    protected void write(File file, Resume r) {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(r);
        } catch (IOException e) {
            throw new WebAppException("Couldn't write file " + file.getAbsolutePath(), r, e);
        }
    }

    protected Resume read(File file) {
        Resume r = new Resume();
        try (InputStream is  = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(is)) {
            return (Resume) ois.readObject();
        } catch (IOException e) {
            throw new WebAppException("Couldn't read file " + file.getAbsolutePath(), r, e);
        } catch (ClassNotFoundException e) {
            throw new WebAppException("Error read resume", e);
        }
    }
}
