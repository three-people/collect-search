package com.baseinfo.collect.beans;

import com.baseinfo.collect.common.IndexConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = IndexConstants.PERSONINDEXNAME)
public class PersonEsIndex {
    @Id
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String personID;

    @Field(type = FieldType.String,index = FieldIndex.not_analyzed, store = true)
    private String sex;

    @Field(type = FieldType.String,index = FieldIndex.not_analyzed, store = true)
    private String Uname;

    @Field(type = FieldType.Attachment.String,index = FieldIndex.analyzed, analyzer = "ik",store = true)
    private String addrass;

    public String getPersonID() {
        return personID;
    }

    public String getSex() {
        return sex;
    }

    public String getUname() {
        return Uname;
    }

    public String getAddrass() {
        return addrass;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setUname(String uname) {
        Uname = uname;
    }

    public void setAddrass(String addrass) {
        this.addrass = addrass;
    }
}
