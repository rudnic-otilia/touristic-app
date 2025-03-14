package org.example;

public class Location {
    //obligatoriu
    private String county;
    private Integer sirutaCode;
    //optionali
    private String locality;
    private String adminUnit;
    private String address;
    private Integer latitude;
    private Integer longitude;

    //builder
    static class Builder {
        private String county;
        private Integer sirutaCode;
        private String locality = "";
        private String adminUnit = "";
        private String address = "";
        private Integer latitude = 0;
        private Integer longitude = 0;

        public Builder (String county, Integer sirutaCode) {
            this.county = county;
            this.sirutaCode = sirutaCode;
        }

        public Builder setLocality(String locality) {
            this.locality = locality;
            return this;
        }

        public Builder setAdminUnit(String adminUnit) {
            this.adminUnit = adminUnit;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setLatitude(Integer latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder setLongitude(Integer longitude) {
            this.longitude = longitude;
            return this;
        }

        public Location build() {
            return new Location(this);
        }
    }

    private Location(Builder builder) {
        county = builder.county;
        sirutaCode = builder.sirutaCode;
        locality = builder.locality;
        adminUnit = builder.adminUnit;
        address = builder.address;
        latitude = builder.latitude;
        longitude = builder.longitude;
    }
}
