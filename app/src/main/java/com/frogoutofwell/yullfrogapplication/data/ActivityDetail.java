package com.frogoutofwell.yullfrogapplication.data;

/**
 * Created by Tacademy on 2016-05-17.
 */
public class ActivityDetail {
    public int seq;
    public String name;
    public String endDate;
    public int averageRate;
    public InterDetail institution;
    public String act;
    public String indus;
    public int term;
    public String region;
    public int totalPostCount;
    public int totalPostSatisfaction;
    public int totalInterCount;
    public int totalInterLevel;


    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(int averageRate) {
        this.averageRate = averageRate;
    }

    public InterDetail getInstitution() {
        return institution;
    }

    public void setInstitution(InterDetail institution) {
        this.institution = institution;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getIndus() {
        return indus;
    }

    public void setIndus(String indus) {
        this.indus = indus;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getTotalPostCount() {
        return totalPostCount;
    }

    public void setTotalPostCount(int totalPostCount) {
        this.totalPostCount = totalPostCount;
    }

    public int getTotalPostSatisfaction() {
        return totalPostSatisfaction;
    }

    public void setTotalPostSatisfaction(int totalPostSatisfaction) {
        this.totalPostSatisfaction = totalPostSatisfaction;
    }

    public int getTotalInterCount() {
        return totalInterCount;
    }

    public void setTotalInterCount(int totalInterCount) {
        this.totalInterCount = totalInterCount;
    }

    public int getTotalInterLevel() {
        return totalInterLevel;
    }

    public void setTotalInterLevel(int totalInterLevel) {
        this.totalInterLevel = totalInterLevel;
    }
}
