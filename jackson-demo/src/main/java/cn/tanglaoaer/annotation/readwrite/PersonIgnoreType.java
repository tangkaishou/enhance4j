package cn.tanglaoaer.annotation.readwrite;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/16
 */
import com.fasterxml.jackson.annotation.JsonIgnoreType;

public class PersonIgnoreType {


    public long    personId = 0;

    public String  name = null;

    public Address address = null;

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @JsonIgnoreType
    public static class Address {
        public String streetName  = null;
        public String houseNumber = null;
        public String zipCode     = null;
        public String city        = null;
        public String country     = null;

        public Address(String streetName, String houseNumber, String zipCode, String city, String country) {
            this.streetName = streetName;
            this.houseNumber = houseNumber;
            this.zipCode = zipCode;
            this.city = city;
            this.country = country;
        }

        public String getStreetName() {
            return streetName;
        }

        public void setStreetName(String streetName) {
            this.streetName = streetName;
        }

        public String getHouseNumber() {
            return houseNumber;
        }

        public void setHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }

}