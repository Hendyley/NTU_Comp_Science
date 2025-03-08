package MovieGoerModule;

import java.io.Serializable;

public class Review implements Serializable{
    private int userRating;
    private String remark;

    public Review(int rating, String rv){
        this.userRating = rating;
        this.remark = rv;
    }

    public int getUserRating(){
        return userRating;
    }

    public void setRating(int rating) {
        this.userRating = rating;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark(){
        return remark;
    }



}
