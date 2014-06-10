package dev.openshift.tapestry.angular.services;

import java.io.*;
import java.util.Hashtable;
import java.util.List;


import dev.openshift.tapestry.angular.data.Phone;
import dev.openshift.tapestry.angular.data.PhoneDetails;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;




public class PhoneCatalogImpl implements PhoneCatalog
{

    private List<Phone> catalog;

    private Hashtable<String, PhoneDetails> hPhoneDetails;

    private StringBuilder phonesStrBuilder;

    private Hashtable<String, String> hPhoneDetailsJSON;

    private final String path = "dev/openshift/tapestry/angular/pages/phones/";

    public PhoneCatalogImpl() {

        try {

            final String filePath = path + "phones.json";
            // read the json file
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(filePath);

            BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            phonesStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                    phonesStrBuilder.append(inputStr);

            final ObjectMapper mapper = new ObjectMapper();


            catalog = mapper.readValue(phonesStrBuilder.toString(), new TypeReference<List<Phone>>() { });

            hPhoneDetails = new Hashtable();
            hPhoneDetailsJSON = new Hashtable();

            for (Phone phone : catalog) {

                String fileDetails = path + phone.getId() + ".json";
                // read the json file
                InputStream inputStreamDetails = classLoader.getResourceAsStream(fileDetails);

                BufferedReader streamReaderDetails = new BufferedReader(new InputStreamReader(inputStreamDetails, "UTF-8"));
                StringBuilder strBuilder = new StringBuilder();

                String inputStr2;
                while ((inputStr2 = streamReaderDetails.readLine()) != null)
                    strBuilder.append(inputStr2);

                ObjectMapper mapper2 = new ObjectMapper();


                PhoneDetails details = mapper2.readValue(strBuilder.toString(), PhoneDetails.class);


                hPhoneDetails.put(phone.getId(),details);
                hPhoneDetailsJSON.put(phone.getId(),strBuilder.toString());




            }

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
        return phonesStrBuilder.toString();
    }


    public PhoneDetails getPhonesDetails(String id) {
        return hPhoneDetails.get(id);
    }


    public String getPhonesDetailsAsString(String id) {
        return hPhoneDetailsJSON.get(id);
    }


}
