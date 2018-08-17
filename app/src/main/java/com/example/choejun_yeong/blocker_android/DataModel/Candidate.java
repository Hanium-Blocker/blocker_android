package com.example.choejun_yeong.blocker_android.DataModel;

public class Candidate {

    private int election_id;
    private int number;
    private String name;
    private String party;
    private String birth;
    private String gender;
    private String campaign_link;

    public int getElection_id() {
        return election_id;
    }

    public void setElection_id(int election_id) {
        this.election_id = election_id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCampaign_link() {
        return campaign_link;
    }

    public void setCampaign_link(String campaign_link) {
        this.campaign_link = campaign_link;
    }
}
