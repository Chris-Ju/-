/**
 * Create a node including next and pre node and column to decide position.
 * @author  Chris Ju
 */
public class SparseGridNode {
    private Object occupant;
    private int col;
    private SparseGridNode next;
    private SparseGridNode pre;

    public SparseGridNode(Object occ, int column, SparseGridNode newPre, SparseGridNode newNext) {
        occupant = occ;
        col = column;
        pre = newPre;
        next = newNext;
    }

    /**
     * getNode method
     * @return occupant this.occupant
     */
    public Object getOccupant() {
        return occupant;
    }

    /**
     * getColumn method
     * @return col this.col
     */
    public int getCol() {
        return col;
    }

    /**
     * getPre method
     * @return pre this.pre
     */
    public SparseGridNode getPre() {
        return pre;
    }


    /**
     * getNext method
     * @return next this.next
     */
    public SparseGridNode getNext() {
        return next;
    }

    /**
     * setOccupant method
     * @param occ set this occupant 
     */
    public void setOccupant(Object occ) {
        occupant = occ;
    }

    /**
     * setPre method
     * @param newPre set pre occupant
     */
    public void setPre(SparseGridNode newPre) {
        pre = newPre;
    }

    /**
     * setNext method
     * @param newNext set next occupant
     */
    public void setNext(SparseGridNode newNext) {
        next = newNext;
    }
}