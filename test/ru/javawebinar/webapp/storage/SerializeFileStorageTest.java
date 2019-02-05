package ru.javawebinar.webapp.storage;

public class SerializeFileStorageTest extends AbstractStorageTest {
    {
        storage = new SerializeFileStorage("file_storage");
    }
}
