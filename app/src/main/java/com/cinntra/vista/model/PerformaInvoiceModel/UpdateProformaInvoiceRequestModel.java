package com.cinntra.vista.model.PerformaInvoiceModel;


import com.cinntra.vista.model.AddressExtensions;
import com.cinntra.vista.model.DocumentLines;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class UpdateProformaInvoiceRequestModel implements Serializable {
    public String id;
    public String U_QUOTNM;

    @SerializedName("U_QUOTID")
    @Expose
    String U_QUOTID;
    public String QuoteNo;

    public float NetTotal;

    float DocTotal;

    public String TaxDate;

    public String DocDueDate;

    public String ContactPersonCode;

    public float DiscountPercent;

    public String DocDate;

    public String CardCode;

    public String CardName;

    public String Comments;

    public String SalesPersonCode;
    String departement;
    String PRID;

    public String U_OPPID;

    public String U_OPPRNM;

    public AddressExtensions AddressExtension;

    public ArrayList<com.cinntra.vista.model.DocumentLines> DocumentLines;

    public String CreateDate;

    public String CreateTime;

    public String UpdateDate;

    public String UpdateTime;
    public String BPLID;
    public String PaymentGroupCode;
    public String FreightCharge;


    public String getU_QUOTID() {
        return U_QUOTID;
    }

    public void setU_QUOTID(String u_QUOTID) {
        U_QUOTID = u_QUOTID;
    }

    public String getBPLID() {
        return BPLID;
    }

    public void setBPLID(String BPLID) {
        this.BPLID = BPLID;
    }

    public String getPaymentGroupCode() {
        return PaymentGroupCode;
    }

    public void setPaymentGroupCode(String paymentGroupCode) {
        PaymentGroupCode = paymentGroupCode;
    }

    public String getFreightCharge() {
        return FreightCharge;
    }

    public void setFreightCharge(String freightCharge) {
        FreightCharge = freightCharge;
    }

    public String getPRID() {
        return PRID;
    }

    public void setPRID(String PRID) {
        this.PRID = PRID;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getQuoteNo() {
        return QuoteNo;
    }

    public void setQuoteNo(String quoteNo) {
        QuoteNo = quoteNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getU_QUOTNM() {
        return U_QUOTNM;
    }

    public void setU_QUOTNM(String u_QUOTNM) {
        U_QUOTNM = u_QUOTNM;
    }


    public float getDocTotal() {
        return DocTotal;
    }

    public void setDocTotal(float docTotal) {
        DocTotal = docTotal;
    }

    public float getNetTotal() {
        return NetTotal;
    }

    public void setNetTotal(float netTotal) {
        NetTotal = netTotal;
    }

    public String getTaxDate() {
        return TaxDate;
    }

    public void setTaxDate(String taxDate) {
        TaxDate = taxDate;
    }

    public String getDocDueDate() {
        return DocDueDate;
    }

    public void setDocDueDate(String docDueDate) {
        DocDueDate = docDueDate;
    }

    public String getContactPersonCode() {
        return ContactPersonCode;
    }

    public void setContactPersonCode(String contactPersonCode) {
        ContactPersonCode = contactPersonCode;
    }

    public float getDiscountPercent() {
        return DiscountPercent;
    }

    public void setDiscountPercent(float discountPercent) {
        DiscountPercent = discountPercent;
    }

    public String getDocDate() {
        return DocDate;
    }

    public void setDocDate(String docDate) {
        DocDate = docDate;
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

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    public String getSalesPersonCode() {
        return SalesPersonCode;
    }

    public void setSalesPersonCode(String salesPersonCode) {
        SalesPersonCode = salesPersonCode;
    }

    public String getU_OPPID() {
        return U_OPPID;
    }

    public void setU_OPPID(String u_OPPID) {
        U_OPPID = u_OPPID;
    }

    public String getU_OPPRNM() {
        return U_OPPRNM;
    }

    public void setU_OPPRNM(String u_OPPRNM) {
        U_OPPRNM = u_OPPRNM;
    }

    public AddressExtensions getAddressExtension() {
        return AddressExtension;
    }

    public void setAddressExtension(AddressExtensions addressExtension) {
        AddressExtension = addressExtension;
    }

    public ArrayList<DocumentLines> getDocumentLines() {
        return DocumentLines;
    }

    public void setDocumentLines(ArrayList<DocumentLines> documentLines) {
        DocumentLines = documentLines;
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
