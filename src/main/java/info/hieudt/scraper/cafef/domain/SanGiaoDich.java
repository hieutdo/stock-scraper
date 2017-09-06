package info.hieudt.scraper.cafef.domain;

public enum SanGiaoDich {
    HSX(1), HNX(2), UPCOM(3);

    private int value;

    private SanGiaoDich(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
