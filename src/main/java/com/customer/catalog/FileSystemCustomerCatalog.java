package com.customer.catalog;

import com.customer.util.Constants;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileSystemCustomerCatalog implements CustomerCatalog {

    private List<String> readFile(final String fileName) throws IOException, URISyntaxException {

        final List<String> lines = new ArrayList<>();

        return Files.readAllLines(
                Paths.get(this.getClass().getResource("/" + fileName).toURI()), Charset.defaultCharset());
    }

    @Override
    public List<String> getAllCustomer() {

        try {
            return readFile(Constants.FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
