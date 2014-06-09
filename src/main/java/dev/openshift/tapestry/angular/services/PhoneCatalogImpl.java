package dev.openshift.tapestry.angular.services;

import java.io.*;
import java.util.Iterator;
import java.util.List;


import dev.openshift.tapestry.angular.data.Phone;
import org.apache.tapestry5.internal.grid.CollectionGridDataSource;
import org.apache.tapestry5.ioc.internal.util.CollectionFactory;
import dev.openshift.tapestry.angular.data.Product;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.TypeReference;




public class PhoneCatalogImpl implements PhoneCatalog
{

    private List<Phone> catalog;

    private StringBuilder responseStrBuilder;

    private final String path = "dev/openshift/tapestry/angular/pages/";

    public PhoneCatalogImpl() {

        try {

            final String filePath = path + "phones.json";
            // read the json file
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(filePath);

            BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr);

            final ObjectMapper mapper = new ObjectMapper();


            catalog = mapper.readValue(responseStrBuilder.toString(), new TypeReference<List<Phone>>() { });

        }

        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }


    			
	public List<Phone> getPhones() {
		 return catalog;
	}

    public String getPhonesAsString() {
        return responseStrBuilder.toString();
    }


}
