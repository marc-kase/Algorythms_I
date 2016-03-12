
public class TestBoard extends Board{
    public TestBoard(int[][] blocks) {
        super(blocks);
    }

    public TestBoard twin() {
        return (TestBoard) super.twin();
    }
}
