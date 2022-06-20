package BusinessLogic;

public class Review {

    private String itemID;
    private int reviewGrade;
    private String comment;

    public Review(String itemID, int reviewGrade) {
        this.itemID = itemID;
        this.reviewGrade = reviewGrade;
    }

    public Review (String itemID, int reviewGrade, String comment) {
        this.itemID = itemID;
        this.reviewGrade = reviewGrade;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Grade: " + reviewGrade + "." + comment;
    }

    public int getReviewGrade() {
        return this.reviewGrade;
    }

    public String getComment() {
        return this.comment;
    }

}
