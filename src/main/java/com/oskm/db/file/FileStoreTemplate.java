package com.oskm.db.file;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.*;

/**
 * Created by oskmkr on 2015-12-26.
 */
@Repository
public class FileStoreTemplate<T> {
    private static final Logger LOG = LoggerFactory.getLogger(FileStoreTemplate.class);

    public void save(String fileName, T obj) throws IOException {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = FileUtils.openOutputStream(new File(fileName + ".db"));

            oos = new ObjectOutputStream(fos);

            oos.writeObject(obj);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {

            if (null != fos) {
                fos.close();
            }

            if (null != oos) {
                oos.close();
            }
        }
    }

    public T load(String fileName) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        T readObj = null;

        try {
            fis = FileUtils.openInputStream(new File(fileName + ".db"));
            ois = new ObjectInputStream(fis);

            readObj = (T) ois.readObject();

        } catch (Exception e) {

        } finally {
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != ois) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return readObj;
    }
}
