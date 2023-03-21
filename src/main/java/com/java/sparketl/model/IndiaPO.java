package com.java.sparketl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="india_po")
public class IndiaPO {

    @Column(name = "officename")
    String officename;

    @Id
    @Column(name = "pincode")
    String pincode;

    @Column(name = "officetype")
    String officeType;

    @Column(name = "deliverystatus")
    String deliverystatus;

    @Column(name = "divisionname")
    String divisionname;

    @Column(name = "regionname")
    String regionname;

    @Column(name = "circlename")
    String circlename;

    @Column(name = "taluk")
    String taluk;

    @Column(name = "districtname")
    String districtname;

    @Column(name = "statename")
    String statename;

    @Column(name = "telephone")
    String telephone;

    @Column(name = "relatedsuboffice")
    String relatedSuboffice;

    @Column(name = "relatedheadoffice")
    String relatedHeadoffice;

    @Column(name = "longitude")
    String longitude;

    @Column(name = "latitude")
    String latitude;

    public IndiaPO() {
    }

    public IndiaPO(String officename, String pincode, String officeType, String deliverystatus, String divisionname, String regionname, String circlename, String taluk, String districtname, String statename, String telephone, String relatedSuboffice, String relatedHeadoffice, String longitude, String latitude) {
        this.officename = officename;
        this.pincode = pincode;
        this.officeType = officeType;
        this.deliverystatus = deliverystatus;
        this.divisionname = divisionname;
        this.regionname = regionname;
        this.circlename = circlename;
        this.taluk = taluk;
        this.districtname = districtname;
        this.statename = statename;
        this.telephone = telephone;
        this.relatedSuboffice = relatedSuboffice;
        this.relatedHeadoffice = relatedHeadoffice;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public IndiaPO(long name, String shortDescription, String bestSellingRank, String shortDescription1, String bestSellingRank1, String thumbnailImage, String salePrice, String manufacturer, String url, String type, String image, String customerReviewCount, String shipping, String salePriceRange, String url1, boolean published) {
    }

    public String getOfficename() {
        return officename;
    }

    public void setOfficename(String officename) {
        this.officename = officename;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getOfficeType() {
        return officeType;
    }

    public void setOfficeType(String officeType) {
        this.officeType = officeType;
    }

    public String getDeliverystatus() {
        return deliverystatus;
    }

    public void setDeliverystatus(String deliverystatus) {
        this.deliverystatus = deliverystatus;
    }

    public String getDivisionname() {
        return divisionname;
    }

    public void setDivisionname(String divisionname) {
        this.divisionname = divisionname;
    }

    public String getRegionname() {
        return regionname;
    }

    public void setRegionname(String regionname) {
        this.regionname = regionname;
    }

    public String getCirclename() {
        return circlename;
    }

    public void setCirclename(String circlename) {
        this.circlename = circlename;
    }

    public String getTaluk() {
        return taluk;
    }

    public void setTaluk(String taluk) {
        taluk = taluk;
    }

    public String getDistrictname() {
        return districtname;
    }

    public void setDistrictname(String districtname) {
        this.districtname = districtname;
    }

    public String getStatename() {
        return statename;
    }

    public void setStatename(String statename) {
        this.statename = statename;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRelatedSuboffice() {
        return relatedSuboffice;
    }

    public void setRelatedSuboffice(String relatedSuboffice) {
        this.relatedSuboffice = relatedSuboffice;
    }

    public String getRelatedHeadoffice() {
        return relatedHeadoffice;
    }

    public void setRelatedHeadoffice(String relatedHeadoffice) {
        this.relatedHeadoffice = relatedHeadoffice;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "IndiaPO{" +
                "officename='" + officename + '\'' +
                ", pincode='" + pincode + '\'' +
                ", officeType='" + officeType + '\'' +
                ", deliverystatus='" + deliverystatus + '\'' +
                ", divisionname='" + divisionname + '\'' +
                ", regionname='" + regionname + '\'' +
                ", circlename='" + circlename + '\'' +
                ", Taluk='" + taluk + '\'' +
                ", districtname='" + districtname + '\'' +
                ", statename='" + statename + '\'' +
                ", telephone='" + telephone + '\'' +
                ", relatedSuboffice='" + relatedSuboffice + '\'' +
                ", relatedHeadoffice='" + relatedHeadoffice + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }
}
