package tools;

public class MathPercent {

    public static float addPercent(float startInt, int percent) {
        if (percent < 1 || startInt <= 0) return startInt;
        float newInt = startInt + (startInt / 100.0f * percent);
        return newInt;
    }

    public static float addPercent(int startInt, int percent) {
        return MathPercent.addPercent((float) startInt, percent);
    }

    public static float subPercent(float startInt, int percent) {
        if (percent < 1 || startInt <= 0) return startInt;
        float newInt = startInt - (startInt / 100.0f * percent);
        return newInt;
    }

    public static float subPercent(int startInt, int percent) {
        return MathPercent.subPercent((float) startInt, percent);
    }
}
