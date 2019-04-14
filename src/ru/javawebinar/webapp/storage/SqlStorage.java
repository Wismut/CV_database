package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.Resume;
import ru.javawebinar.webapp.sql.Sql;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SqlStorage implements IStorage {
    public Sql sql;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sql = new Sql(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sql.execute("DELETE FROM resume");
    }

    @Override
    public void save(Resume r) throws WebAppException {
        sql.execute("INSERT INTO resume (uuid, full_name, location, home_page) VALUES (?, ?, ?, ?)", st -> {
            st.setString(1, r.getUuid());
            st.setString(2, r.getFullName());
            st.setString(3, r.getLocation());
            st.setString(4, r.getHomePage());
            st.execute();
            return null;
        });
    }

    @Override
    public void update(Resume r) {
        sql.execute("UPDATE resume SET full_name = ?, location = ?, home_page = ? WHERE uuid = ?", st -> {
            st.setString(1, r.getFullName());
            st.setString(2, r.getLocation());
            st.setString(3, r.getHomePage());
            st.setString(4, r.getUuid());
            if (st.executeUpdate() == 0) {
                throw new WebAppException("Resume not found", r);
            }
            return null;
        });
    }

    @Override
    public Resume load(final String uuid) {
        return sql.execute("SELECT * FROM resume r WHERE r.uuid = ?", st -> {
            st.setString(1, uuid);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                throw new WebAppException("Resume " + uuid + " is not exist");
            }
            Resume r = new Resume(uuid,
                    rs.getString("full_name"),
                    rs.getString("location"),
                    rs.getString("home_page"));
            return r;
        });
    }

    @Override
    public void delete(final String uuid) {
        sql.execute("DELETE FROM resume WHERE uuid = ?", st -> {
            st.setString(1, uuid);
            if (st.executeUpdate() == 0) {
                throw new WebAppException("Resume " + uuid + " is not exist", uuid);
            }
            return null;
        });
    }

    @Override
    public Collection<Resume> getAllSorted() {
        return sql.execute("SELECT * FROM resume r ORDER BY full_name, uuid", st -> {
            List<Resume> sortedResumes = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Resume r = new Resume(rs.getString("uuid"),
                        rs.getString("full_name"),
                        rs.getString("location"),
                        rs.getString("home_page"));
                sortedResumes.add(r);
            }
            return sortedResumes;
        });
    }

    @Override
    public int size() {
        return sql.execute("SELECT count(*) FROM resume", st -> {
            ResultSet rs = st.executeQuery();
            rs.next();
            return rs.getInt(1);
        });
    }

    @Override
    public boolean isSectionSupported() {
        return false;
    }
}
