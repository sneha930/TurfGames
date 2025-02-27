package com.lcwd.game.turf.GameTurf.dtos;

import jakarta.validation.constraints.NotBlank;

public class AddressDto {

    public String id;

    @NotBlank(message = "Address Line 1 is required")
    public String line1;

    public String line2; // Optional field, so no validation

    @NotBlank(message = "City is required")
    public String city;

    @NotBlank(message = "State is required")
    public String state;

    @NotBlank(message = "Country is required")
    public String country;

    @NotBlank(message = "Pincode is required")
//    @Pattern(regexp = "\\d{5,6}", message = "Pincode must be a valid 5 or 6-digit number")
    public String pincode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public AddressDto(String id, String line1, String line2, String city, String state, String country, String pincode) {
        this.id = id;
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
    }

    public AddressDto() {
    }


    // ✅ MANUAL BUILDER CLASS
    public static class Builder {
        private String id;
        private String line1;
        private String line2;
        private String city;
        private String state;
        private String country;
        private String pincode;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder line1(String line1) {
            this.line1 = line1;
            return this;
        }

        public Builder line2(String line2) {
            this.line2 = line2;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder pincode(String pincode) {
            this.pincode = pincode;
            return this;
        }

        public AddressDto build() {
            return new AddressDto(id, line1, line2, city, state, country, pincode);
        }
    }

    @Override
    public String toString() {
        return "AddressDto{" +
                "id=" + id +
                ", line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", pincode='" + pincode + '\'' +
                '}';
    }
}
