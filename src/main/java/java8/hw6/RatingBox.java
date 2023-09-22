package java8.hw6;

public class RatingBox {

    String word;
    long rating;
    long count;
    double percentageAll;
    double percentageInSentences;

    public double getPercentageAll() {
        return percentageAll;
    }

    public void setPercentageAll(double percentageAll) {
        this.percentageAll = percentageAll;
    }

    public void setPercentageInSentences(double percentageInSentences) {
        this.percentageInSentences = percentageInSentences;
    }

    public RatingBox(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public long getRating() {
        return rating;
    }

    public long getCount() {
        return count;
    }


    public void setWord(String word) {
        this.word = word;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "RatingBox{" +
                "word='" + word + '\'' +
                ", rating=" + rating +
                ", count=" + count +
                ", percentageAll=" + percentageAll +
                ", percentageInSentences=" + percentageInSentences +
                '}';
    }
}
