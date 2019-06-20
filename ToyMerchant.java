/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.client;

/**
 
 * @author HP
 */
public class ToyMerchant {
    private String toy_name,toy_code,toy_description,toy_price,toy_date_of_manufacture,
            toy_batch_number,company_name,street_address,zip_code,country,message;

    
    
    public void setToy_name(String toy_name) {
        this.toy_name = toy_name;
    }

    public void setToy_code(String toy_code) {
        this.toy_code = toy_code;
    }

    public void setToy_description(String toy_description) {
        this.toy_description = toy_description;
    }

    public void setToy_price(String toy_price) {
        this.toy_price = toy_price;
    }

    public void setToy_date_of_manufacture(String toy_date_of_manufacture) {
        this.toy_date_of_manufacture = toy_date_of_manufacture;
    }

    public void setToy_batch_number(String toy_batch_number) {
        this.toy_batch_number = toy_batch_number;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    public String getToy_name() {
        return toy_name;
    }

    public String getToy_code() {
        return toy_code;
    }

    public String getToy_description() {
        return toy_description;
    }

    public String getToy_price() {
        return toy_price;
    }

    public String getToy_date_of_manufacture() {
        return toy_date_of_manufacture;
    }

    public String getToy_batch_number() {
        return toy_batch_number;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getStreet_address() {
        return street_address;
    }

    public String getZip_code() {
        return zip_code;
    }

    public String getCountry() {
        return country;
    }

    public String getMessage() {
        return message;
    }
 
    @Override
    public String toString (){

        return "Toy Name: " + toy_name + "\n" + "Toy Code: " + toy_code + "\n" + "Toy Description:" 
                + toy_description + "\n" + "Toy Price: " + toy_price + "\n" + "Dateof Manufacture: " 
                + toy_date_of_manufacture + "\n" + "Toy Batch Number : " + toy_batch_number + "\n" 
                + "Company Name: " + company_name + "\n" + "Street Address: " +street_address + "\n"
                + "Zip code: " + zip_code + "\n" + "Country: " + country + "\n" + "Message: " + message + "\n" ;

    }

}
