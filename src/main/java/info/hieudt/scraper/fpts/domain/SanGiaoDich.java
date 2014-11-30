package info.hieudt.scraper.fpts.domain;

public enum SanGiaoDich {
    HOSE(1), HNX(2), UPCOM(4); //, VN30, HNX30;

    private int value;

    private SanGiaoDich(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
