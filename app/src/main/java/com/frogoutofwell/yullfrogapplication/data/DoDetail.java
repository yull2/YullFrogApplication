package com.frogoutofwell.yullfrogapplication.data;

/**
 * Created by Tacademy on 2016-05-16.
 */
public class DoDetail {
    public int seq;
    public int memberSeq;
    public int rate;
    public String writeTime;
    public String comment;
    public String commentGood;
    public String commentBad;

    public InterDetail doInter;

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getMemberSeq() {
        return memberSeq;
    }

    public void setMemberSeq(int memberSeq) {
        this.memberSeq = memberSeq;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(String writeTime) {
        this.writeTime = writeTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentGood() {
        return commentGood;
    }

    public void setCommentGood(String commentGood) {
        this.commentGood = commentGood;
    }

    public String getCommentBad() {
        return commentBad;
    }

    public void setCommentBad(String commentBad) {
        this.commentBad = commentBad;
    }

    public InterDetail getDoInter() {
        return doInter;
    }

    public void setDoInter(InterDetail doInter) {
        this.doInter = doInter;
    }
}
