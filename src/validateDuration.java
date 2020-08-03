public class validateDuration {
    public validateDuration(Integer duration) throws NegativeDurationException {
        if(duration < 0) {
            throw new NegativeDurationException("Negative duration: " + duration);
        }
    }

}
