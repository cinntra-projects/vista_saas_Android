package com.cinntra.vista.model.BPModel;

import java.io.Serializable;
import java.util.ArrayList;

public class BusinessPartnerAllListResponseModel implements Serializable
{
    public String message;
    public int status;
    public ArrayList<Datum> data = new ArrayList<>();
    public Meta meta;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<Datum> getData() {
        return data;
    }

    public void setData(ArrayList<Datum> data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public class Datum implements Serializable {
        public String id;
        public String CardCode;
        public String CardName;
        public String Industry;
        public String CardType;
        public String Website;
        public String EmailAddress;
        public String Phone1;
        public String CountryCode;
        public String DiscountPercent;
        public String Currency;
        public String IntrestRatePercent;
        public String CommissionPercent;
        public String Notes;
        public String PayTermsGrpCode;
        public String CreditLimit;
        public String AttachmentEntry;
        public String SalesPersonCode;
        public String ContactPerson;
        public String U_LEADID;
        public String U_LEADNM;
        public String U_PARENTACC;
        public String U_BPGRP;
        public String U_CONTOWNR;
        public String U_RATING;
        public String U_TYPE;
        public String U_ANLRVN;
        public String U_CURBAL;
        public String U_ACCNT;
        public String U_INVNO;
        public String U_LAT;
        public String U_LONG;
        public String U_SOURCE;
        public String U_EMIRATESID;
        public String U_VATNUMBER;
        public String CreateDate;
        public String CreateTime;
        public String UpdateDate;
        public String UpdateTime;
        public String Zone;
        public String LoyaltyPoints;
        public String ShipToDefault;

        public String BpAddresses;
        public ArrayList<BPLIDs> BPLID;
        public ArrayList<ContactEmployee> ContactEmployees = new ArrayList<>();
        public ArrayList<BPAddress> BPAddresses = new ArrayList<>();

        public class BPLIDs implements Serializable{
            public int id;
           
            public String BPLId;
           
            public String BPLName;
           
            public String Address;
           
            public String MainBPL;
           
            public String Disabled;
           
            public String UserSign2;
           
            public String UpdateDate;
           
            public String DflWhs;
           
            public String TaxIdNum;
           
            public String StreetNo;
           
            public String Building;
           
            public String ZipCode;
           
            public String City;
           
            public String State;
           
            public String Country;
           
            public String Series;
           
            public String FederalTaxID;
        }


        // Getter for ShipToDefault
        public String getShipToDefault() {
            return ShipToDefault;
        }

        // Setter for ShipToDefault
        public void setShipToDefault(String ShipToDefault) {
            this.ShipToDefault = ShipToDefault;
        }

        public String getBpAddresses() {
            return BpAddresses;
        }

        public void setBpAddresses(String bpAddresses) {
            BpAddresses = bpAddresses;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCardCode() {
            return CardCode;
        }

        public void setCardCode(String cardCode) {
            CardCode = cardCode;
        }

        public String getCardName() {
            return CardName;
        }

        public void setCardName(String cardName) {
            CardName = cardName;
        }

        public String getIndustry() {
            return Industry;
        }

        public void setIndustry(String industry) {
            Industry = industry;
        }

        public String getCardType() {
            return CardType;
        }

        public void setCardType(String cardType) {
            CardType = cardType;
        }

        public String getWebsite() {
            return Website;
        }

        public void setWebsite(String website) {
            Website = website;
        }

        public String getEmailAddress() {
            return EmailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            EmailAddress = emailAddress;
        }

        public String getPhone1() {
            return Phone1;
        }

        public void setPhone1(String phone1) {
            Phone1 = phone1;
        }

        public String getCountryCode() {
            return CountryCode;
        }



        public void setCountryCode(String countryCode) {
            CountryCode = countryCode;
        }

        public String getDiscountPercent() {
            return DiscountPercent;
        }

        public void setDiscountPercent(String discountPercent) {
            DiscountPercent = discountPercent;
        }

        public String getCurrency() {
            return Currency;
        }

        public void setCurrency(String currency) {
            Currency = currency;
        }

        public String getIntrestRatePercent() {
            return IntrestRatePercent;
        }

        public void setIntrestRatePercent(String intrestRatePercent) {
            IntrestRatePercent = intrestRatePercent;
        }

        public String getCommissionPercent() {
            return CommissionPercent;
        }

        public void setCommissionPercent(String commissionPercent) {
            CommissionPercent = commissionPercent;
        }

        public String getNotes() {
            return Notes;
        }

        public void setNotes(String notes) {
            Notes = notes;
        }

        public String getPayTermsGrpCode() {
            return PayTermsGrpCode;
        }

        public void setPayTermsGrpCode(String payTermsGrpCode) {
            PayTermsGrpCode = payTermsGrpCode;
        }

        public String getCreditLimit() {
            return CreditLimit;
        }

        public void setCreditLimit(String creditLimit) {
            CreditLimit = creditLimit;
        }

        public String getAttachmentEntry() {
            return AttachmentEntry;
        }

        public void setAttachmentEntry(String attachmentEntry) {
            AttachmentEntry = attachmentEntry;
        }

        public String getSalesPersonCode() {
            return SalesPersonCode;
        }

        public void setSalesPersonCode(String salesPersonCode) {
            SalesPersonCode = salesPersonCode;
        }

        public String getContactPerson() {
            return ContactPerson;
        }

        public void setContactPerson(String contactPerson) {
            ContactPerson = contactPerson;
        }

        public String getU_LEADID() {
            return U_LEADID;
        }

        public void setU_LEADID(String u_LEADID) {
            U_LEADID = u_LEADID;
        }

        public String getU_LEADNM() {
            return U_LEADNM;
        }

        public void setU_LEADNM(String u_LEADNM) {
            U_LEADNM = u_LEADNM;
        }

        public String getU_PARENTACC() {
            return U_PARENTACC;
        }

        public void setU_PARENTACC(String u_PARENTACC) {
            U_PARENTACC = u_PARENTACC;
        }

        public String getU_BPGRP() {
            return U_BPGRP;
        }

        public void setU_BPGRP(String u_BPGRP) {
            U_BPGRP = u_BPGRP;
        }

        public String getU_CONTOWNR() {
            return U_CONTOWNR;
        }

        public void setU_CONTOWNR(String u_CONTOWNR) {
            U_CONTOWNR = u_CONTOWNR;
        }

        public String getU_RATING() {
            return U_RATING;
        }

        public void setU_RATING(String u_RATING) {
            U_RATING = u_RATING;
        }

        public String getU_TYPE() {
            return U_TYPE;
        }

        public void setU_TYPE(String u_TYPE) {
            U_TYPE = u_TYPE;
        }

        public String getU_ANLRVN() {
            return U_ANLRVN;
        }

        public void setU_ANLRVN(String u_ANLRVN) {
            U_ANLRVN = u_ANLRVN;
        }

        public String getU_CURBAL() {
            return U_CURBAL;
        }

        public void setU_CURBAL(String u_CURBAL) {
            U_CURBAL = u_CURBAL;
        }

        public String getU_ACCNT() {
            return U_ACCNT;
        }

        public void setU_ACCNT(String u_ACCNT) {
            U_ACCNT = u_ACCNT;
        }

        public String getU_INVNO() {
            return U_INVNO;
        }

        public void setU_INVNO(String u_INVNO) {
            U_INVNO = u_INVNO;
        }

        public String getU_LAT() {
            return U_LAT;
        }

        public void setU_LAT(String u_LAT) {
            U_LAT = u_LAT;
        }

        public String getU_LONG() {
            return U_LONG;
        }

        public void setU_LONG(String u_LONG) {
            U_LONG = u_LONG;
        }

        public String getU_SOURCE() {
            return U_SOURCE;
        }

        public void setU_SOURCE(String u_SOURCE) {
            U_SOURCE = u_SOURCE;
        }

        public String getU_EMIRATESID() {
            return U_EMIRATESID;
        }

        public void setU_EMIRATESID(String u_EMIRATESID) {
            U_EMIRATESID = u_EMIRATESID;
        }

        public String getU_VATNUMBER() {
            return U_VATNUMBER;
        }

        public void setU_VATNUMBER(String u_VATNUMBER) {
            U_VATNUMBER = u_VATNUMBER;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String createDate) {
            CreateDate = createDate;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String createTime) {
            CreateTime = createTime;
        }

        public String getUpdateDate() {
            return UpdateDate;
        }

        public void setUpdateDate(String updateDate) {
            UpdateDate = updateDate;
        }

        public String getUpdateTime() {
            return UpdateTime;
        }

        public void setUpdateTime(String updateTime) {
            UpdateTime = updateTime;
        }

        public String getZone() {
            return Zone;
        }

        public void setZone(String zone) {
            Zone = zone;
        }

        public String getLoyaltyPoints() {
            return LoyaltyPoints;
        }

        public void setLoyaltyPoints(String loyaltyPoints) {
            LoyaltyPoints = loyaltyPoints;
        }

        public ArrayList<BPLIDs> getBPLID() {
            return BPLID;
        }

        public void setBPLID(ArrayList<BPLIDs> BPLID) {
            this.BPLID = BPLID;
        }

        public ArrayList<ContactEmployee> getContactEmployees() {
            return ContactEmployees;
        }

        public void setContactEmployees(ArrayList<ContactEmployee> contactEmployees) {
            ContactEmployees = contactEmployees;
        }

        public ArrayList<BPAddress> getBPAddresses() {
            return BPAddresses;
        }

        public void setBPAddresses(ArrayList<BPAddress> BPAddresses) {
            this.BPAddresses = BPAddresses;
        }
    }

    public class BPAddress implements Serializable {
        public String id;
        public String AddressType;
        public String BPID;
        public String BPCode;
        public String AddressName;
        public String Street;
        public String Block;
        public String City;
        public String State;
        public String ZipCode;
        public String Country;
        public String RowNum;
        public String U_SHPTYP;
        public String U_COUNTRY;
        public String U_STATE;
        public String BranchName;
        public String AddressName2;
        public String AddressName3;
        public String BuildingFloorRoom;
        public String County;
        public String Phone;
        public String CountryCode;
        public String Fax;
        public String Email;
        public String TaxOffice;
        public String GSTIN;
        public String GstType;
        public String ShippingType;
        public String PaymentTerm;
        public String CurrentBalance;
        public String CreditLimit;
        public String Lat;
        public String Long;
        public int Status;
        public int Default;
        public String CreateDate;
        public String CreateTime;
        public String UpdateDate;
        public String UpdateTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAddressType() {
            return AddressType;
        }

        public void setAddressType(String addressType) {
            AddressType = addressType;
        }

        public String getBPID() {
            return BPID;
        }

        public void setBPID(String BPID) {
            this.BPID = BPID;
        }

        public String getBPCode() {
            return BPCode;
        }

        public void setBPCode(String BPCode) {
            this.BPCode = BPCode;
        }

        public String getAddressName() {
            return AddressName;
        }

        public void setAddressName(String addressName) {
            AddressName = addressName;
        }

        public String getStreet() {
            return Street;
        }

        public void setStreet(String street) {
            Street = street;
        }

        public String getBlock() {
            return Block;
        }

        public void setBlock(String block) {
            Block = block;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String city) {
            City = city;
        }

        public String getState() {
            return State;
        }

        public void setState(String state) {
            State = state;
        }

        public String getZipCode() {
            return ZipCode;
        }

        public void setZipCode(String zipCode) {
            ZipCode = zipCode;
        }

        public String getCountry() {
            return Country;
        }

        public void setCountry(String country) {
            Country = country;
        }

        public String getRowNum() {
            return RowNum;
        }

        public void setRowNum(String rowNum) {
            RowNum = rowNum;
        }

        public String getU_SHPTYP() {
            return U_SHPTYP;
        }

        public void setU_SHPTYP(String u_SHPTYP) {
            U_SHPTYP = u_SHPTYP;
        }

        public String getU_COUNTRY() {
            return U_COUNTRY;
        }

        public void setU_COUNTRY(String u_COUNTRY) {
            U_COUNTRY = u_COUNTRY;
        }

        public String getU_STATE() {
            return U_STATE;
        }

        public void setU_STATE(String u_STATE) {
            U_STATE = u_STATE;
        }

        public String getBranchName() {
            return BranchName;
        }

        public void setBranchName(String branchName) {
            BranchName = branchName;
        }

        public String getAddressName2() {
            return AddressName2;
        }

        public void setAddressName2(String addressName2) {
            AddressName2 = addressName2;
        }

        public String getAddressName3() {
            return AddressName3;
        }

        public void setAddressName3(String addressName3) {
            AddressName3 = addressName3;
        }

        public String getBuildingFloorRoom() {
            return BuildingFloorRoom;
        }

        public void setBuildingFloorRoom(String buildingFloorRoom) {
            BuildingFloorRoom = buildingFloorRoom;
        }

        public String getCounty() {
            return County;
        }

        public void setCounty(String county) {
            County = county;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String phone) {
            Phone = phone;
        }

        public String getCountryCode() {
            return CountryCode;
        }

        public void setCountryCode(String countryCode) {
            CountryCode = countryCode;
        }

        public String getFax() {
            return Fax;
        }

        public void setFax(String fax) {
            Fax = fax;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            Email = email;
        }

        public String getTaxOffice() {
            return TaxOffice;
        }

        public void setTaxOffice(String taxOffice) {
            TaxOffice = taxOffice;
        }

        public String getGSTIN() {
            return GSTIN;
        }

        public void setGSTIN(String GSTIN) {
            this.GSTIN = GSTIN;
        }

        public String getGstType() {
            return GstType;
        }

        public void setGstType(String gstType) {
            GstType = gstType;
        }

        public String getShippingType() {
            return ShippingType;
        }

        public void setShippingType(String shippingType) {
            ShippingType = shippingType;
        }

        public String getPaymentTerm() {
            return PaymentTerm;
        }

        public void setPaymentTerm(String paymentTerm) {
            PaymentTerm = paymentTerm;
        }

        public String getCurrentBalance() {
            return CurrentBalance;
        }

        public void setCurrentBalance(String currentBalance) {
            CurrentBalance = currentBalance;
        }

        public String getCreditLimit() {
            return CreditLimit;
        }

        public void setCreditLimit(String creditLimit) {
            CreditLimit = creditLimit;
        }

        public String getLat() {
            return Lat;
        }

        public void setLat(String lat) {
            Lat = lat;
        }

        public String getLong() {
            return Long;
        }

        public void setLong(String aLong) {
            Long = aLong;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int status) {
            Status = status;
        }

        public int getDefault() {
            return Default;
        }

        public void setDefault(int aDefault) {
            Default = aDefault;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String createDate) {
            CreateDate = createDate;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String createTime) {
            CreateTime = createTime;
        }

        public String getUpdateDate() {
            return UpdateDate;
        }

        public void setUpdateDate(String updateDate) {
            UpdateDate = updateDate;
        }

        public String getUpdateTime() {
            return UpdateTime;
        }

        public void setUpdateTime(String updateTime) {
            UpdateTime = updateTime;
        }
    }

    public class ContactEmployee implements Serializable {
        public String id;
        public String Title;
        public String FirstName;
        public String MiddleName;
        public String LastName;
        public String Position;
        public String Address;
        public String MobilePhone;
        public String CountryCode;
        public String Fax;
        public String E_Mail;
        public String Remarks1;
        public String InternalCode;
        public String DateOfBirth;
        public String Gender;
        public String Profession;
        public String CardCode;
        public int U_BPID;
        public String U_BRANCHID;
        public String U_NATIONALTY;
        public String CreateDate;
        public String CreateTime;
        public String UpdateDate;
        public String UpdateTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getFirstName() {
            return FirstName;
        }

        public void setFirstName(String firstName) {
            FirstName = firstName;
        }

        public String getMiddleName() {
            return MiddleName;
        }

        public void setMiddleName(String middleName) {
            MiddleName = middleName;
        }

        public String getLastName() {
            return LastName;
        }

        public void setLastName(String lastName) {
            LastName = lastName;
        }

        public String getPosition() {
            return Position;
        }

        public void setPosition(String position) {
            Position = position;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String address) {
            Address = address;
        }

        public String getMobilePhone() {
            return MobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            MobilePhone = mobilePhone;
        }

        public String getCountryCode() {
            return CountryCode;
        }

        public void setCountryCode(String countryCode) {
            CountryCode = countryCode;
        }

        public String getFax() {
            return Fax;
        }

        public void setFax(String fax) {
            Fax = fax;
        }

        public String getE_Mail() {
            return E_Mail;
        }

        public void setE_Mail(String e_Mail) {
            E_Mail = e_Mail;
        }

        public String getRemarks1() {
            return Remarks1;
        }

        public void setRemarks1(String remarks1) {
            Remarks1 = remarks1;
        }

        public String getInternalCode() {
            return InternalCode;
        }

        public void setInternalCode(String internalCode) {
            InternalCode = internalCode;
        }

        public String getDateOfBirth() {
            return DateOfBirth;
        }

        public void setDateOfBirth(String dateOfBirth) {
            DateOfBirth = dateOfBirth;
        }

        public String getGender() {
            return Gender;
        }

        public void setGender(String gender) {
            Gender = gender;
        }

        public String getProfession() {
            return Profession;
        }

        public void setProfession(String profession) {
            Profession = profession;
        }

        public String getCardCode() {
            return CardCode;
        }

        public void setCardCode(String cardCode) {
            CardCode = cardCode;
        }

        public int getU_BPID() {
            return U_BPID;
        }

        public void setU_BPID(int u_BPID) {
            U_BPID = u_BPID;
        }

        public String getU_BRANCHID() {
            return U_BRANCHID;
        }

        public void setU_BRANCHID(String u_BRANCHID) {
            U_BRANCHID = u_BRANCHID;
        }

        public String getU_NATIONALTY() {
            return U_NATIONALTY;
        }

        public void setU_NATIONALTY(String u_NATIONALTY) {
            U_NATIONALTY = u_NATIONALTY;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String createDate) {
            CreateDate = createDate;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String createTime) {
            CreateTime = createTime;
        }

        public String getUpdateDate() {
            return UpdateDate;
        }

        public void setUpdateDate(String updateDate) {
            UpdateDate = updateDate;
        }

        public String getUpdateTime() {
            return UpdateTime;
        }

        public void setUpdateTime(String updateTime) {
            UpdateTime = updateTime;
        }
    }

    public class Meta {
        public int count;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

}
